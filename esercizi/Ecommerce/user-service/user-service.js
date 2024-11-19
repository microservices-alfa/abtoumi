const express = require('express');
const mongoose = require('mongoose');
const dotenv = require('dotenv');
const userRoutes = require('./routes/userRoutes');

// Charger les variables d'environnement
dotenv.config();

// Initialisation de l'application Express
const app = express();
app.use(express.json());

// Connexion à MongoDB

//
const mongoURI = process.env.MONGO_URI || (process.env.NODE_ENV === 'production' ? 'mongodb://host.docker.internal:27017/userdb' : 'mongodb://localhost:27017/userdb' );
 

// Connexion à MongoDB
mongoose.connect(mongoURI)
 
  .then(() => console.log('connected a MongoDB'))
  .catch(err => console.log('Error !!!! MongoDB:', err));

// Utilisation des routes
app.use('/api/users', userRoutes);

// Démarrer le serveur
const port = process.env.PORT || 3000;
app.listen(port, () => {
    console.log(`User Service su port ${port}  con URL =${mongoURI}`);
});
