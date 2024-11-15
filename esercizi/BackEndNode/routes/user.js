const express = require('express');
const router=express.Router();
const User=require('../models/user');
 
/************************************************************************* */
/************************************************************************* */


/*                               CRUD User                              */


/************************************************************************* */
/************************************************************************* */


/************************************************************************* */




/************************************************************************* */
router.post('/add', (req, res) => {
    // Assuming you are using body-parser or express.json() middleware
    const data = req.body;
    
    // Create a new user instance
    const usr = new User(data);
    
    // Save the user to the database
    usr.save()
        .then(savedUser => {
            // Respond with the saved user
            res.status(200).json(savedUser); // HTTP 200
        })
        .catch(err => {
            // Handle errors and send appropriate response
            console.error(err); // Log the error
            res.status(500).json({ error: 'Server error' }); // Internal server error
        });
});
/************************************************************************* */
// autre methode de POST

router.post('/create',async (req,res) => {
   
    try{
        data=req.body;
        usr=new User(data);
        savedUser=await usr.save();
        res.send(savedUser)
    }catch(error) {
                // Handle errors and send appropriate response
                console.error(err); // Log the error
                res.status(500).json({ error: 'Server error' }); // Internal server error
            }
});
/************************************************************************* */

router.get('/getall',(req,res)=>{

   
    User.find().then(
    (users)=>{res.send(users)
        
    }
    ).catch(err => {
        // Handle errors and send appropriate response
        console.error(err); // Log the error
        res.status(500).json({ error: 'Server error' }); // Internal server error
    });

})

/************************************************************************* */
router.get('/getbyid/:id', async (req, res) => {
    try {
        const myid = req.params.id;  // Get the ID from the URL parameters
        
        // Retrieve the user from the database
        const user = await User.findOne({ _id: myid });

        // If no user found, return a 404 error
        if (!user) {
            return res.status(404).json({ message: 'User not found' });
        }

        // Send the retrieved user back as a JSON response
        res.status(200).json(user); // HTTP 200 for OK
        
    } catch (error) {
        // Handle errors and send appropriate response
        console.error(error); // Log the error for debugging purposes
        res.status(500).json({ error: 'Server error' }); // Internal server error
    }
});
/************************************************************************* */

/// autre methode plus simple getall
router.get('/all', async (req, res) => {
    try {
        // Retrieve all users from the database
        const users = await User.find();
        
        // If no users found, return a message or empty array
        if (users.length === 0) {
            return res.status(404).json({ message: 'No users found' });
        }

        // Send the retrieved users back as a JSON response
        res.status(200).json(users); // HTTP 200 for OK
        
    } catch (error) {
        // Handle errors and send appropriate response
        console.error(error); // Log the error for debugging purposes
        res.status(500).json({ error: 'Server error' }); // Internal server error
    }
});


/************************************************************************* */

router.put('/update/:id',async(req,res)=>{
    try{
        data=req.body;
        const myid = req.params.id;  // Get the ID from the URL parameters

        const user = await User.findByIdAndUpdate({ _id: myid },data);
    
        res.send(user)

    }catch(error) {
                // Handle errors and send appropriate response
                console.error(err); // Log the error
                res.status(500).json({ error: 'Server error' }); // Internal server error
            }
})
/************************************************************************* */
router.delete('/delete/:id',async(req,res)=>{
    try {
        const myid = req.params.id;  // Get the ID from the URL parameters
        
        // Retrieve the user from the database
        const user = await User.findByIdAndDelete({ _id: myid });
      

        // If no user found, return a 404 error
        if (!user) {
            return res.status(404).json({ message: 'User not found' });
        }

        // Send the retrieved user back as a JSON response
        res.status(200).json(user); // HTTP 200 for OK
        
    } catch (error) {
        // Handle errors and send appropriate response
        console.error(error); // Log the error for debugging purposes
        res.status(500).json({ error: 'Server error' }); // Internal server error
    }

})



module.exports=router;