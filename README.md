# Operativsystemer Arbeidskrav 1
## Starting the program
* First, go to the main file, Main.java
* Then, assign the desired listeningport in the Main file
* You can choose the servermode, either as a single threaded server or an multithreaded server.
  * This is done by changing the boolean singleThread to either true or false.
* Run Main
* At last, Send a HTTP GET requests on localhost:port

## What is the difference between single-threaded and multi-threaded processes?
In a single-threaded client-server program, a single thread of execution handles all incoming client requests, which can lead to performance bottlenecks as the server must wait for each client request to be processed before moving on to the next one. This can result in slower response times, especially if the server is handling a large number of client requests simultaneously.

In contrast, a multi-threaded client-server program can handle multiple client requests concurrently, with each request being processed by a separate thread. This can greatly improve performance, as the server can process multiple requests simultaneously without waiting for each one to be completed before moving on to the next. This approach can also improve the responsiveness of the server, as each client can receive a faster response time due to the ability of the server to handle multiple requests concurrently.

However, implementing a multi-threaded client-server program can be more complex than a single-threaded program, as issues such as thread synchronization and race conditions must be carefully managed to ensure the correct operation of the program. Additionally, the overhead of creating and managing multiple threads can also impact the overall performance of the program, especially on systems with limited resources. Overall, the choice between a single-threaded and multi-threaded client-server program will depend on the specific requirements of the system and the trade-offs between performance and complexity.


## Our observations
Upon observing the HTTP GET requests sent by clients, we found that two data transmissions occur from the client to the server. The first message, containing no content, is assumed to be part of the TCP three-way-handshake. The second message is the actual HTTP GET request. Our server generates the same response regardless of the content of the messages received, so both the empty message and the actual request receive the same response.

However, using a single-threaded server slows down efficiency because it must generate a response twice, taking 10 seconds each time. In contrast, a multi-threaded server can handle both messages in parallel, reducing the time of the HTTP interaction between a client and our server from 20.02s to 10.02s.

Initially, we attempted to read the content of the messages to determine whether they were empty or not, hoping to return an empty response and save time. However, this led to an infinite loop when the server attempted to read the empty messages, preventing the client from sending the actual request. Ultimately, we determined that it was better to let the single-threaded server generate a response for each message, demonstrating the benefits of multi-threading when developing server software like this.
