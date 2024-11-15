const express = require('express');
const app=express();

const { default: mongoose } = require("mongoose")
const productRoute=require('./routes/product')
const usertRoute=require('./routes/user')
require('./config/connect'); // faire un appel a fichier connect
app.use(express.json())
app.use('/product',productRoute);
app.use('/user',usertRoute);
app.listen(3000,()=>{
    console.log('Server work !!')
})


