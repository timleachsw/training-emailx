package tim.emailx;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // count number of emails
        int count = 0;

        File textFile;
        Scanner textScanner;

        // load file
        try {
            textFile = new File("data/sample.txt");
            textScanner = new Scanner(textFile);
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't load file. Exiting.");
            return;
        }

        // go through line by line
        while (textScanner.hasNextLine()) {
            String line = textScanner.nextLine();

            // walk through line as in pseudocode
            for (int i = 0; i < line.length() - 13; i++) {
                if (line.substring(i, i + 13).equals("@softwire.com"))
                    count++;
            }
        }

        // show count
        System.out.println(count);

        // close scanner
        textScanner.close();
    }
}
