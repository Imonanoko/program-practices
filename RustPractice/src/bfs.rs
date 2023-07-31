//Input must be an adjacency matrix.
fn bfs(adjacency_matrix:Vec<Vec<bool>>,start:usize){
    assert_eq!(adjacency_matrix.len(), adjacency_matrix[0].len(), "Please enter a square matrix.");
    let size:usize = adjacency_matrix.len();
    assert!(size >start as usize, "Target not found.");
    let mut already_selected:Vec<bool> = vec![];
    for i in 0..size {
        already_selected.push(false);
    }
    already_selected[start]=true;
}
pub fn main(){
    let x:Vec<Vec<bool>>=vec![
        vec![true,false,true],
        vec![false,false,true],
        vec![true,true,true]
    ];

    bfs(x,2);
}
