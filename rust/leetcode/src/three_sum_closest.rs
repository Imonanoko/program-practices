struct Solution;
impl Solution {
    pub fn three_sum_closest(mut nums: Vec<i32>, target: i32) -> i32 {
        let mut ans: Option<i32> = None;
        nums.sort_unstable();
        for (i,&num) in nums.iter().enumerate() {
            let mut left = i+1;
            let mut right = nums.len()-1;
            
            while left < right {
                let sum = num+nums[left]+nums[right];
                let dis = (sum - target).abs();
                if dis == 0 {
                    return sum;
                }else if sum < target {
                    left += 1;
                } else {
                    right -= 1;
                }
                if let Some(val) = ans {
                    if (target - sum).abs() < (target - val).abs() {
                        ans = Some(sum);
                    }
                }else {
                    ans = Some(sum);
                }
            }
        }
        ans.unwrap()
    }
}