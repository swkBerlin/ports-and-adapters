package foo.bar.external;


import foo.bar.service.ObservationSink;

import java.io.*;
import java.util.Collection;
//TODO Maybe add folder as constructor parameter
public class FileWeatherSink implements ObservationSink {
    public final String CVS_EXTENSION = ".csv";
    @Override
    public void storeObservations(String observationName, Collection<String> exportOutput) {
        String fileName = observationName + CVS_EXTENSION;
        try {
            try(BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(fileName), "UTF-8"));){
                for (String s : exportOutput) {
                    bufferedWriter.write(s+"\n");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("unable to store file", e);
        }
    }
}
