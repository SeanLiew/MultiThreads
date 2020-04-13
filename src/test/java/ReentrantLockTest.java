import org.junit.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
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


        new AtomicInteger();

    }

    @Test
    public void tTest (){

        try {
            ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

            ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);

            for (ThreadInfo threadInfo:threadInfos) {
                System.out.println(threadInfo.getThreadId() + "::" + threadInfo.getThreadName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test
    public void testLock (){

            Lock lock = new ReentrantLock();

            Condition condition = lock.newCondition();

        try {


            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
