//Check if the number is prime
//Check for palindrome string
//Find the largest element in an array
import java.util.*;
public class Prime{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean prime = true;
        System.out.print("Enter the number: ");
        int n = sc.nextInt();
        for(int i = 2; i < 10; i++){
            if(n == i){
                continue;
            }
            if(n % i == 0){
                prime = false;
                break;
            }
        } 
        if(prime){
            System.out.println("Number is prime.");
        }
        else{
            System.out.println("Number is not prime.");
        }
        sc.close();
    }
}