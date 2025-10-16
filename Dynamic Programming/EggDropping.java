public class EggDropping {

    // Function to get minimum number of trials
    // needed in worst case with n eggs and k floors
    public static int eggDrop(int eggs, int floors) {
        int[][] dp = new int[eggs + 1][floors + 1];

        // Base cases:
        // 1 floor → 1 trial needed
        // 0 floor → 0 trials needed
        for (int i = 1; i <= eggs; i++) {
            dp[i][0] = 0;
            dp[i][1] = 1;
        }

        // 1 egg → we need to try all floors (worst case)
        for (int j = 1; j <= floors; j++) {
            dp[1][j] = j;
        }

        // Fill rest of the entries using optimal substructure property
        for (int i = 2; i <= eggs; i++) {
            for (int j = 2; j <= floors; j++) {
                dp[i][j] = Integer.MAX_VALUE;

                // Consider dropping from each floor x and choose min of worst cases
                for (int x = 1; x <= j; x++) {
                    int breaks = dp[i - 1][x - 1];    // Egg breaks → check below floors
                    int survives = dp[i][j - x];      // Egg survives → check above floors
                    int trials = 1 + Math.max(breaks, survives);  // Worst case

                    dp[i][j] = Math.min(dp[i][j], trials);
                }
            }
        }

        return dp[eggs][floors];
    }

    // Driver method
    public static void main(String[] args) {
        int eggs = 2;
        int floors = 10;

        System.out.println("Minimum number of trials in worst case with "
                + eggs + " eggs and " + floors + " floors is "
                + eggDrop(eggs, floors));
    }
}
