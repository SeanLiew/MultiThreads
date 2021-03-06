import org.junit.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * desc:
 * author: liuxiaozheng
 * time: 2020/1/14  9:43
 **/
public class ReentrantLockTest {
    ReentrantLock locl = new ReentrantLock();

    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    @Test
    public void test (){

        locl.lock();

        locl.unlock();

        try {
            locl.tryLock(100L, TimeUnit.MICROSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean b = locl.tryLock();

        CountDownLatch c = new CountDownLatch(5);



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
    @Test
    public void testReadWriteLock (){

        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();


        readLock.lock();

        writeLock.lock();
        writeLock.tryLock();

    }
    @Test
    public void testMap(){

        Map<String, Object> params = new ConcurrentHashMap<>();

        params.put("123", "232");

    }
    @Test
    public void testQueue() throws InterruptedException {

        Queue<String> queue = new ConcurrentLinkedQueue<>();

        LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>();
        linkedBlockingQueue.put("");
        linkedBlockingQueue.add("");

        DelayQueue<ScheduledFuture> delayQueue = new DelayQueue<>();

        ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(11);

        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(10);

        arrayBlockingQueue.add("");

        SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>(true);


        LinkedTransferQueue<String> linkedTransferQueue = new LinkedTransferQueue<>();
        linkedTransferQueue.transfer("");

    }
    @Test
    public void fork() throws InterruptedException {

        ForkJoinPool pool = new ForkJoinPool();
        
//        pool.execute(new ForkJoinTask<Object>() {
//        });

    }
}
