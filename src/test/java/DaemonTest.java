/**
 * desc:
 * author: liuxiaozheng
 * time: 2020/4/9  15:26
 **/
public class DaemonTest {
    public static void main(String[] args) {
        WorkerThread thread = new WorkerThread("work thread 0");

        System.out.printf("work thread daemon: %b\n", thread.isDaemon());
        System.out.printf("main thread daemon: %b\n", Thread.currentThread().isDaemon());

        thread.start();
//        try {
//            Thread.sleep(3 * 1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        System.out.println("main thread exit") ;
        //System.exit(0);
    }

}

class WorkerThread extends Thread {
    public WorkerThread(String trheadName) {
        // if true, JVM will quit after main thread quit
        // if false, JVM will not quit, because this workerThread will never end.
        setDaemon(false);
        setName(trheadName);
    }

    public void run() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; ; i++) {
                    System.out.println(Thread.currentThread().getName() +  i);
                    try {
                        sleep(2 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        for (int i = 1; ; i++) {
            System.out.println(this.getName() + i);
            try {
                sleep(2 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
