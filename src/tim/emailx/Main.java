package tim.emailx;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
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

        // create HashMap
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        Iterator<MatchResult> it = matches.iterator();
        while (it.hasNext()) {
            MatchResult match = it.next();
            String domain = match.group(1);

            // add to HashMap
            if (hashMap.containsKey(domain)) {
                hashMap.put(domain, hashMap.get(domain) + 1);
            } else {
                hashMap.put(domain, 1);
            }
        }

        // close scanner
        textScanner.close();

        // convert the HashMap to an ArrayList, then sort it
        // according to the internet, ArrayList is quicker than LinkedList for sorting
        ArrayList<Map.Entry<String, Integer>> domainCountList = new ArrayList<>(hashMap.entrySet());
        domainCountList.sort(Comparator.comparingInt(Map.Entry::getValue));

        // print all email domains and their counts
        hashMap.forEach((key, value) -> {
            System.out.println(key + ": " + value.toString());
        });
    }
}
