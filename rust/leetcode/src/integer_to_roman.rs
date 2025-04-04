struct Solution;
impl Solution {
    pub fn int_to_roman(num: i32) -> String {
        let mut ans  = String::from("");
        let mut a = num;
        while a != 0 {
            if a >= 1000 {
                ans.push_str("M");
                a-=1000;
            }else if a>=900 {
                ans.push_str("CM");
                a-=900;
            }else if a>=500 {
                ans.push_str("D");
                a-=500;
            }
            else if a>=400 {
                ans.push_str("CD");
                a-=400;
            }else if a>=100 {
                ans.push_str("C");
                a-=100;
            }else if a>=90 {
                ans.push_str("XC");
                a-=90;
            }else if a>=50 {
                ans.push_str("L");
                a-=50;
            }else if a>=40 {
                ans.push_str("XL");
                a-=40;
            }
            else if a>=10 {
                ans.push_str("X");
                a-=10;
            }
            else if a>=9 {
                ans.push_str("IX");
                a-=9;
            }
            else if a>=5 {
                ans.push_str("V");
                a-=5;
            }else if a>=4 {
                ans.push_str("IV");
                a-=4;
            }else if a>=1 {
                ans.push_str("I");
                a-=1;
            }
        }
        ans
    }
}