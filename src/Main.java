import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

// Update file path using
// System.out.println("Absolute path: " + myObj.getAbsolutePath());
public class Main {
    public static HashMap<String, String> yoruba = new HashMap<>();
    public static String word, filePath, line, mergeWord, translatedWord, key;
    public static String totalWord = "";
    public static String[] splitWord;
    public static boolean notFound;
    public static void main(String[] args) {


        System.out.print("\nInput a word: ");
        Scanner translateS = new Scanner(System.in);
        word = translateS.nextLine();

        translator(word);
        store();
        splitWord = word.split(" ");


        printT();

    }


    public static void translator(String word) {
        filePath = "..\\Yoruba Translator\\src\\yoruba.txt";

        word = word.toLowerCase();

        // Read the file and populate the HashMap
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                // Split each line into key and value
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    yoruba.put(key, value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void printT() {
        word = word.toLowerCase();

        // Find and print the key for the given word
        String key = findKeyByValue(yoruba, word);
        if (key != null) {
            translatedWord = key;
            System.out.println("\nTranslation: " + key);
            runAgain();
        } else {
            //System.out.println("Oops! " + word + " is unknown to me. I'm still trying to learn.");
            notFound = true;
            runAgain();
        }
    }

    public static void store() {
        // Find and print the key for the given word
        String key = findKeyByValue(yoruba, word);
        notFound = false;
        if (key != null) {
            translatedWord = key;
        } else {
            System.out.println("Oops! " + word + " is unknown to me. I'm still trying to learn.");
            notFound = true;
        }
    }
    // Method to find the key for a given value
    public static String findKeyByValue(HashMap<String, String> map, String value) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                notFound = false;
                return entry.getKey();
            }
        }
        notFound = true;
        return null; // Return null if the value is not found
    }

    public static void runAgain() {
        String[] call = {"call"};
        Main.main(call);
    }

    public static void respond() {
        if ();
    }


}