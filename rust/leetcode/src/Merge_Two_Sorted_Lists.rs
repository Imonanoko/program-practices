struct Solution;
// Definition for singly-linked list.
#[derive(PartialEq, Eq, Clone, Debug)]
pub struct ListNode {
  pub val: i32,
  pub next: Option<Box<ListNode>>
}

impl ListNode {
  #[inline]
  fn new(val: i32) -> Self {
    ListNode {
      next: None,
      val
    }
  }
}
impl Solution {
    pub fn merge_two_lists(list1: Option<Box<ListNode>>, list2: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
        let mut l1 = &list1;
        let mut l2 = &list2;
        let mut ans = Some(Box::new(ListNode::new(0)));
        let mut tail = ans.as_mut().unwrap();
        while l1.is_some() || l2.is_some() {
            match (l1,l2) {
                (Some(n1),Some(n2))=>{
                    if n1.val < n2.val {
                        tail.next = Some(Box::new(ListNode::new(n1.val)));
                        tail = tail.next.as_mut().unwrap();
                        l1 = &n1.next;
                    }else {
                        tail.next = Some(Box::new(ListNode::new(n2.val)));
                        tail = tail.next.as_mut().unwrap();
                        l2 = &n2.next;
                    }
                }
                (Some(n1),None)=>{
                    tail.next = Some(n1.clone());
                    break;
                }
                (None,Some(n2))=>{
                    tail.next = Some(n2.clone());
                    break;
                }
                _=>{
                    break;
                }
            }
        }
        ans.unwrap().next
    }
}