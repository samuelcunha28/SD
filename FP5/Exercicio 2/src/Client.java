public class Client {
    public static void main(String[] args) {
        new ClientWriterThread().start();
        new ClientPrinterThread().start();
    }
}