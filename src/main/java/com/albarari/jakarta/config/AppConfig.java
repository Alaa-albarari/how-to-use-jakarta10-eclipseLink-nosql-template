package com.albarari.jakarta.config;

/**
 * Configuration class for the application, containing MongoDB settings.
 */
public class AppConfig {
    private MongoDBConfig mongodb;

    /**
     * Gets the MongoDB configuration.
     *
     * @return the MongoDB configuration
     */
    public MongoDBConfig getMongodb() {
        return mongodb;
    }

    /**
     * Sets the MongoDB configuration.
     *
     * @param mongodb the MongoDB configuration to set
     */
    public void setMongodb(MongoDBConfig mongodb) {
        this.mongodb = mongodb;
    }

    /**
     * Inner class representing MongoDB configuration properties.
     */
    public static class MongoDBConfig {
        private String host;
        private int port;
        private String database;
        private String username;
        private String password;

        /**
         * Gets the MongoDB host.
         *
         * @return the MongoDB host
         */
        public String getHost() {
            return host;
        }

        /**
         * Sets the MongoDB host.
         *
         * @param host the MongoDB host to set
         */
        public void setHost(String host) {
            this.host = host;
        }

        /**
         * Gets the MongoDB port.
         *
         * @return the MongoDB port
         */
        public int getPort() {
            return port;
        }

        /**
         * Sets the MongoDB port.
         *
         * @param port the MongoDB port to set
         */
        public void setPort(int port) {
            this.port = port;
        }

        /**
         * Gets the MongoDB database name.
         *
         * @return the MongoDB database name
         */
        public String getDatabase() {
            return database;
        }

        /**
         * Sets the MongoDB database name.
         *
         * @param database the MongoDB database name to set
         */
        public void setDatabase(String database) {
            this.database = database;
        }

        /**
         * Gets the MongoDB username.
         *
         * @return the MongoDB username
         */
        public String getUsername() {
            return username;
        }

        /**
         * Sets the MongoDB username.
         *
         * @param username the MongoDB username to set
         */
        public void setUsername(String username) {
            this.username = username;
        }

        /**
         * Gets the MongoDB password.
         *
         * @return the MongoDB password
         */
        public String getPassword() {
            return password;
        }

        /**
         * Sets the MongoDB password.
         *
         * @param password the MongoDB password to set
         */
        public void setPassword(String password) {
            this.password = password;
        }
    }
}