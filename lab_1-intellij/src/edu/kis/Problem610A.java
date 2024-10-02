package edu.kis;

import java.util.Scanner;

public class Problem610A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int count = 0;
        for (int i = 1; i < N/4.0; i++) {
            if (i != N/2-i) {
                count++;
            }
        }
        System.out.println(count);
    }
}
