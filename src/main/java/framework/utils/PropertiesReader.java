package framework.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Loads {@code configurations.properties} from the classpath into the system properties. A value given on the
 * command line (for example {@code -DbrowserType=firefox}) wins over the file.
 */
public final class PropertiesReader {

    private static final String FILE = "configurations.properties";

    private PropertiesReader() {
    }

    public static void loadProperties() {
        Properties defaults = new Properties();
        try (InputStream in = PropertiesReader.class.getClassLoader().getResourceAsStream(FILE)) {
            if (in == null) {
                throw new IllegalStateException(FILE + " was not found on the classpath");
            }
            defaults.load(in);
        } catch (IOException e) {
            throw new IllegalStateException("Cannot read " + FILE, e);
        }
        defaults.forEach((key, value) -> System.getProperties().putIfAbsent(key, value));
    }
}