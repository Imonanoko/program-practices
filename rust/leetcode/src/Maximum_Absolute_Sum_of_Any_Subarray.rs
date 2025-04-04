pub struct Solution;
use std::cmp::{max, min};
impl Solution {
    pub fn max_absolute_sum(nums: Vec<i32>) -> i32 {
        let mut max_sum = 0;
        let mut min_sum = 0;
        let mut cur_min = 0;
        let mut cur_max = 0;
        for num in nums {
            cur_max = max(cur_max+num,num);
            max_sum = max(max_sum,cur_max);
            cur_min = min(cur_min+num,num);
            min_sum = min(min_sum,cur_min);
        }
        return max(max_sum,min_sum.abs());
    }
}