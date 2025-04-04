struct Solution;
impl Solution {
    pub fn reverse(x: i32) -> i32 {
        if x<0 {
            let y = -x;
            let s = y.to_string();
            let rev_s:String = s.chars().rev().collect();
            let ans = match rev_s.parse::<i32>() {
                Ok(ans)=>ans,
                Err(_)=>{
                    println!("Error: Failed to parse the string into an integer.");
                    0
                }
            };
            return -ans;
        }   
        else {
            let s :String= x.to_string().chars().rev().collect();
            let ans:i32 = match s.parse::<i32>(){
                Ok(ans)=>ans,
                Err(_)=>{
                    println!("Error: Failed to parse the string into an integer.");
                    0 
                }
            };
            return ans;
        } 
    }
}
fn main(){
    assert!(Solution::reverse(123)==321);
    assert!(Solution::reverse(-123)==-321);
    assert!(Solution::reverse(120)==21);
}