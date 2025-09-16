use core::num;

pub fn find_median_sorted_arrays(nums1: Vec<i32>, nums2: Vec<i32>) -> f64 {
    fn find_kth(num1:Vec<i32>, num2:Vec<i32>,k:usize) -> i32 {
        if num1.len() > num2.len() {
            return find_kth(num2.clone(), num1.clone(), k);
        }
        if num1.is_empty() {
            return num2[k-1];
        }
        if k == 1 {
            return num1[0].min(num2[0]);
        }
        let i = num1.len().min(k/2);
        let j = k - i;
        if num1[i-1] < num2[j-1] {
            find_kth(num1[i..].to_vec(), num2, k-i)
        }else {
            find_kth(num1, num2[j..].to_vec(), k-j)
        }
    }

    if (nums1.len() + nums2.len() )% 2 == 1 {
        return find_kth(nums1.clone(), nums2.clone(), (nums1.len() + nums2.len() + 1)/2) as f64
    }else {
        let left = find_kth(nums1.clone(), nums2.clone(), (nums1.len() + nums2.len() + 1)/2) as f64;
        let right = find_kth(nums1.clone(), nums2.clone(), (nums1.len() + nums2.len() + 2)/2) as f64;
        (left + right) / 2.0
    }
}