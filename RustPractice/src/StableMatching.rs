//     0      1     2      3      4
//man:Victor,Wyatt,Xavier,Yancey,Zeus
//woman:Amy,Bertha,Clare,Diane,Erika
//結果應該要是（男：女）=> 0(V):0(A) , 1(W):2(C) , 2(X):1(B) , 3(Y):4(E) , 4(Z):3(D)
//檢查是否都有配對了
//要使用變數當索引值的話需要將型別轉成usize
//.try_into().unwrap()強制轉型別
//64行todo!() 宏是一個在編譯時生成警告的占位符，它不會導致運行時的錯誤，但它提醒你尚未完成某些功能的實現。
//rustc StableMatching.rs 編譯 ./StableMatching 執行
//建議用linked list 去處理free man 每次處理就取一個出來，有人變free就加回去linked list 直到linked list 為空
//女性的排名需要按照男性是幾號排也就是index是男生編號value是名次
use std::convert::TryInto;
fn check_match(array: [bool; 5]) ->bool{
    for i in 0..array.len(){
        if !array[i] {
            return true;
        }
    }
    return false;
}
//換誰追求
fn whose_turn(array: [bool; 5]) ->i32{
    for i in 0..array.len(){
        if !array[i] {
            return i.try_into().unwrap();
        }
    }
    return 77;
}
//是否與現任男友分手？
fn is_exchange(cur: i32, propose: i32, woman: usize, array: [[i32; 5]; 5]) ->bool{
    for i in 0..array.len(){
        if array[woman][i]==cur {
            return false;
        }
        if array[woman][i]==propose {
            break;
        }
    }
    return true
}
pub(crate) fn main(){
    //男生的rank
    let men:[[i32;5];5]=[
        [1,0,3,4,2],
        [3,1,0,2,4],
        [1,4,2,3,0],
        [0,3,2,1,4],
        [1,3,0,4,2]
    ];
    //查看是否有配對了
    let mut men_status:[bool;5]=[false;5];
    //計目前選到那一個（指的是排名）
    let mut men_choice:[i32;5]=[0;5];
    //女生的rank
    let women:[[i32;5];5]=[
        [4,0,1,3,2],
        [2,1,3,0,4],
        [1,2,3,4,0],
        [0,4,3,2,1],
        [3,1,4,2,0]
    ];
    //查看是否有配對了
    let mut women_status:[bool;5] = [false;5];
    //與哪位男生配對
    let mut matching_with_man:[i32;5] = [-1;5];
    let mut who:i32;
    let mut who_index:usize;
    let mut match_woman:usize;
    while check_match(men_status) {
        //現在是那位男士選擇
        who = whose_turn(men_status);
        who_index = whose_turn(men_status).try_into().unwrap();
        while !men_status[who_index] {
            //從男生的rank優先序最高的開始找的女士
            let index:usize=men_choice[who_index].try_into().unwrap();
            match_woman=men[who_index][index].try_into().unwrap();
            //如果女生還沒配對
            if !women_status[match_woman]{
                //設定女生與哪個男生配對
                matching_with_man[match_woman]=who;
                //設定女生狀態
                women_status[match_woman]=true;
                //設定男生狀態
                men_status[who_index]=true;
                //當分手時下次的索引值
                men_choice[who_index]+=1;
            }
            else{
                let cur:i32=matching_with_man[match_woman].try_into().unwrap();
                let cur_index:usize=matching_with_man[match_woman].try_into().unwrap();
                //如果要換男友的話
                if is_exchange(cur,who,match_woman,women){
                    //設定兩個男士得狀態
                    men_status[cur_index]=false;
                    men_status[who_index]=true;
                    //更新女士的男友
                    matching_with_man[match_woman]=who;

                }
                //無論是否被拒絕下次索引值都要加一
                men_choice[who_index]+=1;
            }
        }
    }
    for i in 0..matching_with_man.len() {
        println!("{}:{}",matching_with_man[i],i);
    }
}