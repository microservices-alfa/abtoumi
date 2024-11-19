const mongoose = require('mongoose');

// Définition du schéma produit
const productSchema = new mongoose.Schema({
  name: { type: String, required: true },
  description: { type: String, required: true },
  price: { type: Number, required: true }
});

module.exports = mongoose.model('Product', productSchema);
