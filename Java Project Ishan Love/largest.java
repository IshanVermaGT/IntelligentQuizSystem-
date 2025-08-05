//Find the largest element in an array
import java.util.*;
public class largest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the size of the array: ");
        int size = sc.nextInt();
        int arr[] = new int[size];
        int max = 0;
        System.out.print("Enter the elements of an array: ");
        for(int i = 0; i < size; i++){
            arr[i] = sc.nextInt();
        }
        //Checking for maximum element of an array
        for (int i = 0; i < size; i++) {
            if(arr[i] > max){
                max = arr[i];
            }
            else{
                continue;
            }
        }
        System.out.println("Maximum element in an array is: " + max);
        sc.close();
    }
}
