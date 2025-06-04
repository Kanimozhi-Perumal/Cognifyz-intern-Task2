import java.util.Scanner;

public class PasswordStrengthChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        
        String strength = checkPasswordStrength(password);
        System.out.println("Password Strength: " + strength);
        
        scanner.close();
    }

    public static String checkPasswordStrength(String password) {
        int lengthCriteria = password.length() >= 8 ? 1 : 0;
        int upperCaseCriteria = password.matches(".*[A-Z].*") ? 1 : 0;
        int lowerCaseCriteria = password.matches(".*[a-z].*") ? 1 : 0;
        int numberCriteria = password.matches(".*\\d.*") ? 1 : 0;
        int specialCharacterCriteria = password.matches(".*[!@#$%^&*()-+=].*") ? 1 : 0;

        int score = lengthCriteria + upperCaseCriteria + lowerCaseCriteria + numberCriteria + specialCharacterCriteria;

        if (score == 5) {
            return "Strong";
        } else if (score >= 3) {
            return "Medium";
        } else {
            return "Weak";
        }
    }
}
