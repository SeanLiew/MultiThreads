import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;


/**
 * desc:
 * author: liuxiaozheng
 * time: 2020/4/16  15:09
 **/
public class SimpleTest {

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

}
