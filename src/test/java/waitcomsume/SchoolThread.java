package waitcomsume;

import utils.DateUtilsCon;

/**
 * desc:
 * author: liuxiaozheng
 * time: 2020/4/17  10:38
 **/
public class SchoolThread extends Thread{

    /**
     * 队列
     */
    private BoundedQueue queue;

    private static final String LOGGER = "%s: %s 吃了%s 生产的 %s";


    public static SchoolThread getInstance(BoundedQueue queue, String schoolName){
        SchoolThread farm = new SchoolThread();
        farm.setQueue(queue);
        farm.setName(schoolName);
        return farm;
    }

    @Override
    public void run() {
        while(true){
            try {

                Food food = queue.remove();
                String log = String.format(LOGGER, DateUtilsCon.getCurrTimestamp(), this.getName(), food.getProducer(), food.getName());
                System.out.println(log);
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

}
