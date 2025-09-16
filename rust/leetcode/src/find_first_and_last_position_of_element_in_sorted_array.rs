pub struct Solution;
impl Solution {
    pub fn search_range(nums: Vec<i32>, target: i32) -> Vec<i32> {
        if nums.len() == 0 {
            return vec![-1,-1];
        }else if nums.len() == 1 {
            if nums[0] ==target {
                return vec![0,0];
            }else {
                return vec![-1,-1];
            }
        }
        let mut i = 0;
        let mut j = nums.len()-1;
        let mut ans = vec![i32::MAX,-1];
        let mut iflag = false;
        let mut jflag = false;
        while i<=j {
            if nums[i] == target && !iflag {
                ans[0] = i32::min(i as i32,ans[0]);
                iflag = true
            }else if nums[i] == target && iflag {
                ans[1] = i32::max(ans[1],i as i32);
            }

            if nums[j] == target && !jflag {
                ans[1] = i32::max(j as i32,ans[1]);
                jflag = true
            }else if nums[j] == target && jflag {
                ans[0] = i32::min(j as i32,ans[0]);
            }
            i+=1;
            j-=1;
        }
        if ans[0] == i32::MAX && ans[1] != -1 {
            return vec![ans[1],ans[1]];
        }else if ans[0] != i32::MAX && ans[1] == -1 {
            return vec![ans[0],ans[0]];
        }else if ans == vec![i32::MAX,-1]{
            return vec![-1,-1];
        }
        ans
    }
}