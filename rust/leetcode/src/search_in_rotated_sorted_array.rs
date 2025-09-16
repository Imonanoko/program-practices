pub fn search(nums: Vec<i32>, target: i32) -> i32 {
    let mut right:usize = nums.len()-1;
    let mut left:usize = 0;
    while left <= right {
        let mid = (right + left)/2;
        if nums[mid] == target {
            return mid as i32;
        }
        if nums[left] <= nums[mid] {
            if nums[left] <= target&& target < nums[mid] {
                right = mid-1;
            }else {
                left = mid +1;
            }
        }else {
            if  nums[mid] < target && target <=nums[right]{
                left = mid+1;
            }else {
                right = mid -1;
            }
        }
    }
    -1
}