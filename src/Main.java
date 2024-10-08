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
    public static HashMap<String, String> data = new HashMap<>();
    public static String word, filePath, line, translatedWord, key;;
    public static String[] splitWord, splitWord2;
    public static boolean notFound, custom, date, time, cases;

    public static String name;
    public static String buffer = "";
    public static int i, j, k = 0;

    public static void main(String[] args) {

        if (k == 0) {
            System.out.println("----------Welcome to Eddy's Yoruba Chat Bot----------");
            System.out.println("Hi, what do you want me to do for you.");
            System.out.println("At the moment, I can only respond to greetings. Please input your greetings below and I'll respond");
            k++;
        }
        System.out.println("\n");
        System.out.print("User: ");
        Scanner translateS = new Scanner(System.in);
        word = translateS.nextLine();
        word = word.toLowerCase();

        System.out.print("\n");
        System.out.print("Bot: ");

        splitWord = word.split(" ");
        splitWord2 = word.split(", ");


        translator(word);

        trySentence();


        //custom
        customResponse();
        date();


        runAgain();

    }


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
                    data.put(key, value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        word = word.toLowerCase();

        // Find and print the key for the given word
        String key = findKeyByValue(data, word);
        if (key != null) {
            translatedWord = key;
            System.out.print(key + " ");
        } else {
            //System.out.println("Oops! " + word + " is unknown to me. I'm still trying to learn.");
            notFound = true;
        }

        // Find and print the key for the given word
        key = findKeyByValue(data, word);
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
        if (notFound && !cases) {
            trySentence();
        }
        String[] call = {"call"};
        Main.main(call);
    }

    public static void trySentence() {
        if (key != null) {
            for (i = 0; i < splitWord.length; i++) {
                translator(splitWord[i]);
            }
        }

        /*if (key != null) {
            for (int j = 0; j < splitWord2.length; j++) {
                translator(splitWord2[j]);
            }
        }*/

    }

    public static void customResponse() {
        /*if (i == 0) {
            j = 0;
        } else {
            j = i;
        }*/
        if (splitWord[0].equals("my") && splitWord[1].equals("name") && splitWord[2].equals("is")) {
            name = splitWord[3];
            System.out.println("Ojo dada " + name + " bawo ni o se wa");
            custom = true;
        } else if ((splitWord[0].equals("oruko") || splitWord[0].equals("orukọ")) && splitWord[1].equals("mi") && splitWord[2].equals("ni")) {
            name = splitWord[3];
            System.out.println("Good day " + name + " how are you?");
            custom = true;
        }
        cases = true;
        runAgain();
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
        cases = true;
        runAgain();
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
        cases = true;
        runAgain();
    }

}