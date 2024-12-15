package com.albarari.jakarta.config;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class MongoConfig {
    private String host;
    private int port;
    private String database;
    private String username;
    private String password;

    public static MongoConfig getConfig() {
        Yaml yaml = new Yaml();
        try (InputStream input = MongoConfig.class.getClassLoader().getResourceAsStream("mongo-config.yaml")) {
            Map<String, Object> config = yaml.load(input);
            Map<String, Object> mongodb = (Map<String, Object>) config.get("mongodb");

            MongoConfig mongoConfig = new MongoConfig();
            mongoConfig.host = (String) mongodb.get("host");
            mongoConfig.port = (Integer) mongodb.get("port");
            mongoConfig.database = (String) mongodb.get("database");
            mongoConfig.username = (String) mongodb.get("username");
            mongoConfig.password = (String) mongodb.get("password");

            return mongoConfig;
        } catch (Exception e) {
            throw new RuntimeException("Failed to load MongoDB configuration", e);
        }
    }

    // Getters
    public String getHost() { return host; }
    public int getPort() { return port; }
    public String getDatabase() { return database; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
}
