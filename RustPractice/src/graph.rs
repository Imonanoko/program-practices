struct tree {
    node: i32,
    children: Vec<tree>,
}

impl tree {
    fn add_children(&mut self, child: tree) {
        self.children.push(child);
    }
    fn print_tree_graph(&self) {
        print!("{} ", self.node);
        if self.children.len() > 0 {
            for i in 0..self.children.len() {
                self.children[i].print_tree_graph();
                println!();
            }
        }
    }
}
   //           1
   //   2               3
   // 4    5
pub fn main() {
    // 建構一個樹狀結構
    let mut root = tree {
        node: 1,
        children: Vec::new(),
    };

    let child1 = tree {
        node: 2,
        children: Vec::new(),
    };

    let child2 = tree {
        node: 3,
        children: Vec::new(),
    };

    root.add_children(child1);
    root.add_children(child2);

    let grandchild1 = tree {
        node: 4,
        children: Vec::new(),
    };

    let grandchild2 = tree {
        node: 5,
        children: Vec::new(),
    };

    root.children[0].add_children(grandchild1);
    root.children[0].add_children(grandchild2);

    // 列印樹
    root.print_tree_graph();
}