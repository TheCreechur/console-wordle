import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.lang.Math;

public class WordArray {
    private String[] array;
    private int length;
    private static String[] words;
    private static File wordtxt = new File("words.txt");

    public WordArray(int initLength) {
        length = initLength;
        int matches = 0;
        for (String word : words) {
            if (word.length() == initLength) {
                matches++;
            }
        }
        array = new String[matches];
        int index = 0;
        for (String word : words) {
            if (word.length() == initLength) {
                array[index] = word;
                index++;
            }
        }
    }

    public int getInstances() {
        return array.length;
    }

    public int getLength() {
        return length;
    }

    public String getRandomString() {
        return array[((int) (Math.random() * array.length))];
    }

    public static void setup() {
        int lines = 0;
        try (Scanner fileScanner = new Scanner(wordtxt)) {
            while (fileScanner.hasNextLine()) {
                fileScanner.nextLine();
                lines++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("your code broke twin");
        }
        words = new String[lines];
        try (Scanner fileScanner = new Scanner(wordtxt)) {
            int line = 0;
            while (fileScanner.hasNextLine()) {
                words[line] = fileScanner.nextLine();
                line++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("your code broke twin");
        }

    }
}
