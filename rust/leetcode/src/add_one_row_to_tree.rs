// Definition for a binary tree node.
#[derive(Debug, PartialEq, Eq)]
pub struct TreeNode {
  pub val: i32,
  pub left: Option<Rc<RefCell<TreeNode>>>,
  pub right: Option<Rc<RefCell<TreeNode>>>,
}

impl TreeNode {
  #[inline]
  pub fn new(val: i32) -> Self {
    TreeNode {
      val,
      left: None,
      right: None
    }
  }
}
struct Solution;
use std::rc::Rc;
use std::cell::RefCell;

type Node = Option<Rc<RefCell<TreeNode>>>;

impl Solution 
{
    pub fn add_one_row(root: Node, val: i32, depth: i32) -> Node
    {
        fn new_node(val: i32, left: Node, right: Node) -> Node
        {
            Some(Rc::new(RefCell::new(TreeNode{ val: val, left: left, right: right })))
        }
        
        // this helper function recursively performs DFS until
        // it reaches a target depth; there, a new row is added
        fn helper(node: &Node, val: i32, depth: i32, d: i32)
        {
            if let Some(node) = node
            {
                if d+1 == depth
                {
                    let mut node = node.borrow_mut();

                    node.left  = new_node(val, node.left.take(), None);
                    node.right = new_node(val, None, node.right.take());
                }
                else
                {
                    helper(&node.borrow().left,  val, depth, d+1);
                    helper(&node.borrow().right, val, depth, d+1);
                }
            }
        }

        if depth == 1
        {
            // [1] marginal case
            new_node(val, root, None)
        }
        else 
        {
            // [2] typical case
            helper(&root, val, depth, 1);
            root
        }
    }
}