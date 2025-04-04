pub struct Solution;
impl Solution {
    pub fn my_atoi(s: String) -> i32 {
        let mut ans:i32=0;
        let mut ans_str = String::new();
        let mut is_postive = true;
        let mut white_space_flag = true;
        for chr in s.chars() {
            if white_space_flag {
                match chr {
                    ' '=>(),
                    '+'=>{
                        white_space_flag = false;
                    },
                    '-'=>{
                        is_postive = false;
                        white_space_flag = false;
                    },
                    '0'..='9'=>{
                        white_space_flag = false;
                        ans_str.push(chr);
                    },
                    _=>{
                        return 0;
                    },
                }
            }else {
                match chr {
                    '0'..='9'=>{
                        ans_str.push(chr);
                    },
                    _=>{
                        if ans_str == String::from("") {
                            return 0;
                        }
                        ans = match ans_str.parse::<i32>() {
                            Ok(value)=>{
                                if is_postive {
                                    value
                                }else {
                                    -value
                                }
                                
                            },
                            Err(_)=>{
                                if is_postive{
                                    2147483647
                                }else {
                                    -2147483648
                                }
                                
                            },
                        };
                        return ans;
                    },
                }
            }
        }
        if ans_str == String::from("") {
            return 0;
        }
        ans = match ans_str.parse::<i32>() {
            Ok(value)=>{
                if is_postive {
                    value
                }else {
                    -value
                }
                
            },
            Err(_)=>{
                if is_postive{
                    2147483647
                }else {
                    -2147483648
                }
                
            },
        };
        ans
    }
}


