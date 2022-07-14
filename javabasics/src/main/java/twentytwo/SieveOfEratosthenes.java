package twentytwo;

import java.util.Scanner;

public class SieveOfEratosthenes {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.print("Find all prime numbers <=n,enter n: ");
        int n = input.nextInt();

        boolean[] primes =new boolean[n+1];

        for(int i =0; i<primes.length;i++){
            primes[i] = true;
        }
        for(int i =2;i<=n/2;i++){
            if(primes[i]){
               for(int j =i;j<=n/i;j++){
                   primes[i*j] = false;
            }
        }
    }
        final int NUMBER_PER_LINE =10;
        int count =0;
        for(int i=2;i<primes.length;i++){
            if(primes[i]){
                count++;
                if(count % NUMBER_PER_LINE ==0){
                    System.out.printf("%7d\n",i);
                }else{
                    System.out.printf("%7d",i);
                }
            }
        }
        System.out.println("\n" + count + " prime(s) less than or equal to " + n);
    }
}
