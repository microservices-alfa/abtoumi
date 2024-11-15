# Usa un'immagine di base di TomEE 8.0.2 con Java 8
FROM tomee:jre11-alpine-plume

ENV DB_HOST=host.docker.internal


# Copia il file WAR dalla directory target del progetto nella cartella webapps di TomEE
COPY ./target/WEBAPP.war /usr/local/tomee/webapps/WEBAPP.war

# Espone la porta 8080 (porta di TomEE)
EXPOSE 8080

# Comando per avviare TomEE
CMD ["catalina.sh", "run"]
