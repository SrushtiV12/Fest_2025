class FrogJump {
    public boolean canCross(int[] stones) {
        int n = stones.length;
        for(int i = 1; i<n; i++){
            if(stones[i] - stones[i-1] > i) return false;
        }

        boolean [][] dp = new boolean [n][n+1];
        dp[0][0] = true;

        for(int i = 1; i<n; i++){
            for(int j = 0; j<i; j++){
                int k = stones[i] - stones[j];
                if (k <= n) {
                if ((k - 1 >= 0 && dp[j][k - 1]) || dp[j][k] || (k + 1 <= n && dp[j][k + 1])) {
                dp[i][k] = true;
                if (i == n - 1) return true;
            }
        }

            }
        }

        return false;
    }

    public static void main(String[] args) {
        FrogJump solution = new FrogJump();
        
        System.out.println("Testing Frog Jump Algorithm:");
        System.out.println("============================");
        System.out.println("Problem: A frog starts at stone 0 and wants to reach the last stone.");
        System.out.println("The frog can jump k-1, k, or k+1 units where k is the previous jump.");
        System.out.println("First jump must be 1 unit.\n");
        
        // Test case 1: Successful crossing
        int[] stones1 = {0, 1, 3, 5, 6, 8, 12, 17};
        System.out.println("Test Case 1:");
        System.out.print("Stones: ");
        printArray(stones1);
        boolean result1 = solution.canCross(stones1);
        System.out.println("Can cross: " + result1);
        System.out.println("Expected: true");
        System.out.println("Explanation: Jump sequence: 0→1(1 unit)→3(2 units)→5(2 units)→6(1 unit)→8(2 units)→12(4 units)→17(5 units)");
        System.out.println();
        
        // Test case 2: Cannot cross
        int[] stones2 = {0, 1, 2, 3, 4, 8, 9, 11};
        System.out.println("Test Case 2:");
        System.out.print("Stones: ");
        printArray(stones2);
        boolean result2 = solution.canCross(stones2);
        System.out.println("Can cross: " + result2);
        System.out.println("Expected: false");
        System.out.println("Explanation: Gap from stone 4 to 8 is too large for the frog to jump");
        System.out.println();
        
        // Test case 3: Simple case - two stones
        int[] stones3 = {0, 1};
        System.out.println("Test Case 3:");
        System.out.print("Stones: ");
        printArray(stones3);
        boolean result3 = solution.canCross(stones3);
        System.out.println("Can cross: " + result3);
        System.out.println("Expected: true");
        System.out.println("Explanation: Single jump from 0 to 1 (1 unit)");
        System.out.println();
        
        // Test case 4: Consecutive stones
        int[] stones4 = {0, 1, 2, 3, 4, 5, 6};
        System.out.println("Test Case 4:");
        System.out.print("Stones: ");
        printArray(stones4);
        boolean result4 = solution.canCross(stones4);
        System.out.println("Can cross: " + result4);
        System.out.println("Expected: true");
        System.out.println("Explanation: Can jump with pattern 1,1,1,1,1,1");
        System.out.println();
        
        // Test case 5: Gap too wide at start
        int[] stones5 = {0, 2};
        System.out.println("Test Case 5:");
        System.out.print("Stones: ");
        printArray(stones5);
        boolean result5 = solution.canCross(stones5);
        System.out.println("Can cross: " + result5);
        System.out.println("Expected: false");
        System.out.println("Explanation: First jump must be 1 unit, but stone 1 is at position 2");
        System.out.println();
        
        // Test case 6: Complex successful case
        int[] stones6 = {0, 1, 3, 6, 10, 15, 21};
        System.out.println("Test Case 6:");
        System.out.print("Stones: ");
        printArray(stones6);
        boolean result6 = solution.canCross(stones6);
        System.out.println("Can cross: " + result6);
        System.out.println("Expected: true");
        System.out.println("Explanation: Increasing jump pattern works");
        System.out.println();
        
        // Test case 7: Single stone
        int[] stones7 = {0};
        System.out.println("Test Case 7:");
        System.out.print("Stones: ");
        printArray(stones7);
        boolean result7 = solution.canCross(stones7);
        System.out.println("Can cross: " + result7);
        System.out.println("Expected: true");
        System.out.println("Explanation: Already at the last stone");
        System.out.println();
        
        System.out.println("============================");
        System.out.println("All test cases completed!");
    }
    
    // Helper method to print array
    private static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}