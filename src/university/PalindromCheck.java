package university;

import Prog1Tools.IOTools;

public class PalindromCheck{

    public static void main (String [] args){
    	String input = IOTools.readString("Input a word: ").toLowerCase();
		String reversed = reverse(input);
		
		if(reversed.equals(input)){
			System.out.println("The string "+ input +" is a palindrome.");
		}else{System.out.println("The string "+ input +" is NOT a palindrome.");}
    }

    public static String reverse(String str){ // "input" is passed as "str" argument
   		char[] c = str.toCharArray();

   		for(int l = 0, h = str.length()-1; l<h; l++,h--){
   			char temp = c[l]; // saves first letter
   			c[l] = c[h]; // assigns last letter to first letter // first letter is changed
   			c[h] = temp; // assigns first letter to last letter
   		}
   		return String.copyValueOf(c); // converts Char-array c to String
    }


}