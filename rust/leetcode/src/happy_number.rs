struct Solution;
impl Solution {
    pub fn is_happy(n: i32) -> bool {
        fn a(n:i32)->bool {
            let s = n.to_string();
            if s.len()==1 {
                if s == "1"{
                    return true;
                }
                else {
                    return false;
                }
            }else {
                let mut r = 0; 
                for i in s.chars() {
                    let c:i32 = i.to_string().parse::<i32>().unwrap();
                    r+=c*c;
                }
                return a(r);
            }
        }
        return a(n);
    }
}