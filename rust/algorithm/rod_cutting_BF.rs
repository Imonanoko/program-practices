use std::env;
use std::fs::File;
use std::io::{self, BufRead};
use std::path::Path;
use std::time::Instant;
use std::io::Write;
use std::collections::LinkedList;
//將p借用給這個function(不能修改p) p才不會在主函數消失(詳情情查閱rust所用權)，n是需求的長度，cut_list存取切開的長度，這邊使用了借用&並賦予修改的權限讓這個函式可以修改這個記憶體位置
fn cut_rod(p:&Vec<u32>,n:u32,cut_list:&mut LinkedList<u32>)->u32{
    //如果長度為就回傳0
    if n==0 {
        return 0;
    }
    //建立q存取最大的價格，先假設為這個type最小的值
    let mut q = u32::MIN;
    //將每個組合都測試一次看哪個組合最大
    for i in 1..=n as usize{
        //建立一個新的LinkedList去保存計入遞迴後切開的長度
        let mut temp_list = LinkedList::new();
        let new_value = p[i] + cut_rod(p, n - i as u32, &mut temp_list);
        //只要有新的值大於之前的最大
        if q < new_value {
            //將q換成新的最大值
            q = new_value;
            //清除之前的切個紀錄
            cut_list.clear();
            //換成新的切割紀錄
            cut_list.push_back(i as u32);
            //加入進入遞迴後的切割長度
            cut_list.append(&mut temp_list);
        }
    }
    //輸出最大的價格，因為切割的長度是透過記憶體修改的方式所以不用回傳，在main可以直接讀取
    q
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
    //存取切開的長度，因為是空間是動態的並且只需要加入跟搜尋所以用LinkedList
    let mut cut_list:LinkedList<u32> = LinkedList::new();
    //計算執行時間
    let start = Instant::now();
    //執行函數
    let ans:u32 = cut_rod(&p,length,&mut cut_list);
    let duration = start.elapsed();
    //處理LinkedList的資料，使其變成長度間以空格隔開
    let mut cuts = String::new();
    for (i, cut) in cut_list.iter().enumerate() {
        if i > 0 {
            cuts.push(' ');  // 在元素之间添加空格
        }
        cuts.push_str(&cut.to_string());
    }
    //印出輸入長度
    println!("Rod length:{}",length);
    //印出最大價格
    println!("Maximum revenue:{}",ans);
    //印出切開的長度組合
    println!("Cuts:{}",cuts);
    //印出執行時間
    println!("Method: BF執行時間 =  {:?} ms", duration.as_millis());
    Ok(())
}
