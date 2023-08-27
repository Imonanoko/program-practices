#[derive(PartialEq, Eq, Clone, Debug)]
pub struct ListNode {
    pub val: i32,
    pub next: Option<Box<ListNode>>,
}

impl ListNode {
    #[inline]
    fn new(val: i32) -> Self {
        ListNode {
            next: None,
            val,
        }
    }
}
//Box是用來管理記憶體用的如果不需要的時候會自動釋放，所以下面的程式碼其實是可以不用Box的
fn add_two_numbers(
    l1: Option<Box<ListNode>>,
    l2: Option<Box<ListNode>>,
) -> Option<Box<ListNode>> {
    let mut dummy_head = ListNode::new(0);
    let mut p = &mut dummy_head;
    let mut carry = 0;

    let mut l1 = l1;
    let mut l2 = l2;
    //is_some()用於檢查是否裡面有值，有的畫返回true
    while l1.is_some() || l2.is_some() {
        let mut sum = carry;

        if let Some(node) = l1 {
            sum += node.val;
            l1 = node.next;
        }

        if let Some(node) = l2 {
            sum += node.val;
            l2 = node.next;
        }

        carry = sum / 10;
        p.next = Some(Box::new(ListNode::new(sum % 10)));
        //as_mut()將 Option 轉換為對內部值的可變引用
        //unwrap()取出 Option 中的值，如果是 Some 的話。如果 Option 是 None，則 unwrap() 會引發 panic。在這裡，我們確保在 Some 的情況下使用 unwrap() 是安全的
        //保證下一個一定有值
        p = p.next.as_mut().unwrap();
    }

    if carry > 0 {
        p.next = Some(Box::new(ListNode::new(carry)));
    }

    dummy_head.next
}
pub fn main() {
    // 創建兩個鏈結串列
    let l1 = Some(Box::new(ListNode::new(2)));
    let l2 = Some(Box::new(ListNode::new(5)));

    // 呼叫 add_two_numbers 函式
    let result = add_two_numbers(l1, l2);

    // 輸出結果
    println!("{:?}", result);
}


