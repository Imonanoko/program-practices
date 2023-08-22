//     0      1     2      3      4
//man:Victor,Wyatt,Xavier,Yancey,Zeus
//woman:Amy,Bertha,Clare,Diane,Erika
//結果應該要是（男：女）=> 0(V):0(A) , 1(W):2(C) , 2(X):1(B) , 3(Y):4(E) , 4(Z):3(D)
//檢查是否都有配對了
//要使用變數當索引值的話需要將型別轉成usize
//.try_into().unwrap()強制轉型別
//64行todo!() 宏是一個在編譯時生成警告的占位符，它不會導致運行時的錯誤，但它提醒你尚未完成某些功能的實現。
//rustc StableMatching.rs 編譯 ./StableMatching 執行
use std::collections::linked_list::LinkedList;
//是否與現任男友分手？
pub fn main(){
    //男生的rank
    let men:[[i32;5];5]=[
        [1,0,3,4,2],
        [3,1,0,2,4],
        [1,4,2,3,0],
        [0,3,2,1,4],
        [1,3,0,4,2]
    ];
    //查看是否有配對了，在list裡面的都是單身
    let mut men_status: LinkedList<i32> = LinkedList::new();
    for i in 0..5{
        men_status.push_back(i);
    }
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
    //將上面那個矩陣變成index為男生value為排名
    let mut Ranking_of_men_among_women:[[i32;5];5]=[[0;5];5];
    for i in 0..5{
        for j in 0..5 {
            let x:usize = women[i][j] as usize;
            Ranking_of_men_among_women[i][x]= j as i32;
        }
    }
    //查看是否有配對了
    let mut women_status:[bool;5] = [false;5];
    //與哪位男生配對
    let mut matching_with_man:[i32;5] = [-1;5];
    //檢查是否還有單身男子
    while !men_status.is_empty() {
        //現在是誰追求，因為pop可能跳出空值所以要預防空值發生。用.unwrap_or(-1)，如果為空值則傳-1出去
        let who:i32 = men_status.pop_back().unwrap_or(-1);
        if who == -1 {
            println!("Error");
            break;
        }
        //男子追求的路上
        loop {
            //目前追求的女孩
            let girl_friend:i32=men[who as usize][men_choice[who as usize] as usize];
            //如果單身
            if !women_status[girl_friend as usize] {
                //將狀態改成現充
                women_status[girl_friend as usize]=true;
                //如果被分手下次從哪個排名找
                men_choice[who as usize]+=1;
                //紀錄女孩跟誰交往
                matching_with_man[girl_friend as usize]=who;
                break;
            }
            //如果有男友
            else{
                //女孩現任男友
                let cur:i32=matching_with_man[girl_friend as usize];
                //如果現任排名比追求者差的話
                if Ranking_of_men_among_women[girl_friend as usize][who as usize]<Ranking_of_men_among_women[girl_friend as usize][cur as usize] {
                    //將現任設定為單身
                    men_status.push_back(cur);
                    //更新女孩交往紀錄
                    matching_with_man[girl_friend as usize]=who;
                    break;
                }
                //如果被分手下次從哪個排名找
                men_choice[who as usize]+=1;
            }
        }
    }
    //輸出誰跟誰在一起，前面男生後面女生
    for i in 0..matching_with_man.len() {
        println!("{}:{}",matching_with_man[i],i);
    }
}