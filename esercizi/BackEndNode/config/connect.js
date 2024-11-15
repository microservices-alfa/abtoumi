const mongoose=require('mongoose');
require('dotenv').config();
const mongoURI = process.env.MONGO_URI || 'mongodb://localhost:27017/ecomerce'; 
mongoose.connect(mongoURI)
.then(
    ()=>{console.log('connect')

    }
).catch(
    (err)=>{
        console.log('err')
    }
)


module.exports=mongoose;
