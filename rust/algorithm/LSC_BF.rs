use std::convert::TryInto;
use std::env;
use std::fs::File;
use std::io::{self, BufRead};
use std::mem;
use std::path::Path;
use std::time::Instant;
//用全部遍歷的方式去找最長子字串
fn lcs_bf(mut s1: String, mut s2: String) {
    //存取最後的最大長度
    let mut length: u128 = 0;
    //存取最後的最長子字串
    let mut lcs_str: String = "".to_string();
    //存取最大長度跟最小長度
    let len_min: u128;
    let len_max: u128;
    //將最小的字串鎖定在s1方便後續處理
    if s1.len() > s2.len() {
        //如果s1比較常讓S1跟S2交換
        mem::swap(&mut s1, &mut s2);
    }
    //確定S1最短後就設定他們的長度
    len_min = s1.len() as u128;
    len_max = s2.len() as u128;
    //長度為n的字串會有2的長度次方種組合，利用位移的方式去算次方
    let mask_limit: u128 = 1 << len_min;
    //算出所有組合
    for mask in 1..mask_limit {
        //將每一次的組合暫存在sub_str，以遍後面比較
        let mut sub_str = String::new();

        for i in 0..len_min {
            //如果mask的第i位是1的話就代表那個字是需要被選的
            if mask & (1 << i) != 0 {
                //將s1的第i個字元加到sub_str
                sub_str.push(s1.chars().nth(i.try_into().unwrap()).unwrap());
            }
        }
        //紀錄目前的自元對應到s2的index以便下一個字元重這個index開始找s2，確定不會找到前面的
        let mut cur_index = 0;
        //比較找到的組合是否也符合S2'out:是一個LABEL方便後面確定s2沒有這個子字串的時候直接跳出迴圈
        'out: for (idx, i) in sub_str.chars().enumerate() {
            //因為後面的自元不會找到上一個字元的前面所以會從cur_index開始找
            for j in cur_index..len_max {
                //如果子字串第i個字元跟s2的第j個字元一樣將cur_index改成j
                if s2.chars().nth(j.try_into().unwrap()) == Some(i) {
                    cur_index = j;
                    break;
                }
                //如果找到s2的最後面了表示這個組合不是s2的子字串，所以直接跳出迴圈找下一個組合
                if j == (len_max - 1).try_into().unwrap()
                    && s2.chars().nth(j.try_into().unwrap()) != Some(i)
                {
                    break 'out;
                }
            }

            // 检查是否是 sub_str 中的最后一个字符，如果符合表示這個組合是s2的子字串
            if idx == sub_str.len() - 1 {
                //跟前面找到的子字串比較大小比較大的話就將長度跟子字串體換
                if length < sub_str.len().try_into().unwrap() {
                    length = sub_str.len() as u128;
                    lcs_str = sub_str.clone();
                }
            }
        }
    }
    //印出最大長度
    println!("LCS長度:{}", length);
    //印出最大長度的字串
    println!("LCS String:{}", lcs_str);
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
        }
        //把第二行的字串給S1
        else if line_number == 4 {
            s2 = line;
            break;
        }
    }
    //計算執行時間
    let start = Instant::now();
    //執行函數
    lcs_bf(s1, s2);
    let duration = start.elapsed();
    println!("Method: BF執行時間 =  {:?} ms", duration.as_millis());
    Ok(())
}
