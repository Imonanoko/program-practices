struct Solution;
impl Solution {
    pub fn four_sum(mut nums: Vec<i32>, target: i32) -> Vec<Vec<i32>> {
        let mut ans:Vec<Vec<i32>> = vec![];
        nums.sort();
        for i in 0..nums.len()-2{
            if i > 0 && nums[i] == nums[i - 1] {
                continue;
            }
            for j in i+1..nums.len()-1 {
                if j > i + 1 && nums[j] == nums[j - 1] {
                    continue;
                }
                let mut left = j+1;
                let mut right = nums.len()-1;
                while left < right {
                    let sum = nums[i]+nums[j]+nums[left]+nums[right];
                    if sum > target {
                        right-=1;
                    }else if sum < target {
                        left+=1;
                    }else {
                        ans.push(vec![nums[i],nums[j],nums[left],nums[right]]);
                        left+=1;
                        right-=1;
                        while left < right && nums[left] == nums[left - 1] {
                            left += 1;
                        }
                        while left < right && nums[right] == nums[right + 1] {
                            right -= 1;
                        }
                    }
                }
            }
        }
        ans
    }
}