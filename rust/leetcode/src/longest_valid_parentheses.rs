use std::cmp::max;

pub fn longest_valid_parentheses(s: String) -> i32 {
    let mut left: Vec<isize> = Vec::new();
    left.push(-1);
    let mut res = 0;
    for (index, chr) in s.chars().enumerate() {
        if chr == '(' {
            left.push(index as isize);
        }else {
            left.pop();
            if left.is_empty() {
                left.push(index as isize);
            }else {
                let cur = index as isize - left.last().unwrap();
                res = max(cur, res);
            }
        }
    }
    res as i32
}
