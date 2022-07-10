package utils;

import java.util.Random;
import java.util.stream.IntStream;

import static java.lang.String.format;

public class RandomStringGenerator {

    private RandomStringGenerator() {
        throw new AssertionError(format("Creation of instance of %s is prohibited.", RandomStringGenerator.class));
    }

    public static String getRandCharSequence(int length) {
        StringBuilder builder = new StringBuilder();
        IntStream.range(0, length).forEach(i -> {
            char symbol = (char)(97 + new Random().nextInt(26));
            builder.append(symbol);
        });
        return builder.toString();
    }
}
