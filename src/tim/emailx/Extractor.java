package tim.emailx;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

class Extractor {
    private Pattern _regex;

    Extractor(Pattern regex) {
        _regex = regex;
    }

    ArrayList<MatchResult> fromFile(String path) throws IOException {
        /*
        Scanner scanner = new Scanner(path);
        Stream<MatchResult> matchesStream = scanner.findAll(_regex);
        Iterator<MatchResult> it = matchesStream.iterator();
        ArrayList<MatchResult> matches = new ArrayList<>();
        while (it.hasNext())
            matches.add(it.next());
        return matches;
         */
        Path pathObject = Path.of(path);
        String digits = Files.readString(pathObject);
        ArrayList<MatchResult> results = new ArrayList<>();
        Matcher matcher = _regex.matcher(digits);
        if (matcher.find()) {
            do {
                results.add(matcher.toMatchResult());
            } while (matcher.find(matcher.start() + 1));
        }

        return results;
    }
}
