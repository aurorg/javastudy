package twentytwo;

import java.util.Scanner;

public class Gcd {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter first integer: ");
        int m =input.nextInt();
        System.out.print("Enter second integer: ");
        int n = input.nextInt();
        System.out.println("The greatest common divisor for " + m + " and " + n + " is " + gcd(m,n));

    }
    public static int gcd(int m,int n){
        int gcd =1;

        if(m%n==0) return n;
        for(int i =n/2; i>=1;i--){
            if(m%i==0 && n%i==0){
                gcd =i;
                break;
            }
        }
        return gcd;

    }
}
