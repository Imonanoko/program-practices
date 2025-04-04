struct Solution;
impl Solution {
    pub fn my_sqrt(x: i32) -> i32 {
        for i in 0..=46340 {
            let b = i*i;
            if b > x {
                return i-1;
            }
            else if b == x {
                return i;
            }
        }
        return 46340;
    }
}
pub fn main() {
    println!("{}",Solution::my_sqrt(i32::MAX));
}