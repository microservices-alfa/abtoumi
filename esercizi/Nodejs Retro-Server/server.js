// import 
var express =require('express');
// instantiate server
var server =express();

// configure route 


// lanch server

server.get('/',function(req,res){
    res.setHeader('Content-Type','text/html');
    res.status(200).send('<h1> Buongiorno in Toumi Server</h1>');



});
server.listen(8080,function(){
console.log('server work :)  ')

});