package tim.emailx;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        // email matching pattern
        Pattern emailRegex = Pattern.compile("[\\w.'%+\\-]+@([a-z0-9\\-]+\\.[a-z0-9\\-.]*[a-z0-9\\-])", Pattern.CASE_INSENSITIVE);

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

        // find matches
        Stream<MatchResult> matches = textScanner.findAll(emailRegex);

        // create hashmap
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        Iterator<MatchResult> it = matches.iterator();
        while (it.hasNext()) {
            MatchResult match = it.next();
            String domain = match.group(1);

            // add to hashmap
            if (hashMap.containsKey(domain)) {
                hashMap.put(domain, hashMap.get(domain) + 1);
            } else {
                hashMap.put(domain, 1);
            }
        }

        // close scanner
        textScanner.close();

        // print all email domains
        hashMap.forEach((key, value) -> {
            System.out.println(key + ": " + value.toString());
        });
    }
}
