public class ObjSharing {
    public static void main(String[] args) {
        int NThreads = 5;
        SynchronizedArrayList asFrases = new SynchronizedArrayList();

        for (int i = 0; i < NThreads; i++)
            new Worker(asFrases, i).start();

        for (int j = 0; j < 6; j++) {
            try {
                Thread.sleep(1000);
            } catch (Exception ex) {
            }
            for (int k = 0; k < asFrases.get().size(); k++)
                System.out.println(asFrases.get().get(k));
        }
    }
}