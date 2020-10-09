package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Используется для считывания данных из config.properties
 */
public class PropReader {

    /**
     * Название конфиг файла
     */
    private static final String PROP_FILE_NAME = "config.properties";

    /**
     * Путь к файлу для чтения
     */
    public static final String PATH_READ_FILE = "pathReadFile";

    /**
     * Путь к файлу для записи
     */
    public static final String PATH_WRITE_FILE = "pathWriteFile";

    /**
     * Вернет строку по ключу из config.properties
     *
     * @param key Ключ в config.properties
     * @return Вернет значение по ключу
     */
    public static String getVal(String key) {

        Properties prop = new Properties();

        InputStream inputStream = PropReader.class.getClassLoader().getResourceAsStream(PROP_FILE_NAME);

        if (inputStream != null) {
            try {
                prop.load(inputStream);
                return prop.getProperty(key);

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        return null;
    }
}
