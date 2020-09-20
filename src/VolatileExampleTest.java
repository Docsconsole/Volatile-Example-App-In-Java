public class VolatileExampleTest implements Runnable {
    SharedData obj = new SharedData();

    public static void main(String[] args) {
        VolatileExampleTest vd = new VolatileExampleTest();
        new Thread(vd).start();
        new Thread(vd).start();
        new Thread(vd).start();
        new Thread(vd).start();
    }

    @Override
    public void run() {
        obj.incrementCounter();
        System.out.println("Counter for Thread " + Thread.currentThread().getName() +
                " " + obj.getCounter());
    }
}

class SharedData {
    public volatile int sharedVolatileObject = 0;

    public int getCounter() {
        return sharedVolatileObject;
    }

    public void incrementCounter() {
        ++sharedVolatileObject;
    }
}