import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @author: liuxiaozheng
 * @Created: 2020-06-07-13:38
 */
public class StopThread {
    private static boolean stop = false;

    public static void main(String[] args) {
        new Thread(() -> {
            long i = 0;
            if(!stop){
                while(true){
                    i++;
                }
            }
            System.out.println(i);
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        stop = true;
    }
}
