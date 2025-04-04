struct Solution;
impl Solution {
    pub fn is_palindrome(x: i32) -> bool {
        if x < 0 {
            return false;
        }
        let i32_to_string:String = x.to_string();
        let reverse:String = i32_to_string.chars().rev().collect();
        reverse==i32_to_string
    }
}
fn main(){
    let outcome = Solution::is_palindrome(121);
    println!("{}",outcome);
}