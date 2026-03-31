import java.util.Scanner;

public class Main {
    public static void random(){
        
    }
    public static void custom(){

    }
    public static void clear(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static void main(String[] args) {
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
            }else if (choice.equals("custom")){
                boolean customChoice = false;
                System.out.println("custom");
                picked = true;
            }
            clear();
        }

        
    }
}
