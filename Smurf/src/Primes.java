import java.util.Scanner;

public class Primes {
  
  //our little static utility method to wrap Scanner
  /**
   * FetchNumber
   * presents the user with a string and 
   * expects to receive a number from the system.in stream.
   * 
   * @param String askNumber
   * @return int
   */
  public static int FetchNumber(String askNumber) {
    System.out.print(askNumber);
    Scanner input = new Scanner(System.in);
    int num = 0;
    //try ... catch block to assign num to Scanner's nextInt
    //if the user does not input type int we recursively ask for an int.
    try {
      num = input.nextInt();
    } catch (Exception e) {
      return FetchNumber("You did not enter a number.");
    }
    return num;
  }
  
  /**
   * IsPrime
   * runs through a typical algorithm to determine if the given int is prime.
   * we're using the square of the num as the upper limit. using the square
   * of the number gives us a nice middle point to the divisors of the number. 
   * 
   * @param num
   * @return boolean 
   */
  public static boolean IsPrime(int num) {
    
    //fail fast if num is divisible by 2 
    if (num % 2 == 0) {
      return false;
    }
    //we only need to test up to the square of the number 
    for (int i = 2; i < Math.sqrt(num) + 1; ++i) {  
      //if the number is divisible by anything except for 1 and itself: return false
      if (num % i == 0) {
        return false;
      }
    }
    //must be a prime number
    return true;
  }

  /**
   * Main
   * @param args
   */
  public static void main(String[] args) {
    //start at 2 because it is the first prime
    int num = 2;
    //prime counter
    int p = 0;
    int max = FetchNumber("How many primes do you want to find?");
    System.out.println("2 is a prime number!");
    while (p < max) {
      if (IsPrime(num) == true) {
        System.out.println(num + " is a prime number!");
        //don't increment our prime counter until we have a new prime!
        p++;
      }
      //next prime candidate
      num++;
    }
  }
}