package waitcomsume;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * desc:
 * author: liuxiaozheng
 * time: 2020/4/17  10:01
 **/
public class BoundedQueue {
    /**
     * 数组容器
     */
    private Food[] items;

    /**
     * 元素数量
     */
    private int count;

    /**
     * 默认最大容量
     */
    private static final int DEFAULT_LARGE_SIZE = 16;

    /**
     * 下一个插入的索引
     */
    private int addIndex;

    /**
     * 下一个删除的索引
     */
    private int removeIndex;

    /**
     * 锁
     */
    private ReentrantLock lock = new ReentrantLock();

    /**
     * 容器为空的condition
     */
    private Condition notEmpty = lock.newCondition();

    /**
     * 容器为满的condition
     */
    private Condition notFull = lock.newCondition();

    private BoundedQueue(){
    }
    public static BoundedQueue getInstance(int largeSize){
        if (largeSize <= 0){
            largeSize = DEFAULT_LARGE_SIZE;
        }
        BoundedQueue queue = new BoundedQueue();
        queue.items = new Food[largeSize];
        return queue;
    }
    public static BoundedQueue getInstance(){
        BoundedQueue queue = new BoundedQueue();
        queue.items = new Food[DEFAULT_LARGE_SIZE];
        return queue;
    }

    /**
     * 增加元素
     * @param food
     * @throws InterruptedException
     */
    public void add(Food food) throws InterruptedException {
        lock.lock();
        try {
            if (count == items.length){
                notFull.await();
            }
            items[addIndex] = food;
            if (++addIndex == items.length){
                addIndex = 0;
            }
            ++count;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 移除一个元素
     * @return 移除的元素
     * @throws InterruptedException
     */
    public Food remove() throws InterruptedException {
        lock.lock();
        try {
            if (count == 0){
                notEmpty.await();
            }
            Food food = items[removeIndex];
            items[removeIndex] = null;
            if (++removeIndex == items.length){
                removeIndex = 0;
            }
            --count;
            notFull.signal();
            return food;
        } finally {
            lock.unlock();
        }
    }


}
