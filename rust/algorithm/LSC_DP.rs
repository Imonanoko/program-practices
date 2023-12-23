use std::env;
use std::fs::File;
use std::io::{self, BufRead};
use std::path::Path;
use std::time::Instant;
//用dp去找最長子字串
fn lcs_dp(x: &str, y: &str,m:usize,n:usize) ->(Vec<Vec<u32>>,Vec<Vec<u32>>){
    //將x,y字串的每個字都切開存在vector，加快搜尋
    let x_chars: Vec<char> = x.chars().collect();
    let y_chars: Vec<char> = y.chars().collect();
    //將c初始化並全部設定為0
    let mut c = vec![vec![0; n + 1]; m + 1];
    //將c初始化並全部設定為0,0表示無1是上2是左3是左上方
    let mut b = vec![vec![0; n + 1]; m + 1];
    //遍歷每個字元
    for i in 1..=m as usize {
        for j in 1..=n as usize {
            //一樣的話表示這兩個會對應，所以是不包含現在搜尋到的自元的最大長度加一
            if x_chars[i-1]==y_chars[j-1] {
                c[i][j] = c[i-1][j-1]+1;
                b[i][j] = 3;
            }
            //如果上面的值比左邊大就將上面的值給現在的索引，並設定值是從哪邊來的
            else if c[i-1][j] >= c[i][j-1]{
                c[i][j] = c[i-1][j];
                b[i][j] = 1;
            }
            //如果左邊的值比上面大就將左邊的值給現在的索引，並設定值是從哪邊來的
            else{
                c[i][j] = c[i][j-1];
                b[i][j] = 2;
            }
        }
    }
    //回傳c跟b得vector
    (c,b)
}
//印出最長子字串
fn print_lcs(b: &Vec<Vec<u32>>, x: &str, i: usize, j: usize) {
    //如果i或j是0表示子字串為空
    if i == 0 || j == 0 {
        return;
    }
    //如果指向左上方表示這個索引是子字串的一部份，所以要印出來然後移動到左上方繼續索引
    if b[i][j] == 3 {
        print_lcs(b, x, i - 1, j - 1);
        print!("{}", x.chars().nth(i - 1).unwrap());
    } 
    //如果指向上方表示這個索引不是子字串的一部份，所以移動到上面繼續找其他子字串
    else if b[i][j] == 1 {
        print_lcs(b, x, i - 1, j);
    } 
    //如果指向左方表示這個索引不是子字串的一部份，所以移動到左邊繼續找其他子字串
    else {
        print_lcs(b, x, i, j - 1);
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
    //建立讀取的buffer
    let reader = io::BufReader::new(file);
    //設置一個變數能夠知道讀到第幾行
    let mut line_number = 0;
    //存取第一筆資料的變數
    let mut s1 = String::new();
    //存取第二筆資料的變數
    let mut s2 = String::new();
    //每行讀取
    for line in reader.lines() {
        //行數+1
        line_number += 1;
        //檢查有沒有讀到那行的資料
        let line = line?;
        //把第二行的字串給S1
        if line_number == 2 {
            s1 = line;
        } //把第二行的字串給S1
        else if line_number == 4 {
            s2 = line;
            break;
        }
    }
    //計算執行時間
    let start = Instant::now();
    //執行函數，並讀取c，b的值
    let (c,b) = lcs_dp(&s1, &s2,s1.len() ,s2.len());
    let duration = start.elapsed();
    //印出最大長度
    println!("LCS長度:{}",c[s1.len()][s2.len()]);
    //印出最大長度的字串
    print_lcs(&b,&s1,s1.len() ,s2.len());
    //換行
    println!();
    //印出函數執行時間
    println!("Method: DP執行時間 =  {:?} ms", duration.as_millis());
    Ok(())
}