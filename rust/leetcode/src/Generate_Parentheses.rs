struct Solution;
impl Solution {
    pub fn generate_parenthesis(n: i32) -> Vec<String> {
        let mut res:Vec<String> = Vec::new();
        fn combination(n:i32,shape:&str, left:i32,right:i32, res: &mut Vec<String>){
            if right>left || left > n{
                return;
            }else if left==n && right==n{
                res.push(shape.to_string());
            }
            combination(n, &format!("{}(",shape), left+1, right, res);
            combination(n, &format!("{})",shape), left, right+1, res);
        }
        combination(n, "(", 1, 0, &mut res);
        res       
    }
}