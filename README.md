### Socket Server / Client

Python
- https://python-socketio.readthedocs.io/en/latest/client.html
```python
import socketio

sio = socketio.Client()
sio.connect('http://localhost:8000')

# Doesn't display if server is already running
@sio.event
def connect():
    print("I'm connected!")

@sio.event
def disconnect():
    print("I'm disconnected!")

@sio.on('command')
def on_message(data):
    print('Msg rcvd: ' + data)
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
