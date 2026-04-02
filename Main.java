import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static String[] dictionaryWords;
    public static File dictionarytxt = new File("words_alpha.txt");

    public static void wordleGame(String word) {
        clear();
        int length = word.length();
        int tries = length;
        String[] guesses = new String[length];
        for (int i = 0; i < length; i++) {
            guesses[i] = "";
            for (int h = 0; h < length; h++) {
                guesses[i] += "#";
            }
        }
        Scanner scan = new Scanner(System.in);
        String choice = "";
        for (int i = 0; i < length; i++) {
            while ((choice.toLowerCase().replaceAll("[^A-Za-z]+", "").length() != length) || (!isWord(choice))) {
                clear();
                for (int h = 0; h < length; h++) {
                    System.out.println(guesses[h]);
                }
                System.out.println("");
                choice = scan.nextLine();
            }
            choice = compareStrings(choice, word);
            guesses[length - tries] = choice;
            tries--;
            choice = "";
        }
        scan.close();
        clear();
        System.out.println("The answer was: " + word);
    }

    public static boolean isWord(String word) {
        for (String currentWord : dictionaryWords) {
            if (word.equals(currentWord)) {
                return true;
            }
        }
        return false;
    }

    public static String compareStrings(String guess, String answer) {
        int length = guess.length();
        String result = "";
        for (int i = 0; i < length; i++) {
            if (guess.substring(i, i + 1).equals(answer.substring(i, i + 1))) {
                result += Codes.green + guess.substring(i, i + 1) + Codes.reset;
            } else if (answer.contains(guess.substring(i, i + 1))) {
                result += Codes.yellow + guess.substring(i, i + 1) + Codes.reset;
            } else {
                result += guess.substring(i, i + 1);
            }
        }
        return result;
    }

    public static String generateWord(int length) {
        if (WordSorter.getArray(length).getInstances() > 0) {
            return WordSorter.getArray(length).getRandomString();
        }
        System.out.println("Failed to get a word of that length, picking a random 5 letter word");
        return WordSorter.getArray(5).getRandomString();
    }

    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) {
        int lines = 0;
        try (Scanner fileScanner = new Scanner(dictionarytxt)) {
            while (fileScanner.hasNextLine()) {
                fileScanner.nextLine();
                lines++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("your code broke twin");
        }
        dictionaryWords = new String[lines];
        try (Scanner fileScanner = new Scanner(dictionarytxt)) {
            int line = 0;
            while (fileScanner.hasNextLine()) {
                dictionaryWords[line] = fileScanner.nextLine();
                line++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("your code broke twin");
        }

        WordSorter.setup();
        int maxLength = 20;
        Scanner scan = new Scanner(System.in);
        boolean picked = false;
        String choice = "";
        while (!picked) {
            System.out.println("Welcome to Wordle!");
            System.out.println("Type 'random' to play the regular game");
            System.out.println("Type 'custom' to play a custom game");
            choice = scan.nextLine();
            choice = choice.toLowerCase().replaceAll(" ", "");
            if (choice.equals("random")) {
                System.out.println("random");
                picked = true;
                wordleGame(generateWord(5));
            } else if (choice.equals("custom")) {
                boolean customChoice = false;
                System.out.println("custom");
                picked = true;
                while (!customChoice) {
                    clear();
                    System.out.println("What type of custom game do you want to play?");
                    System.out.println("Type 'length' to play a random game with a custom length");
                    System.out.println("Type 'word' to play a game with a custom word");
                    choice = scan.nextLine();
                    choice = choice.toLowerCase().replaceAll(" ", "");
                    if (choice.equals("length")) {
                        int customInt = 0;
                        while ((customInt < 2) || (customInt > maxLength)) {
                            clear();
                            System.out.println("What do you want the word's length to be? (max length is "
                                    + WordSorter.getMaxLength() + ")");
                            customInt = scan.nextInt();
                        }
                        System.out.println("Custom length: " + customInt);
                        customChoice = true;
                        wordleGame(generateWord(customInt));
                    } else if (choice.equals("word")) {
                        String customWord = "";
                        while (customWord.length() < 3) {
                            clear();
                            System.out.println("What do you want the word to be?");
                            customWord = scan.nextLine().toLowerCase().replaceAll(" ", "");
                        }
                        System.out.println("Custom word: " + customWord);
                        customChoice = true;
                        wordleGame(customWord);
                    }
                }
            }
            clear();
            scan.close();
        }

    }
}
