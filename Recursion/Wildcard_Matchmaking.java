class Wildcard_Matchmaking {
    public boolean isMatch(String s, String p) {
        // Variation of LCS problem
        int n = s.length();
        int m = p.length();

        boolean[][] dp = new boolean[n + 1][m + 1];

        // Base case: empty string matches empty pattern
        dp[0][0] = true;

        // If pattern is empty -> no non-empty string can match it
        for (int i = 1; i <= n; i++) {
            dp[i][0] = false;
        }

        // Initialize the first row → this represents cases where the string 's' is empty (i = 0)
        for (int j = 1; j <= m; j++) {
            // If the current pattern character is '*'
            // WHY → because '*' can match zero or more characters.
            // So even if the string is empty, '*' can represent "zero characters".
            // Hence, we carry forward the previous result dp[0][j-1]
            // (i.e., if the pattern up to j-1 could match an empty string, 
            // then including another '*' will also match empty string).
            if (p.charAt(j - 1) == '*'){
                dp[0][j] = dp[0][j - 1];
            }
            // Otherwise (if the pattern character is not '*'),
            // WHY → because characters like 'a', 'b', or '?' need at least one character to match.
            // An empty string has no characters to match with, so dp[0][j] = false.
            else{
                dp[0][j] = false;
            }
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                char ch1 = s.charAt(i - 1); // current character from string 's'
                char ch2 = p.charAt(j - 1); // current character from pattern 'p'
                if (ch2 == '?' || ch1 == ch2) {
                    // CASE 1: If pattern character is '?' OR both characters are the same
                    // WHY → '?' can match exactly one character of any type,
                    // or if both characters are identical, it's a direct match.
                    // Therefore, we look diagonally back (i-1, j-1),
                    // meaning: if the previous substring and pattern matched, 
                    // then these current characters also match.
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (ch2 == '*') {
                    // CASE 2: If pattern character is '*'
                    // WHY → '*' can match:
                    //   (a) Zero characters → ignore '*' → dp[i][j-1]
                    //   (b) One or more characters → let '*' absorb one char from 's' → dp[i-1][j]
                    // Hence, we take OR of both possibilities.
                    boolean option1 = dp[i - 1][j];
                    boolean option2 = dp[i][j - 1];
                    dp[i][j] = option1 || option2;
                } else {
                    // CASE 3: If characters don't match and it's not '?' or '*'
                    // WHY → direct mismatch, so this cell is false.
                    dp[i][j] = false;
                }
            }
        }
        // Final answer → whether full string 's' matches full pattern 'p'
        return dp[n][m];
    }

    public static void main(String[] args) {
        Wildcard_Matchmaking matcher = new Wildcard_Matchmaking();
        
        // Test cases
        System.out.println("Testing Wildcard Matching Algorithm:");
        System.out.println("====================================");
        
        // Test case 1: Basic character matching
        String s1 = "adceb";
        String p1 = "*a*b*";
        System.out.println("String: \"" + s1 + "\", Pattern: \"" + p1 + "\"");
        System.out.println("Result: " + matcher.isMatch(s1, p1)); // Expected: true
        System.out.println();
        
        // Test case 2: No match
        String s2 = "adceb";
        String p2 = "*a*c*";
        System.out.println("String: \"" + s2 + "\", Pattern: \"" + p2 + "\"");
        System.out.println("Result: " + matcher.isMatch(s2, p2)); // Expected: false
        System.out.println();
        
        // Test case 3: Question mark matching
        String s3 = "aa";
        String p3 = "a?";
        System.out.println("String: \"" + s3 + "\", Pattern: \"" + p3 + "\"");
        System.out.println("Result: " + matcher.isMatch(s3, p3)); // Expected: true
        System.out.println();
        
        // Test case 4: Multiple wildcards
        String s4 = "cb";
        String p4 = "*a*";
        System.out.println("String: \"" + s4 + "\", Pattern: \"" + p4 + "\"");
        System.out.println("Result: " + matcher.isMatch(s4, p4)); // Expected: false
        System.out.println();
        
        // Test case 5: Empty string with pattern
        String s5 = "";
        String p5 = "*";
        System.out.println("String: \"" + s5 + "\", Pattern: \"" + p5 + "\"");
        System.out.println("Result: " + matcher.isMatch(s5, p5)); // Expected: true
        System.out.println();
        
        // Test case 6: Complex pattern
        String s6 = "abcdef";
        String p6 = "a*c?e*";
        System.out.println("String: \"" + s6 + "\", Pattern: \"" + p6 + "\"");
        System.out.println("Result: " + matcher.isMatch(s6, p6)); // Expected: true
    }
}