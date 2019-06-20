### Socket Server / Client

Python
- https://www.geeksforgeeks.org/socket-programming-python/
```python
# Import socket module 
import socket                
  
# Create a socket object 
s = socket.socket()          
  
# Define the port on which you want to connect 
port = 12345                
  
# connect to the server on local computer 
s.connect(('127.0.0.1', port)) 
  
# receive data from the server 
print s.recv(1024) 
# close the connection 
s.close()
```
--------------------------------------------------

Node.js
- https://www.npmjs.com/package/socket.io
```node.js
const server = require('http').createServer();
const io = require('socket.io')(server);
io.on('connection', client => {
  client.on('event', data => { /* … */ });
  client.on('disconnect', () => { /* … */ });
});
server.listen(3000);
```
```bash
npm install http
npm install socket.io
```
