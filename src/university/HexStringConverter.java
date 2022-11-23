package university;

public class HexStringConverter{

	public static String[] hexStrings = {"2ae43", "8g023", "249abc", "2354aer23", "234245"};

    public static void main (String [] args){
		// available-numbers & characters initialization
        String initial = "0123456789abcdef";
		char[] dict = initial.toCharArray();
		// print = Valid Chars: 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, a, b, c, d, e, f,
		System.out.print("Valid Chars: ");
		for(int l=0, h=dict.length-1; l<=h; l++){
			System.out.print(dict[l]+", ");
		}
		System.out.println();

		// check validity of each element hexStrings
		for (String hexString : hexStrings) {
			char[] hexCharArr = hexString.toCharArray();

			if (checkValid(hexCharArr, dict)) {
				System.out.print("The decimal value of the hex-String " + String.valueOf(hexCharArr) + " is: ");
				convertHexString2Decimal(hexCharArr);
			}
		}
    }

//    public static char pythonGet(char[] arr, int index) {
//    	if (index >= 0) return arr[index];
//    	return arr[arr.length+index];
//    }

// String.indexOf
    public static boolean checkValid(char[] cArr,char[] dict){

    	for(int i=0; i< cArr.length; i++){
    		for (int j=0; j<dict.length;j++) {
    			if(cArr[i] == dict[j]){break;}
    			if(j+1 >= dict.length){
    				System.out.println("The String "+ String.valueOf(cArr) +" is no valid hex-String.");
    				return false;
    			}
    		}
    	}
    	return true;
    }

    public static void convertHexString2Decimal(char[] cArr){
    	double sum = 0;
    	for(int l = 0, h = cArr.length-1; l<=cArr.length & h>=0; l++,h--){
 			sum += Character.getNumericValue(cArr[h])*Math.pow(16,l);
    	}
    	System.out.println(String.format("%.0f",sum));
    }
}