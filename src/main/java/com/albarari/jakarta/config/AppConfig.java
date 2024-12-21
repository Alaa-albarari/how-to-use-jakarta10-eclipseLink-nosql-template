package com.albarari.jakarta.config;

public class AppConfig {
    private MongoDBConfig mongodb;

    // Getters and setters
    public MongoDBConfig getMongodb() {
        return mongodb;
    }

    public void setMongodb(MongoDBConfig mongodb) {
        this.mongodb = mongodb;
    }

    public static class MongoDBConfig {
        private String host;
        private int port;
        private String database;
        private String username;
        private String password;

        // Getters and setters
        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getDatabase() {
            return database;
        }

        public void setDatabase(String database) {
            this.database = database;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}

