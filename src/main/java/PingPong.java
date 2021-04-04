import java.util.concurrent.SynchronousQueue;

class PingPong {
    private int count;
    private final Object monitor = new Object();
    public PingPong(int count) {
        this.count = count;
    }

    public void ping() {
        for (int i = 0; i < count; i++) {
            try {
                Thread.sleep(1000);
                synchronized (monitor) {
                    monitor.notify();
                    System.out.print("Ping");
                    monitor.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void pong() {
        for (int i = 0; i < count; i++) {
            try {
                synchronized (monitor) {
                    monitor.notify();
                    System.out.print("Pong");
                    monitor.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        PingPong pingPong = new PingPong(10);
        new Thread(pingPong::ping).start();
        new Thread(pingPong::pong).start();
    }
}
