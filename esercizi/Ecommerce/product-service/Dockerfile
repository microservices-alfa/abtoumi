# Utilisation de l'image officielle Node.js
FROM node:16

# Définition du répertoire de travail
WORKDIR /app

# Copie des fichiers de configuration
COPY package.json  package-lock.json ./

# Installation des dépendances
RUN npm install

# Copie du code source dans l'image
COPY . .

# Exposition du port 3001 pour l'API
EXPOSE 3001

# Commande pour démarrer le serveur
CMD ["node", "product-service.js"]
