package tim.emailx;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class EmailExtractor {
    // email matching pattern
    public static Pattern emailRegex = Pattern.compile("[\\w.'%+\\-]+@([a-z0-9\\-]+\\.[a-z0-9\\-.]*[a-z0-9\\-])",
            Pattern.CASE_INSENSITIVE);

    public static HashMap<String, Integer> hashMapFromPath(String path) throws FileNotFoundException {
        // load file
        File textFile = new File(path);
        Scanner textScanner = new Scanner(textFile);

        // find matches
        Stream<MatchResult> matches = textScanner.findAll(emailRegex);

        // create HashMap
        HashMap<String, Integer> hashMap = new HashMap<>();
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

        return hashMap;
    }
}
