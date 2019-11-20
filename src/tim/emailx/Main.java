package tim.emailx;

import java.io.FileNotFoundException;
import java.util.*;

public class Main {

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
}
