import java.util.Stack;

class BasicCalculator {
    public int calculate(String s) {
        int length = s.length();
        int sign = 1;
        int ans = 0;
        int currNo = 0;
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < length; i++) {
            if (Character.isDigit(s.charAt(i))) {
                currNo = s.charAt(i) - '0';
                while (i + 1 < length && Character.isDigit(s.charAt(i + 1))) {
                    currNo = currNo * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                currNo = currNo * sign;
                ans += currNo;
                currNo = 0;
                sign = 1;
            } else if (s.charAt(i) == '+') {
                sign = 1;
            } else if (s.charAt(i) == '-') {
                sign = -1;
            } else if (s.charAt(i) == '(') {
                stack.push(ans);
                stack.push(sign);
                ans = 0;
                sign = 1;
            } else if (s.charAt(i) == ')') {
                int prevSign = stack.pop();
                ans = prevSign * ans;
                int precAns = stack.pop();
                ans = precAns + ans;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        BasicCalculator calculator = new BasicCalculator();
        
        System.out.println("Testing Basic Calculator Algorithm:");
        System.out.println("===================================");
        System.out.println();
        
        // Test case 1: Simple addition
        String expr1 = "1 + 1";
        System.out.println("Expression: \"" + expr1 + "\"");
        System.out.println("Result: " + calculator.calculate(expr1));
        System.out.println("Expected: 2");
        System.out.println();
        
        // Test case 2: Subtraction
        String expr2 = " 2-1 + 2 ";
        System.out.println("Expression: \"" + expr2 + "\"");
        System.out.println("Result: " + calculator.calculate(expr2));
        System.out.println("Expected: 3");
        System.out.println();
        
        // Test case 3: Parentheses
        String expr3 = "(1+(4+5+2)-3)+(6+8)";
        System.out.println("Expression: \"" + expr3 + "\"");
        System.out.println("Result: " + calculator.calculate(expr3));
        System.out.println("Expected: 23");
        System.out.println();
        
        // Test case 4: Nested parentheses
        String expr4 = "2-(5-6)";
        System.out.println("Expression: \"" + expr4 + "\"");
        System.out.println("Result: " + calculator.calculate(expr4));
        System.out.println("Expected: 3");
        System.out.println();
        
        // Test case 5: Multiple nested parentheses
        String expr5 = "(1+(2+(3+(4+5))))";
        System.out.println("Expression: \"" + expr5 + "\"");
        System.out.println("Result: " + calculator.calculate(expr5));
        System.out.println("Expected: 15");
        System.out.println();
        
        // Test case 6: Complex expression with spaces
        String expr6 = " ( 10 + ( 5 - 3 ) ) + 8 ";
        System.out.println("Expression: \"" + expr6 + "\"");
        System.out.println("Result: " + calculator.calculate(expr6));
        System.out.println("Expected: 20");
        System.out.println();
        
        // Test case 7: Negative numbers
        String expr7 = "-(3+(4+5))";
        System.out.println("Expression: \"" + expr7 + "\"");
        System.out.println("Result: " + calculator.calculate(expr7));
        System.out.println("Expected: -12");
        System.out.println();
        
        // Test case 8: Large numbers
        String expr8 = "100 + 200 - (50 + 30)";
        System.out.println("Expression: \"" + expr8 + "\"");
        System.out.println("Result: " + calculator.calculate(expr8));
        System.out.println("Expected: 220");
        System.out.println();
        
        // Test case 9: Single number
        String expr9 = "42";
        System.out.println("Expression: \"" + expr9 + "\"");
        System.out.println("Result: " + calculator.calculate(expr9));
        System.out.println("Expected: 42");
        System.out.println();
        
        System.out.println("===================================");
        System.out.println("All test cases completed!");
    }
}