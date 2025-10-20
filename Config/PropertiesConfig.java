package Config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesConfig {
   private static final Properties properties =new Properties();
    private static final PropertiesConfig propertiesConfig=new PropertiesConfig();

    private PropertiesConfig() {
        try (FileReader fileReader1 = new FileReader("C:\\Users\\bbbb\\OneDrive\\Desktop\\Petistaan\\src\\Resources\\database.properties");
             FileReader fileReader2 = new FileReader("C:\\Users\\bbbb\\OneDrive\\Desktop\\Petistaan\\src\\Resources\\message.properties")) {
            properties.load(fileReader1);
            properties.load(fileReader2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
      public static PropertiesConfig getInstance(){
        return propertiesConfig;
      }
       public static String getProperty(String key){
            return properties.getProperty(key);
        }
    }

