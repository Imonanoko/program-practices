use std::collections::HashSet;

pub struct Solution;
impl Solution {
    pub fn is_valid_sudoku(board: Vec<Vec<char>>) -> bool {
        let mut rows:[HashSet<char>;9] = Default::default();
        let mut cols:[HashSet<char>;9] = Default::default();
        let mut blocks:[HashSet<char>;9] = Default::default();
        let mut number_of_rows:[usize;9] = [0;9];
        let mut number_of_cols:[usize;9] = [0;9];
        let mut number_of_blocks:[usize;9] = [0;9];
        for i in 0..9 {
            for j in 0..9 {
                let chr = board[i][j];
                if chr == '.'{
                    continue;
                }
                let block_index = i/3*3+j/3;
                rows[i].insert(chr);
                cols[j].insert(chr);
                blocks[block_index].insert(chr);
                number_of_rows[i] +=1;
                number_of_cols[j] +=1;
                number_of_blocks[block_index]+=1;
            }
        }
        for i in 0..9 {
            if number_of_rows[i] != rows[i].len() || number_of_cols[i] != cols[i].len()||number_of_blocks[i] != blocks[i].len(){
                return false;
            }
        }
        true
    }
}