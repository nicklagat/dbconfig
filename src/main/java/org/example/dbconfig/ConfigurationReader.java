package org.example.dbconfig;

import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;

public class ConfigurationReader {


    public static DatabaseConfig readDatabaseConfig(){

        // Reading the configuration from yaml

        try (InputStream inputStream = ConfigurationReader.class.getResourceAsStream("/database-config.yml")) {

            if (inputStream != null) {
                Yaml yaml = new Yaml();
                return yaml.loadAs(inputStream, DatabaseConfig.class);
            } else {
                System.err.println("YAML file not found. Make sure it's in the classpath");
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
