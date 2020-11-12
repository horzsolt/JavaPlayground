package horzsolt.javaplayground;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class FileReadExample {

    @Test
    public void givenFile_whenUsingBufferedReader_thenExtractedLineIsCorrect() throws IOException {
        try (BufferedReader br = Files.newBufferedReader(Paths.get("e:\\enwik8.txt"))) {
            for (int i = 0; i < 299999; i++) {
                br.readLine();
            }

            String extractedLine = br.readLine();
            assertEquals("      <timestamp>2006-03-03T03:57:23Z</timestamp>", extractedLine);
        }
    }

    @Test
    public void givenLargeFile_whenUsingFilesAPI_thenExtractedLineIsCorrect() throws IOException {
        try (Stream lines = Files.lines(Paths.get("e:\\enwik8.txt"))) {
            String extractedLine = lines.skip(299999).findFirst().get().toString();

            assertEquals("      <timestamp>2006-03-03T03:57:23Z</timestamp>", extractedLine);
        }
    }

    @Test
    public void givenSmallFile_whenUsingFilesAPI_thenExtractedLineIsCorrect() throws IOException {
        String extractedLine = Files.readAllLines(Paths.get("e:\\enwik8.txt")).get(299999);

        assertEquals("      <timestamp>2006-03-03T03:57:23Z</timestamp>", extractedLine);
    }

}
