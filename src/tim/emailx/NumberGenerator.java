package tim.emailx;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;
import java.util.stream.IntStream;

public class NumberGenerator {
    public static void main(String[] args) throws IOException {
        long nDigits = (long) 1e6;  // a million!

        try (FileWriter writer = new FileWriter("data/numbers.txt")) {
            Random random = new Random();
            try (IntStream intStream = random.ints(nDigits, '0', '9')) {
                Iterator<Integer> it = intStream.iterator();
                while (it.hasNext()) {
                    writer.append((char)it.next().intValue());
                }
            }
        }
    }
}
