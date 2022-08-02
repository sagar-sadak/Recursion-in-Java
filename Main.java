// import libraries
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	// main method that handles
    public static void main(String[] args) throws IOException
    {
    	// initialize loop sentient value
		String userInput = "-1";
		// initialize to read input
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		int [] array;
		String userString = "";
		
		// display menu until user enters 5
		while (!userInput.equals("5"))
		{
			printMenu();
			userInput = br.readLine();
			
			switch (userInput) {
			
				// if user enters 1, call largestInt()
				case "1":
					array = parseInts(br);
					System.out.println("The largest number in the array is: " + largestInt(array, 0, array.length - 1));
					break;
				
				// if user enters 2, call primeProduct()
				case "2":
					array = parseInts(br);
					System.out.println("The product of all prime numbers in the array is: " + primeProduct(array, 0, array.length - 1, 1));
					break;
				
				// if user enters 3, call largestSumDigits()
				case "3":
					array = parseInts(br);
					System.out.println("The largest sum of digits in the array is: " + largestSumDigits(array, 0, array.length - 1));
					break;
				
				// if user enters 4, call cleanString()
				case "4":
					System.out.println("Please enter String:");
					userString = br.readLine();
					System.out.println("String after adjacent duplicate characters were removed: " + cleanString(userString));
					break;
				
				// if user enters 5, don't do anything
				case "5":
					break;
				
				// if anything else is entered, display warning message
				default:
					System.out.println("Please choose a number between 1 and 5.");
                    break;
			}
		}
    }


    // Utility method for printing the menu 
    public static void printMenu() {
        System.out.print("\nWhat would you like to do?\n\n");
        System.out.print("1: Find the largest number in an array of integers\n");
        System.out.print("2: Calculate the product of all prime numbers in an array of integers\n");
        System.out.print("3: Find the element with the largest sum of digits in an array of integers\n");
        System.out.print("4: Remove adjacent duplicate characters in a String\n");
        System.out.print("5: Quit\n\n");
    }

    // utility method for parsing integers from standard input
    public static int[] parseInts(BufferedReader reader) {
        String line = "";
        ArrayList<Integer> container = new ArrayList<>();
        try {
            System.out.print("Please enter integers:\n");
            line = reader.readLine();
            int num = Integer.parseInt(line);

            // read in user ints until they enter <= 0
            while (num > 0) {
                container.add(num);
                line = reader.readLine();
                num = Integer.parseInt(line);
            }

        } catch (IOException ex) {
            System.out.println("IO Exception");
        }

        // shift all values from arrayList to an array
        int[] result = new int[container.size()];
         for(int i = 0; i < container.size(); i++){
             result[i] = container.get(i);
         }
         // return that array
        return result;
    }
    
    // Recursive method for finding the largest number in the array
    public static int largestInt(int[] array, int start, int end)
    {
    	// check if condition
    	if (start == end)
    		return array[start];
    	else
    	{
    		// split the array and call the method on left and right parts
    		int middle = (start + end)/2;
    		int left = largestInt(array, start, middle);
    		int right = largestInt(array, middle + 1, end);
    		// return the larger of the two
    		return(Math.max(left, right));
    	}
    }
    
    // Recursive method that return the product of prime numbers in the array
    public static int primeProduct(int[] array, int start, int end, int product)
    {
    	// check if condition
    	if (start == end)
    	{
    		// if it is prime, multiply it to product
    		if (isPrime(array[start], 2))
    		{
    			return array[start]*product;
    		}
    		// else just return the product
    		else
    		{
    			return product;
    		}
    	}
    	
    	else
    	{
    		// split the array in half and call the method on left and right parts
    		int middle = (start + end)/2;
    		int leftProduct = primeProduct(array, start, middle, product);
    		int rightProduct = primeProduct(array, middle + 1, end, product);
    		// return the product of the left and right parts
    		return leftProduct*rightProduct;
    	}
    }
    
    // Recursive function to check if a number is prime
    public static boolean isPrime(int num, int index)
    {
    	// check a bunch of conditions for prime numbers
    	if (num < 2)
    		return false;
    	if (num == 2)
    		return true;
    	if (num % index == 0)
    		return false;
    	if (index > (num/2))
    		return true;
    	
    	// call the method again on the next index
    	return isPrime(num, index + 1);
    }
    
    // Recursive method that returns the number with the largest sum of its digits
    public static int largestSumDigits(int[] array, int start, int end)
    {
    	// if there is only one int, return the sum of that number
    	if (start == end)
    		return sumOfDigits(array[start]);
    	else
    	{
    		// split the array in half and call the method of left and right parts
    		int middle = (start + end)/2;
    		int left = largestSumDigits(array, start, middle);
    		int right = largestSumDigits(array, middle + 1, end);
    		// return the bigger one
    		return (Math.max(left, right));
    	}
    }
    
    // recursive method for summing digits in a number
    public static int sumOfDigits(int num)
	{
    	// check if its a single digit
		if (num < 10)
			return num;
		// else take out the last digit and call the method again
		else
			return (num % 10) + sumOfDigits(num / 10);
	}
    
    // Recursive method to clean a string
    public static String cleanString(String string)
	{
    	// if its a single char, return that
		if (string.length() < 2)
			return string;
		
		// if its exactly two chars, check if they are the same
		if (string.length() == 2)
		{
			if (string.substring(0,1).equals(string.substring(1,2)))
				return string.substring(0,1);
			else
				return string;
		}
		
		else
		{
			// split the string in half and call the method on left and right parts
			int middle = string.length() / 2;
			String left = cleanString(string.substring(0, middle));
			String right = cleanString(string.substring(middle, string.length()));
			// if left and right are equal, return only one copy, else return both
			if (left.substring(left.length() - 1, left.length()).equals(right.substring(0,1)))
				return left.substring(0, left.length() - 1) + right;
			else
				return left + right;
		}
	}
}
