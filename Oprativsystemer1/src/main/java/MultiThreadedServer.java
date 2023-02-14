import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadedServer implements Runnable {

    protected int serverPort = 8080;
    protected ServerSocket serverSocket = null;
    protected boolean isStopped = false;

    public MultiThreadedServer(int port) {
        this.serverPort = port;
    }

    public void run() {
        System.out.println("Multi-threaded server listening on port: " + serverPort);

        openServerSocket();
        Socket Client;

        while (!isStopped()) {
            try {
                Client = serverSocket.accept();
                System.out.println(Client.getInetAddress() + ":" + Client.getPort());

                new Thread(
                        new AsyncSearchSimulator(Client, "Multithreaded Server: ")
                ).start();

            } catch (Exception e) {
                stop();
                e.printStackTrace();
            }
        }

        System.out.println("Server Stopped.");
    }

    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    public synchronized void stop() {
        // implementation to stop the server from the main thread if needed
        isStopped = true;
        try { serverSocket.close(); }
        catch (Exception e) { e.printStackTrace(); }
    }

    private void openServerSocket() {
        //open Socket here
        try {
            serverSocket = new ServerSocket(serverPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}