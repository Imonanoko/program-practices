use std::collections::HashMap;
pub fn two_sum(nums: Vec<i32>, target: i32) -> Vec<i32> {
    let mut num_index_map: HashMap<i32, usize> = HashMap::new();
    //enumerate()會讀到索引和裡面的值，第一個是索引第二個是值
    for (index, &num) in nums.iter().enumerate() {
        let complement = target - num;
        //找之前有沒有存在過這個數有的話將值設定給complement_index，沒找到index的話會回傳None，在if裡面會自動當成false
        if let Some(&complement_index) = num_index_map.get(&complement) {
            return vec![complement_index as i32, index as i32];
        }
        //將每次找到的數字和索引加進HashMap
        num_index_map.insert(num, index);
    }

    vec![]
}
pub fn main(){
    let nums = vec![2, 7, 11, 15];
    let target = 9;

    let result = two_sum(nums, target);
    println!("{:?}", result);
}
