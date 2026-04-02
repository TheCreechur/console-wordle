import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class WordSorter {
    private static WordArray[] array;
    private static int maxLength = 0;
    private static File wordtxt = new File("words.txt");

    public static WordArray getArray(int length) {
        return array[length];
    }

    public static int getMaxLength() {
        return maxLength;
    }

    public static void setup() {
        WordArray.setup();
        for (int i = 0; i < 50; i++) {
            if (new WordArray(i).getInstances() > 0) {
                maxLength = i;
            }
        }
        array = new WordArray[maxLength + 1];
        for (int i = 0; i <= maxLength; i++) {
            array[i] = new WordArray(i);
        }

    }
}
