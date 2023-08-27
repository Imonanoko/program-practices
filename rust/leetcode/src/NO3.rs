pub struct Solution{}
impl Solution {
    pub fn length_of_longest_substring(s: String) -> i32 {
        let set:Vec<char> = s.chars().collect();
        let len = set.len();
        let (mut start,mut end,mut size) = (0,0,0);
        while end<len {
            for i in start..end{
                if set[i]==set[end]{
                    start = i+1;
                    break;
                }
            }
            let cur_size = end-start+1;
            if cur_size>size{
                size = cur_size;
            }
            end+=1
        }
        size as i32
    }
}

pub fn main() {
    let a: String = "abcabcbb".to_string();
    let x = Solution::length_of_longest_substring(a);
    println!("{}", x);
}
