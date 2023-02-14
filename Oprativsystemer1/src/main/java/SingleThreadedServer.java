import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Represents a server which is single-threaded.
 */
public class SingleThreadedServer implements Runnable {

    protected int serverPort = 8080;
    protected ServerSocket serverSocket = null;
    protected boolean isStopped = false;

    public SingleThreadedServer(int port) {
        this.serverPort = port;
    }

    public void run() {
        System.out.println("Singlethread: " + serverPort);

        openServerSocket();
        Socket clientSocket;
        while (!isStopped()) {
            try {clientSocket = serverSocket.accept();
                handleClient(clientSocket);
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


    public synchronized void stop()  {
        isStopped = true;
        try { serverSocket.close(); }
        catch (Exception e) { e.printStackTrace(); }
    }


    private void openServerSocket() {
        try { serverSocket = new ServerSocket(serverPort); }
        catch (IOException e) { e.printStackTrace(); }
    }


    private void handleClient(Socket clientSocket) throws Exception {
        System.out.println(clientSocket.getInetAddress() + ":" + clientSocket.getPort());
        ProcessClientRequest.processClientRequest(clientSocket, "Singlethread Server: ");
    }
}