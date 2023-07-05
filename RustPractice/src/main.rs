// mod test;
mod StableMatching2;
mod StableMatching;
use std::time::Instant;
//intellij 編譯器只會讀這個檔案的資料所以必須使用module去執行它
fn main() {
    let start = Instant::now();
    StableMatching::main();
    let duration = start.elapsed();
    println!("V1程式執行時間: {:?}", duration);
    println!("----------------");
    let start = Instant::now();
    StableMatching2::main();
    let duration = start.elapsed();
    println!("V2程式執行時間: {:?}", duration);
    // test::main();

}
