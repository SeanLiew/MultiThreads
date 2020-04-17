/**
 * desc:
 * author: liuxiaozheng
 * time: 2020/4/9  16:02
 **/
public class Interrupted {

    public static void main(String[] args) throws Exception {
        Thread sleepThread = new Thread(new SleepRunner(), "sleepRunner");
        sleepThread.setDaemon(true);
        Thread busyThread = new Thread(new BusyRunner(), "sleepRunner");
        busyThread.setDaemon(true);

        sleepThread.start();
        busyThread.start();

        Thread.sleep(50);

        sleepThread.interrupt();
        busyThread.interrupt();


        System.out.println("sleepThread is interrupted " + sleepThread.isInterrupted());
        System.out.println("busyThread is interrupted " + busyThread.isInterrupted());

        Thread.sleep(20);

    }




    static class SleepRunner implements Runnable{
        @Override
        public void run() {
            while (true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("sleepRunner 被中断了");
            }
        }
    }

    static class BusyRunner implements Runnable{
        @Override
        public void run() {
            while (true){
                System.out.print("*");
            }
        }
    }
}
