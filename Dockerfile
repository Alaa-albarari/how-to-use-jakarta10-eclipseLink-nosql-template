FROM payara/server-full:6.2024.9-jdk17
COPY target/jakartaee-hello-world.war $DEPLOY_DIR
