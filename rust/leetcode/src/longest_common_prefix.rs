pub struct Solution;
impl Solution {
    pub fn longest_common_prefix(strs: Vec<String>) -> String {
        let mut ans = String::from("");
        let mut min_str = &strs[0];
        for str in strs.iter() {
            if min_str.len() > str.len() {
                min_str = str;
            }
        }

        for (i, chr) in min_str.chars().enumerate() {
            for j in 0..strs.len() {
                let str = &strs[j].chars().nth(i).unwrap();
                if *str != chr {
                    return ans;
                }

            }
            ans.push(chr);
        } 
        ans
    }
}