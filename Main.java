import java.util.Scanner;

public class Main {
    public static void wordleGame(String word){
        clear();
        System.out.println(word);
    }

    public static String generateWord(int length){
        return "hi";
    }
        
    public static void clear(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static void main(String[] args) {
        int maxLength = 20;
        Scanner scan = new Scanner(System.in);
        boolean picked = false;
        String choice = "";
        while (!picked){
            System.out.println("Welcome to Wordle!");
            System.out.println("Type 'random' to play the regular game");
            System.out.println("Type 'custom' to play a custom game");
            choice = scan.nextLine();
            choice = choice.toLowerCase().replaceAll(" ", "");
            if (choice.equals("random")){
                System.out.println("random");
                picked = true;
                wordleGame(generateWord(5));
            }else if (choice.equals("custom")){
                boolean customChoice = false;
                System.out.println("custom");
                picked = true;
                while(!customChoice){
                    clear();
                    System.out.println("What type of custom game do you want to play?");
                    System.out.println("Type 'length' to play a random game with a custom length");
                    System.out.println("Type 'word' to play a game with a custom word");
                    choice = scan.nextLine();
                    choice = choice.toLowerCase().replaceAll(" ", "");
                    if (choice.equals("length")){
                        int customInt = 0;
                        while((customInt < 2) || (customInt > maxLength)){
                            clear();
                            System.out.println("What do you want the word's length to be?");
                            customInt = scan.nextInt();
                        }
                        System.out.println("Custom length: " + customInt);
                        customChoice = true;
                        wordleGame(generateWord(customInt));
                    }else if (choice.equals("word")){
                        String customWord = "";
                        while (customWord.length() < 2){
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
        }

        
    }
}
