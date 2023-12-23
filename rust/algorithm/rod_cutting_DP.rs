use std::env;
use std::fs::File;
use std::io::{self, BufRead};
use std::path::Path;
use std::time::Instant;
use std::io::Write;
use std::convert::TryInto;
//用DP計算所有長度的價格
fn extended_buttom_up_cut_rod(p:&Vec<u32>,n:u32)->(Vec<u32>,Vec<u32>){
    //初始化r,s陣列
    let mut r = vec![0; n as usize+ 1];
    let mut s = vec![0; n as usize+ 1];
    for j in 1..=n{
        //初始化價格，令它是最小值
        let mut q = u32::MIN;
        for i in 1..=j{
            //初始化新值
            let new_value:u32;
            //如果i小於p的長度就取它的值
            if i<p.len().try_into().unwrap() {
                new_value = p[i as usize]+r[(j-i) as usize];
            }
            //超過的話就當作0
            else{
                new_value = r[(j-i) as usize];
            }
            //如果新值超過之前的最大值則取代並記錄它的位置
            if q<new_value {
                q=new_value;
                s[j as usize] = i;
            }
        }
        r[j as usize] =q;
    }
    return (r,s);
}
//印出切開的長度
fn print_cut_rod_solution(p:&Vec<u32>,n:u32){
    //讀出切開位子與那個長度的最大價格
    let (_r,s) = extended_buttom_up_cut_rod(&p,n);
    //因為rust的所用權概念所以必須要將n的值複製到另一個變數，不然main的length會被影響
    let mut l = n;
    //計算要怎麼切
    while l>0 {
        let sn = s[l as usize];
        print!("{} ",sn);
        l=l-sn;
    }
}
fn main() -> Result<(), std::io::Error> {
    let args: Vec<String> = env::args().collect();
    //檢查有沒有參數
    if args.len() < 2 {
        eprintln!("請輸入路徑參數");
        return Ok(());
    }

    let path = Path::new(&args[1]);
    //如果打不開路徑的話會出現錯誤訊息並停止
    let file = File::open(&path)?;
    //先建立長度的價格空間
    let mut p = vec![0; 10000];
    //建立讀取的buffer
    let reader = io::BufReader::new(file);

    for line_result in reader.lines() {
        //如果讀不到檔案的話會出現錯誤訊息並停止
        let line = line_result.expect("讀不到檔案");
        //將讀到的數字從字串轉成u32(int)
        let numbers: Vec<u32> = line
            .split_whitespace()
            .filter_map(|word| word.parse().ok())
            .collect();

        if numbers.len() >= 2 {
            //因為rust的index型別是usize所以要將u32轉成usize才能索引
            let index = numbers[0] as usize; 
            if index < p.len() {
                p[index] = numbers[1];
            } else {
                eprintln!("index {} 超出設定範圍", index);
            }
        }
    }
    //儲存輸入的變數
    let mut input = String::new();
    //提示字串
    print!("請輸入長度:");
    //讓提示字串先打印出來，不然提示字串會在輸入之後才顯示
    io::stdout().flush()?;
    // 讀取輸入
    let length: u32 = match io::stdin().read_line(&mut input) {
        Ok(_) => {
            input.trim().parse().expect("請輸入非負整數")
        },
        //處理讀不到的錯誤
        Err(error) => {
            eprintln!("讀取錯誤: {}", error);
            return Err(error);
        },
    };
    //計算執行時間
    let start = Instant::now();
    //執行函數
    let (_r,_s) = extended_buttom_up_cut_rod(&p,length);
    let duration = start.elapsed();
     //印出輸入長度
     println!("Rod length:{}",length);
     //印出最大價格
     println!("Maximum revenue:{}",_r[length as usize]);
     //印出切開的長度組合
     print_cut_rod_solution(&p,length);
     println!();
     //印出執行時間
     println!("Method: DP執行時間 =  {:?} ms", duration.as_millis());
    Ok(())
}
