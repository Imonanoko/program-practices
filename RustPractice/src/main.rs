// mod StableMatching;
//intellij 編譯器只會讀這個檔案的資料所以必須使用module去執行它
fn main() {
    // StableMatching::main();
    let mut vec:Vec<i32>=Vec::new();
    vec.push(2);
    vec.push(7);
    vec.push(4);
    println!("{} {}",vec[vec.len()-1],vec.len());
    vec.pop();
    println!("{}",vec[vec.len()-1])
}
