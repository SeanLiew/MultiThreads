import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * desc:
 * author: liuxiaozheng
 * time: 2020/4/16  15:09
 **/
public class SimpleTest {
    private int count = 0;
    private AtomicInteger atomicInteger = new AtomicInteger();

    @Test
    public void orTest(){

        int a = -4;
        int b = 5;
        int c = 4;
        int d = -5;

        System.out.println(a|b);
        System.out.println(c|d);
        System.out.println(b^c);
        System.out.println("------------------");
        System.out.println(b<<2);
        System.out.println(a<<2);
        System.out.println("------------------");
        System.out.println(b>>2);
        System.out.println(b>>>2);
        System.out.println(a>>2);
        System.out.println(a>>>2);
        System.out.println(d>>2);
        System.out.println(d>>>2);
    }
    @Test
    public void testCount(){
        CountDownLatch countDownLatch = new CountDownLatch(3);

        for (int c=0; c < 3; c++){
            new Thread(() -> {
                for (int i=0; i < 10000; i++){
                    count++;
                    atomicInteger.incrementAndGet();
                }
                countDownLatch.countDown();
            }).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(count);
        System.out.println(atomicInteger.get());
    }
}
