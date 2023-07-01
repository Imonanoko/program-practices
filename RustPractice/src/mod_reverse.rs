use std::io;
//利用Q[]是每一次的商 cur[]是目前狀態 pre[]是前一個狀態 cur=[pre[1],pre[0]-pre[1]*Q[last]]->Q.pop()
// 直到結束就能在cur[1]找到inverse
fn mod_inverse(find:i32,module:i32)->i32{
    //將每次結果的商存起來方便之後使用
    let mut quotient_list:Vec<i32>=Vec::new();
    //被除數
    let mut dividend:i32=module;
    //除數
    let mut divisor:i32=find;
    //商
    let mut quotient:i32=dividend/divisor;
    //餘數
    let mut remainder:i32=dividend%divisor;
    quotient_list.push(quotient);
    //反覆做直到餘數等於1
    while remainder != 1 {
        dividend=divisor;
        divisor=remainder;
        quotient=dividend/divisor;
        remainder=dividend%divisor;
        quotient_list.push(quotient);
        if remainder == 0 {
            return -1;
        }
        if remainder == 1 {
            break;
        }
    }
    let length:usize=quotient_list.len();
    //前一個狀態
    let mut pre:[i32;2]=[1,-1*quotient_list[length-1]];
    //目前狀態
    let mut cur:[i32;2]=[1,-1*quotient_list[length-1]];

    //需要計算過程的話可以看這邊
    // println!("{} {}",cur[0],cur[1]);

    //用完就丟
    quotient_list.pop();
    while quotient_list.len() !=0 {
        //用上面所說的算式去計算值
        let length:usize=quotient_list.len();
        cur[0] = pre[1];
        cur[1] = pre[0]-pre[1]*quotient_list[length-1];

        //需要計算過程的話可以看這邊
        // println!("{} {}",cur[0],cur[1]);

        quotient_list.pop();
        pre[0]=cur[0];
        pre[1]=cur[1];
    }
    if cur[1]<0 {
        cur[1]+=module;
    }
    return cur[1];
}
fn main(){
    //讀取要找的值
    println!("Please enter the value for which you want to find the modular multiplicative inverse: ");
    let mut input = String::new();
    io::stdin()
        .read_line(&mut input)
        .expect("An error occurred while reading the input.");
    //將輸入轉成整數
    let find: i32 = match input.trim().parse() {
        Ok(n) => n,
        Err(_) => {
            println!("The input is not a valid integer.");
            return;
        }
    };
    //讀取要mod多少
    println!("Please enter the value for which you want to find the modular multiplicative inverse: ");
    let mut input = String::new();
    io::stdin()
        .read_line(&mut input)
        .expect("An error occurred while reading the input.");
    //將輸入轉成整數
    let module: i32 = match input.trim().parse() {
        Ok(n) => n,
        Err(_) => {
            println!("The input is not a valid integer.");
            return;
        }
    };
    println!("The modular multiplicative inverse of {} modulo {} is {}.",find,module,mod_inverse(find,module));
}