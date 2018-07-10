package ConcurrentVisitNotSafe;

public class Main {
    public static void main(String[] args) {
        Data data = new Data();
        NotSafeA na = new NotSafeA(data);
        na.start();
        NotSafeB nb = new NotSafeB(data);
        nb.start();
    }
}
