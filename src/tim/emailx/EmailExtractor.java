package tim.emailx;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Stream;

class EmailExtractor {
    // email matching pattern
    private static Pattern emailRegex = Pattern.compile("(?<=\\s)[\\w.'%+\\-]+@([a-z0-9\\-]+\\.[a-z0-9\\-.]*[a-z0-9\\-])(?=\\s)",
            Pattern.CASE_INSENSITIVE);

    static HashMap<String, Integer> hashMapFromPath(String path) throws FileNotFoundException {
        // load file
        File textFile = new File(path);
        HashMap<String, Integer> hashMap = new HashMap<>();

        try (Scanner textScanner = new Scanner(textFile)) {
            // find matches
            Stream<MatchResult> matches = textScanner.findAll(emailRegex);
            matches.forEach(match -> {
                String domain = match.group(1);

                // add to HashMap
                if (hashMap.containsKey(domain)) {
                    hashMap.put(domain, hashMap.get(domain) + 1);
                } else {
                    hashMap.put(domain, 1);
                }
            });
        }

        return hashMap;
    }
}
