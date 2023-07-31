use std::time::Instant;
//example library::time(StableMatching::main,StableMatching2::main);
pub fn time(func1:fn(), func2:fn()){
    let start = Instant::now();
    func1();
    let duration = start.elapsed();
    println!("function1程式執行時間: {:?}", duration);
    println!("----------------");
    let start = Instant::now();
    func2();
    let duration = start.elapsed();
    println!("function2程式執行時間: {:?}", duration);
}