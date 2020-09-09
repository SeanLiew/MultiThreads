package map;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * desc:
 * author: liuxiaozheng
 * time: 2020/9/9  16:54
 **/
public class HashMap1 {
    public static void main(String[] args) {
        HashMapThread hmt0 = new HashMapThread();
        HashMapThread hmt1 = new HashMapThread();
        HashMapThread hmt2 = new HashMapThread();
        HashMapThread hmt3 = new HashMapThread();
        HashMapThread hmt4 = new HashMapThread();
        hmt0.start();
        hmt1.start();
        hmt2.start();
        hmt3.start();
        hmt4.start();
    }

}
class HashMapThread extends Thread
{
    private static AtomicInteger ai = new AtomicInteger(0);
    private static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(1);

    @Override
    public void run()
    {
        while (ai.get() < 100000)
        {
            map.put(ai.get(), ai.get());
            map.remove(1);
            ai.incrementAndGet();
        }
        System.out.println(Thread.currentThread().getName() + "执行结束完");
    }
}
