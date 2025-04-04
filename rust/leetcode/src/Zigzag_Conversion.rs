struct Solution;

impl Solution {
    pub fn convert(s: String, num_rows: i32) ->String {
        if num_rows==1{
            return s;
        }
        let mut arr:Vec<String> = vec![String::new();num_rows as usize];
        let mut i=0;
        let mut ctr:bool = true;
        for chr in s.chars() {
            arr[i].push(chr);
            if ctr {
                i+=1;
                if i as i32 ==num_rows{
                    i-=2;
                    ctr = false;
                }
            }
            else {
                if i as i32 == 0{
                    i+=2;
                    ctr = true;
                }
                i-=1;
                
            }
        }
        let mut ans:String = String::new();
        for ss in arr {
            ans.extend(ss.chars());
        }
        ans
    }
}
fn main(){
    println!("first test:{}",Solution::convert("PAYPALISHIRING".to_string(), 3));
    assert!(Solution::convert("AB".to_string(), 1)=="AB");
    println!("second test:{}",Solution::convert("PAYPALISHIRING".to_string(), 4));
    assert!(Solution::convert("PAYPALISHIRING".to_string(), 4)=="PINALSIGYAHRPI");
}