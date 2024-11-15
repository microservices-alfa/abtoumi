const express = require('express');
const router=express.Router();
const Product=require('../models/product');
 


/************************************************************************* */
/************************************************************************* */


/*                               CRUD Product                              */


/************************************************************************* */
/************************************************************************* */


/************************************************************************* */
router.post('/addProduct', (req, res) => {
    // Assuming you are using body-parser or express.json() middleware
    const data = req.body;
    
    // Create a new user instance
    const prod = new Product(data);

    prod.save()
        .then(savedProduct => {
            // Respond with the saved user
            res.status(200).json(savedProduct); // HTTP 200
        })
        .catch(err => {
            // Handle errors and send appropriate response
            console.error(err); // Log the error
            res.status(500).json({ error: 'Server error' }); // Internal server error
        });
});
/************************************************************************* */
// autre methode de POST

router.post('/createProduct',async (req,res) => {
   
    try{
        data=req.body;
        prod=new Product(data);
        savedProduct=await prod.save();
        res.send(savedProduct)
    }catch(error) {
                // Handle errors and send appropriate response
                console.error(err); // Log the error
                res.status(500).json({ error: 'Server error' }); // Internal server error
            }
});
/************************************************************************* */

router.get('/getallProduct',(req,res)=>{

   
    Product.find().then(
    (product)=>{res.send(product)
        
    }
    ).catch(err => {
        // Handle errors and send appropriate response
        console.error(err); // Log the error
        res.status(500).json({ error: 'Server error' }); // Internal server error
    });

})

/************************************************************************* */
router.get('/getbyidProduct/:id', async (req, res) => {
    try {
        const myid = req.params.id;  // Get the ID from the URL parameters
        
        // Retrieve the user from the database
        const prod = await Product.findOne({ _id: myid });

     
        if (!prod) {
            return res.status(404).json({ message: 'Product not found' });
        }

        // Send the retrieved user back as a JSON response
        res.status(200).json(prod); // HTTP 200 for OK
        
    } catch (error) {
        // Handle errors and send appropriate response
        console.error(error); // Log the error for debugging purposes
        res.status(500).json({ error: 'Server error' }); // Internal server error
    }
});
/************************************************************************* */

/// autre methode plus simple getall
router.get('/allProduct', async (req, res) => {
    try {
        // Retrieve all users from the database
        const products = await Product.find();
        
        // If no users found, return a message or empty array
        if (products.length === 0) {
            return res.status(404).json({ message: 'No Product found' });
        }

        // Send the retrieved users back as a JSON response
        res.status(200).json(products); // HTTP 200 for OK
        
    } catch (error) {
        // Handle errors and send appropriate response
        console.error(error); // Log the error for debugging purposes
        res.status(500).json({ error: 'Server error' }); // Internal server error
    }
});


/************************************************************************* */

router.put('/updateProduct/:id', async (req, res) => {
    try {
        const myid = req.params.id;  // Get the ID from the URL parameters
        const data = req.body;       // The new data to update the product

        // Validate the ID format
        if (!mongoose.Types.ObjectId.isValid(myid)) {
            return res.status(400).json({ message: 'Invalid product ID format' });
        }

        // Update the product by ID and return the updated document
        const product = await Product.findByIdAndUpdate(myid, data, { new: true });

        // If no product is found with the given ID, return a 404
        if (!product) {
            return res.status(404).json({ message: 'Product not found' });
        }

        // Send the updated product as the response
        res.status(200).json(product);

    } catch (error) {
        // Handle any other errors (e.g., database issues, invalid data)
        console.error(error);  // Log the error for debugging purposes
        res.status(500).json({ error: 'Server error' }); // Internal server error
    }
});
/************************************************************************* */
router.delete('/delete/:id',async(req,res)=>{
    try {
        const myid = req.params.id;  // Get the ID from the URL parameters
        
        // Retrieve the user from the database
        const product = await Product.findByIdAndDelete({ _id: myid });
      

        // If no user found, return a 404 error
        if (!product) {
            return res.status(404).json({ message: 'product not found' });
        }

        // Send the retrieved user back as a JSON response
        res.status(200).json(product); // HTTP 200 for OK
        
    } catch (error) {
        // Handle errors and send appropriate response
        console.error(error); // Log the error for debugging purposes
        res.status(500).json({ error: 'Server error' }); // Internal server error
    }

})



module.exports=router;