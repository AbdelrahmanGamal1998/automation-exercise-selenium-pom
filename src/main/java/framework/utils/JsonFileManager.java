package framework.utils;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import java.io.File;
import java.io.IOException;

/** Reads values from a JSON test data file with a JSON path such as {@code signup.expectedTitle}. */
public class JsonFileManager {

    private final File file;

    public JsonFileManager(String jsonFilePath) {
        this.file = new File(jsonFilePath);
    }

    public String getTestData(String jsonPath) {
        try {
            return JsonPath.read(file, "$." + jsonPath);
        } catch (PathNotFoundException e) {
            throw new IllegalArgumentException("No value for '" + jsonPath + "' in " + file, e);
        } catch (IOException e) {
            throw new IllegalStateException("Cannot read " + file, e);
        }
    }
}