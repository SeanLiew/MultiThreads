import create.CloneTest;
import org.junit.Test;

<<<<<<< HEAD
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
=======
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.StampedLock;
>>>>>>> 41a60dd00cd477b7ede64c9688fc4faf9d0720c0


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
        CloneTest c = new CloneTest();
//        c.clone();

    }
    @Test
    public void atomic(){

        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.addAndGet(1);

        AtomicReference<User> atomicReference = new AtomicReference<User>();

        AtomicIntegerFieldUpdater<User> age = AtomicIntegerFieldUpdater.newUpdater(User.class, "age");


    }

    class User{
        private volatile int age;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
    @Test
    public void semaphore() throws InterruptedException {

        Semaphore semaphore = new Semaphore(10);


        semaphore.acquire();

        semaphore.release();
    }

    @Test
    public void execute() {

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        executorService.execute(null);

        Future<?> submit = executorService.submit(new Runnable() {
            public void run() {

            }
        });

//        new ThreadFactoryBuilder

        Callable<Object> callable = Executors.callable(new Runnable() {
            public void run() {

            }
        });

        User user = new User();

        Callable<User> callable1 = Executors.callable(new Runnable() {
            public void run() {

            }
        }, user);


        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

        scheduledExecutorService.schedule(new Runnable() {
            public void run() {

            }
        }, 100L, TimeUnit.MICROSECONDS);

    }

    @Test
    public void number(){

        int a = -10;

        int b = 3;


        System.out.println(a % b);

        System.out.println(Math.floorMod(a, b));

    }
    @Test
    public void stampedLock(){

        StampedLock lock = new StampedLock();

        Lock lock1 = lock.asReadLock();
        lock1.lock();

        Lock lock2 = lock.asWriteLock();

        ReadWriteLock readWriteLock = lock.asReadWriteLock();


    }
    @Test
    public void cycleBarrier() throws BrokenBarrierException, InterruptedException {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);


        cyclicBarrier.await();


    }

    @Test
    public void phaser(){
        Phaser phaser = new Phaser(10);

        phaser.awaitAdvance(phaser.getPhase());

        phaser.arrive();

        //cyclicBarrier.await()
        phaser.arriveAndAwaitAdvance();

    }

    @Test
    public int testif(){
        int i = 10;
        int j = 2;
        System.out.println(j);
        if ((i = (j <= 0) ? --i : ++i) == 0) {
            System.out.println(i);
            return i;
        }
        System.out.println(i);
        return j;

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
