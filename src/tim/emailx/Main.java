package tim.emailx;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Main {
    /*
    public static void main(String[] args) throws FileNotFoundException {
        // build HashMap from sample
        HashMap<String, Integer> hashMap;
        hashMap = EmailExtractor.hashMapFromPath("data/sample.txt");

        // convert the HashMap to an ArrayList, then sort it
        // according to the internet, ArrayList is quicker than LinkedList for sorting
        ArrayList<Map.Entry<String, Integer>> domainCountList = new ArrayList<>(hashMap.entrySet());
        domainCountList.sort(Comparator.comparingInt(Map.Entry::getValue));

        // print 10 most common domains in order
        // have to start from end, because it's sorted lowest first
        for (int i = 0; i < 10; i++) {
            Map.Entry<String, Integer> mapEntry = domainCountList.get(domainCountList.size() - i - 1);
            System.out.println(String.format("%2d. %14s: %3d", i + 1, mapEntry.getKey(), mapEntry.getValue()));
        }
    }
     */

    public static void main(String[] args) throws IOException {
        Pattern phoneRegex = Pattern.compile("0(1\\d{8,9}|[23789]\\d{9}|5[56]\\d{8}|[58]00\\d{6}|8(001111|45464\\d))|1(0[15]|1([68]\\d{3}|1[12])|23|47[0157]|57[12]|7070)|999");
        Extractor phoneExtractor = new Extractor(phoneRegex);
        ArrayList<MatchResult> phoneNumbers = phoneExtractor.fromFile("data/numbers.txt");
        for (MatchResult match: phoneNumbers)
            System.out.println(match.group());
    }
}
