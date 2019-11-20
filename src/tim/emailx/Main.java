package tim.emailx;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        // email matching pattern
        Pattern emailRegex = Pattern.compile("@softwire\\.com");

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

        // count occurrences
        Stream<MatchResult> matches = textScanner.findAll(emailRegex);
        long count = matches.count();

        // show count
        System.out.println(count);

        // close scanner
        textScanner.close();
    }
}
