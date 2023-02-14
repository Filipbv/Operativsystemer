import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class AsyncSearchSimulator implements Runnable {

    protected Socket clientSocket = null;
    protected String serverText = null;

    public AsyncSearchSimulator(Socket clientSocket, String serverText) {
        this.clientSocket = clientSocket;
        this.serverText = serverText;
    }

    public void run() {
        try{
            long time1 = System.currentTimeMillis();
            System.out.println("Request processing started at: " + time1);
            Thread.sleep(10 * 1000);

            long time2 = System.currentTimeMillis();
            System.out.println("Request processing ended at: " + time2);
            String body = ResponseGenerator.generatorResponseHTML(serverText, time1, time2);
            String header = ResponseGenerator.generatorResponseHeader(body.length());

            new PrintWriter(clientSocket.getOutputStream(), true).println(header + body);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}