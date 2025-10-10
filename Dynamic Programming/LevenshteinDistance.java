public class LevenshteinDistance {
    public static int levenshteinDistance(String str1, String str2) {
        int lenStr1 = str1.length();
        int lenStr2 = str2.length();

        int[][] dp = new int[lenStr1 + 1][lenStr2 + 1];

        for (int i = 0; i <= lenStr1; i++) {
            for (int j = 0; j <= lenStr2; j++) {
                if (i == 0) {
                    dp[i][j] = j; // Deletion
                } else if (j == 0) {
                    dp[i][j] = i; // Insertion
                } else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; // No operation
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], // Deletion
                                    Math.min(dp[i][j - 1], // Insertion
                                             dp[i - 1][j - 1])); // Substitution
                }
            }
        }
        return dp[lenStr1][lenStr2];
    }

    public static void main(String[] args) {
        String str1 = "kitten";
        String str2 = "sitting";
        int distance = levenshteinDistance(str1, str2);
        System.out.println("Levenshtein Distance: " + distance);
    }
    
}
