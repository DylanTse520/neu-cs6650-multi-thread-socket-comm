# Single and Multi-Threaded Socket Communication

## Description

Implement a single-threaded server that can communicate with one client, and a multithreaded server that can communicate with multiple client at the same time.

## Build and run

Under current directory, open a terminal and use javac to compile 3 programs with the following commands:

 > javac Client.java
 > javac SingleThreadServer.java
 > javac MultiThreadServer.java

(a) To use the single thread server, start the single thread server with the following command:

 > java SingleThreadServer
server 1099

Keep the current terminal, open one more terminal and navigate to the same directory.
Start the client with the following commands:

 > java Client
client localhost 1099

Now the client should be able to make connection with the single thread server.
Server terminal should log "New client connected".
In client terminal, type something and hit Enter, the server terminal should log "Client sent: *things you typed*".
Then the client terminal should log "Server replied: Thread *No*: *things you typed*".
It should fail to try to run another client and make connection with the single thread server at the same time.

(b) To use the multi thread server, start the multi thread server with the following command:

 > java MultiThreadServer
server 1099

Keep the current terminal, open two more terminals and navigate to the same directory.
In one terminal, start client1 with the following commands:

 > java Client
client1 localhost 1099

Now client1 should be able to make connection with the multi thread server.
Server terminal should log "New client connected".
In the other terminal, start client2 with the following commands:

 > java Client
client2 localhost 1099

Now client2 should be able to make connection with the multi thread server.
Server terminal should log "New client connected" again.
In either client terminal, type something and hit Enter, the server terminal should log "Client sent: *things you typed*".
Then the corresponding client terminal should log "Server replied: Thread *No*: *things you typed*".
The thread number received in two clients should be different.