use std::cmp::{max, min};

pub struct Solution;
impl Solution {
    pub fn max_area(height: Vec<i32>) -> i32 {
        let mut ans: i32 = 0;
        let mut x_axis_leight = (height.len() - 1) as i32;
        let mut iterator = height.iter();
        let mut left = iterator.next();
        let mut right = iterator.next_back();
        while let (Some(left_value),Some(right_value)) = (left,right) {
            ans  = max(ans, min(left_value, right_value)*x_axis_leight);
            if left_value < right_value {
                left = iterator.next();
            }else {
                right = iterator.next_back();
            }
            x_axis_leight-=1;
        }
        ans
    }
}
