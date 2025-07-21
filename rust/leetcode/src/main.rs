mod longest_valid_parentheses;
fn main() {
    assert_eq!(longest_valid_parentheses::longest_valid_parentheses(String::from("(()")), 2);
    assert_eq!(longest_valid_parentheses::longest_valid_parentheses(String::from(")()())")), 4);
}

