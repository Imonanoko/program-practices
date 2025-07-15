use hex::encode;
use sha2::{Digest, Sha256};
struct MerkleTree {
    leaves: Vec<String>,          // 真實資料
    pub levels: Vec<Vec<String>>, //hash過的tree
}
impl MerkleTree {
    fn new(data: Vec<String>) -> Self {
        let mut levels: Vec<Vec<String>> = Vec::new();
        let mut current: Vec<String> = data
            .iter()
            .map(|d| {
                let hash = Sha256::digest(d.as_bytes());
                encode(hash)
            })
            .collect();

        if current.len() % 2 != 0 {
            current.push(current.last().unwrap().clone());
        }
        levels.push(current.clone());
        while current.len() > 1 {
            current = current
                .chunks(2)
                .map(|chunk| {
                    let mut hasher = Sha256::new();
                    hasher.update(chunk[0].as_bytes());
                    if chunk.len() == 2 {
                        hasher.update(chunk[1].as_bytes());
                    } else {
                        hasher.update(chunk[0].as_bytes());
                    }

                    encode(hasher.finalize())
                })
                .collect();
            levels.push(current.clone());
        }
        MerkleTree {
            leaves: data,
            levels: levels,
        }
    }
    fn get_root(&self) -> Option<String> {
        self.levels.last().and_then(|level| level.get(0)).cloned()
    }
    fn gen_proof(&self, mut index: usize) -> Vec<String> {
        assert!(
            index < self.leaves.len(),
            "Cannot generate proof for padding"
        );
        let mut proof = Vec::new();
        for i in 0..(self.levels.len() - 1) {
            if index % 2 == 0 {
                if index + 1 < self.levels[i].len() {
                    proof.push(self.levels[i][index + 1].clone());
                }
            } else {
                proof.push(self.levels[i][index - 1].clone());
            }
            index /= 2;
        }
        proof
    }
}
fn verify_proof(leaf: &String, mut index: usize, proof: &[String], root: &String) -> bool {
    let mut computed_hash = encode(Sha256::digest(leaf.as_bytes()));
    for hash in proof {
        let mut hasher = Sha256::new();
        if index % 2 == 0 {
            hasher.update(computed_hash.as_bytes());
            hasher.update(hash.as_bytes());
        } else {
            hasher.update(hash.as_bytes());
            hasher.update(computed_hash.as_bytes());
        };
        computed_hash = encode(hasher.finalize());
        index /= 2;
    }
    &computed_hash == root
}
fn main() {
    let data = vec!["a".to_string(), "b".to_string(), "c".to_string()];
    let tree = MerkleTree::new(data.clone());

    let index = 0;
    let leaf = &data[index];
    let proof = tree.gen_proof(index);
    if let Some(root) = tree.get_root() {
        assert!(verify_proof(leaf, index, &proof, &root));
    }
}
