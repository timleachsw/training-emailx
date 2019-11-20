package tim.emailx;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        // build HashMap from sample
        HashMap<String, Integer> hashMap;
        try {
            hashMap = EmailExtractor.hashMapFromPath("data/sample.txt");
        } catch (FileNotFoundException e) {
            System.out.println(String.format("File not found.\nError:\n%s\nExiting.", e.getMessage()));
            return;
        }

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
}
