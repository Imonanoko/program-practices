struct Solution;
impl Solution {
    pub fn remove_duplicates(nums: &mut Vec<i32>) -> i32 {
        for i in 0..nums.len() {
            for j in 1..(nums.len()-i) {
                if nums[j] < nums[j-1]{
                    let tmp = nums[j];
                    nums[j] = nums[j-1];
                    nums[j-1] = tmp;
                }
            }
        }
        let mut i=0;
        let mut j=0;
        while j<nums.len() {
            let now = nums[i];
            if nums[j] == now {
                j+=1;
                continue;
            }else{
                i+=1;
                nums[i] = nums[j];
            }
        }
        i+=1;
        i as i32
    }
}