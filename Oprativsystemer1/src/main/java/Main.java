class Main {

    private static int serverPort = 8080;
    private static boolean singleThread = true;


    public static void main(String[] args) {
        System.out.println("Starting server");
        System.out.println("\n");
        if (singleThread) new SingleThreadedServer(serverPort).run();
        else new MultiThreadedServer(serverPort).run();
    }
}
