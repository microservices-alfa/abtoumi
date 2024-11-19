const express = require('express');
const Product = require('../models/Product');

const router = express.Router();

// Route pour ajouter un produit
router.post('/', async (req, res) => {
  const { name, description, price } = req.body;
  
  const product = new Product({ name, description, price });
  await product.save();
  res.status(201).send('Product  aggiunto ');
});

// Route pour obtenir la liste des produits
router.get('/', async (req, res) => {
  const products = await Product.find();
  res.json(products);
});

module.exports = router;
