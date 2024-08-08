import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.io.File;

// Update file path using
// System.out.println("Absolute path: " + myObj.getAbsolutePath());
public class Main {
    public static HashMap<String, String> yoruba = new HashMap<>();
    public static String word, filePath, line, mergeWord, translatedWord, key;
    public static String totalWord = "";
    public static String[] splitWord;
    public static boolean notFound, custom, date, time;

    public static String name, buffer;

    public static void main(String[] args) {


        System.out.print("\nUser: ");
        Scanner translateS = new Scanner(System.in);
        word = translateS.nextLine();
        word = word.toLowerCase();

        splitWord = word.split(" ");
        customResponse();
        date();

        if (custom || date) {
            runAgain();
        }

        translator(word);
        store();
        printT();

    }

    /*public static void sentenceCase() {
        splitWord = word.split(" ");

        for (int i = 0; i < splitWord.length; i++) {
            findKeyByValue(yoruba, splitWord[i]);
            if (!notFound && i == 0) {
                translator(splitWord[i]);
                store();
                splitWord[i] = key;
            } if (notFound && i == 0) {
                translator(splitWord[i+1]);
                store();
                splitWord[i+1] = key;
            } if (i == 0) {
                mergeWord = splitWord[i] + " " + splitWord[i+1];
                System.out.println("Translation: " + mergeWord);
            }
            //totalWord = totalWord.concat(" ").concat(translatedWord);
        }
        System.out.println(totalWord);
    }*/

    public static void translator(String word) {
        filePath = "..\\Yoruba ChatGPT\\src\\Data.txt";

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
            System.out.println("\nBot: " + key + "\n");
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
            //System.out.println("Oops! " + word + " is unknown to me. I'm still trying to learn.");
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

    public static void customResponse() {
        if (splitWord[0].equals("my") && splitWord[1].equals("name") && splitWord[2].equals("is")) {
            name = splitWord[3];
            System.out.println("Ojo dada " + name + " bawo ni o se wa");
            custom = true;
        } else if ((splitWord[0].equals("oruko") || splitWord[0].equals("orukọ")) && splitWord[1].equals("mi") && splitWord[2].equals("ni")) {
            name = splitWord[3];
            System.out.println("Good day " + name + " how are you?");
            custom = true;
        }

    }

    public static void date() {
        LocalDate currentDate = LocalDate.now();
        if (splitWord[0].equals("what's") && (splitWord[1].equals("today") || splitWord[1].equals("todays") || splitWord[1].equals("today's")) && splitWord[2].equals("date")) {
            System.out.println("Oni ọjọ jẹ " + currentDate);
            date = true;
        } else if (splitWord[0].equals("what") && splitWord[1].equals("is") && (splitWord[2].equals("today") || splitWord[2].equals("todays") || splitWord[2].equals("today's")) && splitWord[3].equals("date")) {
            System.out.println("Oni ọjọ jẹ " + currentDate);
            date = true;
        } else if (splitWord[0].equals("what's") && splitWord[1].equals("the") && splitWord[2].equals("date")) {
            System.out.println("Oni ọjọ jẹ " + currentDate);
            date = true;
        } else if (splitWord[0].equals("what") && splitWord[1].equals("is") && splitWord[2].equals("the") && splitWord[3].equals("date")) {
            System.out.println("Oni ọjọ jẹ " + currentDate);
            date = true;
        } else if (splitWord[0].equals("kini") && (splitWord[1].equals("ojo") || splitWord[1].equals("ọjọ")) && splitWord[2].equals("oni")) {
            System.out.println("Oni ọjọ jẹ " + currentDate);
            date = true;
        } else if (splitWord[0].equals("kini") && (splitWord[1].equals("ojo") || splitWord[1].equals("ọjọ")) && splitWord[2].equals("naa")) {
            System.out.println("Oni ọjọ jẹ " + currentDate);
            date = true;
        }
    }

    public static void time() {
        LocalTime currentTime = LocalTime.now();
        if ((splitWord[0].equals("what's") || splitWord[0].equals("what")) && splitWord[1].equals("the") && splitWord[2].equals("time")) {
            System.out.println("Akoko ni " + currentTime);
            time = true;
        } else if (splitWord[0].equals("what") && splitWord[1].equals("is") && splitWord[2].equals("the") && splitWord[3].equals("time")) {
            System.out.println("Akoko ni " + currentTime);
            time = true;
        } else if (splitWord[0].equals("kini") && splitWord[1].equals("akoko") && splitWord[2].equals("naa")) {
            System.out.println("Akoko ni " + currentTime);
            time = true;
        }
    }

}