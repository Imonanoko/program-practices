struct Solution;
#[derive(PartialEq, Eq, Clone, Debug)]
pub struct ListNode {
    pub val: i32,
    pub next: Option<Box<ListNode>>,
}

impl ListNode {
    #[inline]
    fn new(val: i32) -> Self {
        ListNode { next: None, val }
    }
}
impl Solution {
    pub fn swap_pairs(mut head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
        let mut ans = Some(Box::new(ListNode{val: 0,next:head}));
        let mut ptr = &mut ans;
        while let Some(mut first) = ptr.as_mut().unwrap().next.take() {
            if let Some(mut second) = first.next.take() {
                first.next = second.next.take();
                second.next = Some(first);
                ptr.as_mut().unwrap().next = Some(second);
                ptr = &mut ptr.as_mut().unwrap().next.as_mut().unwrap().next;
            }else {
                ptr.as_mut().unwrap().next = Some(first);
                break;
            }
        }
        ans.unwrap().next
    }
}
