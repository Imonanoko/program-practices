pub struct Solution;
use std::collections::HashMap;
impl Solution {
    pub fn roman_to_int(s: String) -> i32 {
        let mut ans = 0;
        let map = HashMap::from([
            ('I',1),
            ('V',5),
            ('X',10),
            ('L',50),
            ('C',100),
            ('D',500),
            ('M',1000),

        ]);
        let mut cur:char = 'I';
        for roman in s.chars().rev() {
            match roman {
                'I' => {
                    if map.get(&cur) > map.get(&roman) {
                        ans -= 1;
                    }
                    else {
                        ans += 1;
                        cur = roman;
                    }
                },
                'V' => {
                    ans += 5;
                    cur = roman;
                },
                'X' => {
                    if map.get(&cur) > map.get(&roman) {
                        ans -= 10;
                    }
                    else {
                        ans += 10;
                        cur = roman;
                    }
                },
                'L' => {
                    ans += 50;
                    cur = roman;
                },
                'C' => {
                    if map.get(&cur) > map.get(&roman) {
                        ans -= 100;
                    }
                    else {
                        ans += 100;
                        cur = roman;
                    }
                },
                'D' => {
                    ans += 500;
                    cur = roman;
                },
                'M' => {
                    ans += 1000;
                    cur = roman;
                },
                _ => return -1,
            }
        }
        ans
    }
}