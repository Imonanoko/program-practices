pub struct Solution;
impl Solution {
    pub fn is_valid(s: String) -> bool {
        let mut vector:Vec<char> = Vec::new(); 
        for chr in s.chars() {
            if chr == '(' || chr == '[' || chr == '{'{
                vector.push(chr);
            }else {
                if let Some(c) = vector.pop() {
                    match chr {
                        ')' => {
                            if c != '('{
                                return false;
                            }
                        }
                        ']' => {
                            if c != '['{
                                return false;
                            }
                        }
                        '}' => {
                            if c != '{'{
                                return false;
                            }
                        }
                        _=> return false,
                    }
                }else {
                    print!("else");
                    return false;
                }
            }
        }
        vector.is_empty()
    }
}