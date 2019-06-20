### Socket Server / Client

Python
- https://www.geeksforgeeks.org/socket-programming-python/
```python
# Import socket module 
import socket                
  
# Create a socket object 
s = socket.socket()                     
  
# connect to the server on local computer 
s.connect(("localhost", 8000)) 
  
# receive data from the server 
print s.recv(1024) 
# close the connection 
s.close()
```
--------------------------------------------------

Node.js
- https://gist.github.com/luciopaiva/e6f60bd6e156714f0c5505c2be8e06d8

Server
```node.js
const io = require("socket.io");
const server = io.listen(8000);

console.log("Server is running on http://localhost:8000");

// event fired every time a new client connects
server.on("connection", function(socket){
	console.log("New client connected!");
	
	setInterval(() => {
	    socket.emit("command", "hi");
	}, 1000);
});
```
Client
```node.js
const io = require("socket.io-client");
const ioClient = io.connect("http://localhost:8000");

ioClient.on("command", function(msg){
	console.info(msg);
});
```
```bash
npm install socket.io
npm install socket.io-client
```
