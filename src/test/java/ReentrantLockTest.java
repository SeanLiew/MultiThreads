import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * desc:
 * author: liuxiaozheng
 * time: 2020/1/14  9:43
 **/
public class ReentrantLockTest {
    ReentrantLock locl = new ReentrantLock();

    @Test
    public void test (){

        boolean b = locl.tryLock();

        CountDownLatch c = new CountDownLatch(5);


        AtomicInteger atomicInteger = new AtomicInteger();

    }
}
