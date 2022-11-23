package university;

public class PasswordGenerator {

    public static void main(String[] args) {
        String validSymbols = "abcdefghijklmnopqrstuvwxyz1234567890():§$ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] validChars = validSymbols.toCharArray();
        String newPassword = generateNewPassword(validChars);
        System.out.println(newPassword);
    }
    public static String generateNewPassword ( char[] validChars){
        String temp = "";
        for (int i = 0; i < 8; i++) {
            temp += validChars[(int) (Math.random() * validChars.length)];
        }return temp;
    }
}
