const express = require('express');
const jwt = require('jsonwebtoken');
const User = require('../models/User');

const router = express.Router();

// Route pour l'inscription
router.post('/register', async (req, res) => {
  const { username, password } = req.body;

  // Vérifier si l'utilisateur existe déjà
  const userExists = await User.findOne({ username });
  if (userExists) {
    return res.status(400).send('User esiste già');
  }

  // Création de l'utilisateur
  const user = new User({ username, password: password });
  await user.save();
  res.status(201).send('User creato  ');
});

// Route pour la connexion (login)
router.post('/login', async (req, res) => {
  const { username, password } = req.body;

  // Vérification des informations utilisateur
  const user = await User.findOne({ username });
  if (!user) {
    return res.status(400).send('User not exist');
  }

  // Vérification du mot de passe
  const validPassword = await bcrypt.compare(password, user.password);
  if (!validPassword) {
    return res.status(400).send('PASSWORD incorrect');
  }

  // Génération du token JWT
  const token = jwt.sign({ userId: user._id }, process.env.JWT_SECRET);
  res.json({ token });
});

// Route pour obtenir la liste des Users
router.get('/', async (req, res) => {
  const users = await User.find();
  res.json(users);
});

module.exports = router;
