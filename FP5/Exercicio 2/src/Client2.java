public class Client2 {
    public static void main(String[] args) {
        new ClientWriterThread().start();
        new ClientPrinterThread().start();
    }
}