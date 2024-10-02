package edu.kis;

import java.util.Scanner;

public class Fibo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if(n < 1 || n > 45){
            return;
        }
        int[] tab = new int[n];
        tab[0] = 0;
        if(n > 1){
            tab[1] = 1;
        }
        for(int i = 2; i < n; i++) {
            tab[i] = tab[i-1] + tab[i-2];
        }
        for(int i = 0; i < n; i++){
            System.out.println(tab[i]);
        }
    }
}
