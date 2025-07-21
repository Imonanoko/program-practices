struct Solution;
impl Solution {
    pub fn sort_colors(nums: &mut Vec<i32>) {
        Self::quick_sort(nums, 0, nums.len() - 1);
    }
    fn quick_sort(nums: &mut Vec<i32>,left:usize,right:usize){
        if left < right {
            let pivot_index = Self::pivot(nums,left,right);
            Self::quick_sort(nums, left, pivot_index-1);
            Self::quick_sort(nums, pivot_index+1, right);
        }
    }
    fn pivot(nums: &mut Vec<i32>,left:usize,right:usize) -> usize {
        let p_num = nums[left];
        let mut p_index = left;
        for i in left..=right {
            if p_num > nums[i] {
                p_index += 1;
                nums.swap(p_index, i);
            }
        }
        nums.swap(p_index, left);
        p_index
    }
}