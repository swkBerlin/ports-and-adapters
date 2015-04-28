package foo.bar;


import foo.bar.external.FileWeatherSink;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class FileObservationSinkTest {
    public static final String SAMPLE_OBSERVATION = "observation11";
    public static final String OUTPUT_FILE_NAME = SAMPLE_OBSERVATION + ".csv";
    private FileWeatherSink sut = new FileWeatherSink();

    @Before
    public void removeSampleFileName() throws Exception {
        File file = new File(OUTPUT_FILE_NAME);
        if(file.exists()){
            file.delete();
        }
    }

    @Test
    public void shallSaveDataToAFile() throws IOException {
        String[] fileContent = {"a1,a2", "b1,b2", "c1,c2"};

        sut.storeObservations(SAMPLE_OBSERVATION, Arrays.asList(fileContent));

        assertThat(readFileContent(OUTPUT_FILE_NAME), Matchers.contains(fileContent));
    }

    private List<String> readFileContent(String fileName) throws IOException {
        List<String> fileRows = new LinkedList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),
                "UTF-8"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileRows.add(line);
            }
            return fileRows;
        }
    }
}