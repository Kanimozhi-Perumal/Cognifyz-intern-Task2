package task2;
import java.io.*;
import java.util.Scanner;

public class FileEncryptDecrypt {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Prompt user for action
        System.out.println("Choose an option: ");
        System.out.println("1. Encrypt file");
        System.out.println("2. Decrypt file");
        System.out.print("Enter choice (1 or 2): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        // Prompt for file path
        System.out.print("Enter input file path: ");
        String inputFile = scanner.nextLine();
        
        // Generate output file name
        String outputFile;
        if (choice == 1) {
            outputFile = addFileSuffix(inputFile, "_encrypted");
        } else {
            outputFile = addFileSuffix(inputFile, "_decrypted");
        }
        
        // Prompt for encryption key
        System.out.print("Enter encryption key (1-25): ");
        int key = scanner.nextInt();
        
        try {
            // Process file based on choice
            if (choice == 1) {
                processFile(inputFile, outputFile, key, true);
                System.out.println("File encrypted successfully. Output saved to: " + outputFile);
            } else if (choice == 2) {
                processFile(inputFile, outputFile, key, false);
                System.out.println("File decrypted successfully. Output saved to: " + outputFile);
            } else {
                System.out.println("Invalid choice!");
            }
        } catch (IOException e) {
            System.out.println("Error processing file: " + e.getMessage());
        }
        
        scanner.close();
    }
    
    // Helper method to add suffix to filename
    private static String addFileSuffix(String filePath, String suffix) {
        int dotIndex = filePath.lastIndexOf('.');
        if (dotIndex == -1) {
            return filePath + suffix;
        }
        return filePath.substring(0, dotIndex) + suffix + filePath.substring(dotIndex);
    }
    
    // Method to process file (encrypt or decrypt)
    private static void processFile(String inputFile, String outputFile, int key, boolean encrypt) 
            throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            
            String line;
            while ((line = reader.readLine()) != null) {
                String processedLine = encrypt ? encryptLine(line, key) : decryptLine(line, key);
                writer.write(processedLine);
                writer.newLine();
            }
        }
    }
    
    // Encrypt a single line using Caesar cipher
    private static String encryptLine(String text, int key) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                result.append((char) ((c - base + key) % 26 + base));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
    
    // Decrypt a single line using Caesar cipher
    private static String decryptLine(String text, int key) {
        // Decryption is encryption with negative key
        return encryptLine(text, 26 - (key % 26));
    }
}