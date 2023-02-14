import java.io.PrintWriter;
import java.net.Socket;

public class ProcessClientRequest {

    public static void processClientRequest(Socket socket, String title) throws Exception {
        long time1 = System.currentTimeMillis();
        System.out.println("Request processing started at: " + time1);
        Thread.sleep(10 * 1000);
        long time2 = System.currentTimeMillis();
        System.out.println("Request processing ended at: " + time2);
        String body = ResponseGenerator.generatorResponseHTML(title, time1, time2);
        String header = ResponseGenerator.generatorResponseHeader(body.length());

        new PrintWriter(socket.getOutputStream(), true).println(header + body);
    }
}