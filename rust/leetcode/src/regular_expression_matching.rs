pub struct Solution;
impl Solution {
    pub fn is_match(s: String, p: String) -> bool {

        fn char_is_match(s_chars: &[u8],p_chars: &[u8])->bool{
            if p_chars.is_empty() {
                return s_chars.is_empty();
            }
            let first_match = !s_chars.is_empty() && (*p_chars.get(0).unwrap() == b'.' || p_chars.get(0) == s_chars.get(0));
            if p_chars.len() > 1 && *p_chars.get(1).unwrap() == b'*' {
                return char_is_match(s_chars, &p_chars[2..])||
                    (
                        first_match&&
                        char_is_match(&s_chars[1..], p_chars)
                    );
            }else {
                return first_match && char_is_match(&s_chars[1..], &p_chars[1..]);
            }
        }
        let s_chars = s.as_bytes();
        let p_chars = p.as_bytes();
        return char_is_match(p_chars, s_chars);
    }
}