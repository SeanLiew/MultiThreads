package waitcomsume;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * desc:
 * author: liuxiaozheng
 * time: 2020/4/17  10:38
 **/
public class FarmThread extends Thread{

    /**
     * 队列
     */
    private BoundedQueue queue;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 计数器
     */
    private static final AtomicInteger num = new AtomicInteger();

    public static FarmThread getInstance(BoundedQueue queue, String farmName, String productName){
        FarmThread farm = new FarmThread();
        farm.setQueue(queue);
        farm.setName(farmName);
        farm.setProductName(productName);
        return farm;
    }

    @Override
    public void run() {
        while(true){
            try {
                Food food = new Food();
                food.setName(productName + num.getAndIncrement());
                food.setProducer(this.getName());
                queue.add(food);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public BoundedQueue getQueue() {
        return queue;
    }

    public void setQueue(BoundedQueue queue) {
        this.queue = queue;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
