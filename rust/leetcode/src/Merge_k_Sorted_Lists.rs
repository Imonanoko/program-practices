use std::i32;

struct Solution;
// Definition for singly-linked list.
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
  pub fn merge_k_lists(mut lists: Vec<Option<Box<ListNode>>>) -> Option<Box<ListNode>> {
      let mut res = Some(Box::new(ListNode::new(0)));
      let mut tail = res.as_mut().unwrap();
      while lists.len() != 0 {
        let mut min = i32::MAX;
        let mut min_index = -1;
        for (i, list) in lists.iter().enumerate() {
          if let Some(node) = list {
              if node.val < min {
                min = node.val;
                min_index = i as i32;
              }
          } 
        }
        if min_index == -1 {
          return res.unwrap().next;
        }
        tail.next = Some(Box::new(ListNode::new(min)));
        tail = tail.next.as_mut().unwrap();
        
        if let Some(node) = lists[min_index as usize].take() {
          match node.next {
              Some(node_next)=>{
                lists[min_index as usize] = Some(node_next);
              }
              None=>{
                lists.remove(min_index as usize);
              }
          }
        }
      }

      res.unwrap().next
  }
}
