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
struct Solution;
impl Solution {
    pub fn reverse_list(head: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
        if let Some(list_node) = head {
          let mut answer = ListNode::new(list_node.val);
          let mut cur_node = list_node.next;
          while let Some(node) = &cur_node {
              let mut new_node = ListNode::new(node.val);
              new_node.next = Some(Box::new(answer));
              answer  = new_node;
              cur_node = cur_node.unwrap().next;
          }

          return Some(Box::new(answer));
        }else{
          return None;
        }
    }
}