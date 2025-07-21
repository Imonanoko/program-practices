use std::collections::VecDeque;
pub struct Solution;
impl Solution {
    pub fn shortest_path_binary_matrix(grid: Vec<Vec<i32>>) -> i32 {
        let n = grid.len() - 1;
        let dirs = [
            (-1, -1),
            (-1, 0),
            (-1, 1),
            (0, -1),
            (0, 1),
            (1, -1),
            (1, 0),
            (1, 1),
        ];
        if grid[0][0] == 1 || grid[n][n] == 1 {
            return -1;
        }
        let mut grid_count: Vec<Vec<i32>> = vec![vec![0; n+1]; n+1];
        grid_count[0][0] = 1;
        let mut queue: VecDeque<(i32, i32)> = VecDeque::new();
        queue.push_back((0, 0));
        while let Some((x, y)) = queue.pop_front() {
            let step = grid_count[x as usize][y as usize];
            for (dx, dy) in dirs {
                let nx = x + dx;
                let ny = y + dy;
                if nx < 0 || ny < 0 || nx > n as i32 || ny > n as i32 || grid[nx as usize][ny as usize] == 1 {
                    continue;
                }
                let ux = nx as usize;
                let uy = ny as usize;
                if grid_count[ux][uy] == 0 {
                    grid_count[ux][uy] = step + 1;
                    if ux == n && uy == n {
                        return grid_count[ux][uy];
                    }
                    queue.push_back((ux as i32,uy as i32));
                }
            }
        }
        let ans = grid_count[n][n];
        if ans == 0 {
            return -1;
        }
        ans
    }
}
