//Check for palindrome string
import java.util.*;
public class string{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter any string: ");
        String S = sc.next();
        S = S.toLowerCase();
        int i = 0;
        int j = S.length()-1;
        boolean palindrome = true;
        while(i < j){
            if(S.charAt(i) != S.charAt(j)){
                palindrome = false;
                break;
            }
            i++;
            j--;
        }
        if(palindrome){
            System.out.println("String is Palindrome.");
        }
        else{
            System.out.println("String is not Palindrome.");
        }
        sc.close();
    }
}
