package beakjoon.dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _Q1912_sequenceSums {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n];
        int[] sums = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        sums[0] = nums[0];
        int max = sums[0];

        for (int i = 1; i < n; i++) {
            sums[i] = Math.max(sums[i - 1] + nums[i], nums[i]);
            max = Math.max(sums[i], max);
        }
        System.out.println(max);
    }
}