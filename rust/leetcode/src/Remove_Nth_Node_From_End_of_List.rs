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
    pub fn remove_nth_from_end(head: Option<Box<ListNode>>, n: i32) -> Option<Box<ListNode>> {
        let mut lenght = 0;
        let mut ptr = &head;
        while let Some(node) = ptr {
            lenght+=1;
            ptr=&node.next;
        };
        if lenght == n {
            return head.unwrap().next;
        }
        let mut ans = Some(Box::new(ListNode {
            val: 0,
            next: head,
        }));
        let mut current = &mut ans;
        for _ in 0..(lenght-n) {
            if let Some(ref mut _node) = current.as_mut().unwrap().next {
                current = &mut current.as_mut().unwrap().next
            }
        }
        if let Some(ref mut node) = current {
            node.next = node.next.as_mut().unwrap().next.take();
        }
        ans.unwrap().next
    }
}