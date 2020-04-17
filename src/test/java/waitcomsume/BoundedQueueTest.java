package waitcomsume;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * desc:
 * author: liuxiaozheng
 * time: 2020/4/17  10:12
 **/
public class BoundedQueueTest {
    private Integer[] items;

    @Test
    public void testQueue(){
        items = new Integer[4];
        System.out.println(JSON.toJSONString(items));
        System.out.println(items.length);

        items[1] = 2;
        System.out.println(JSON.toJSONString(items));
        System.out.println(items.length);

        Queue q = new LinkedBlockingQueue<>();
    }

    public static void main(String[] args) {
        BoundedQueue queue = BoundedQueue.getInstance(2048);

        FarmThread pkFarm = FarmThread.getInstance(queue, "北京农场", "苹果");
        FarmThread njFarm = FarmThread.getInstance(queue, "南京农场", "水蜜桃");
        FarmThread xaFarm = FarmThread.getInstance(queue, "西安农场", "冬瓜");
        FarmThread xjFarm = FarmThread.getInstance(queue, "新疆农场", "哈密瓜");
        FarmThread hljFarm = FarmThread.getInstance(queue, "黑龙江农场", "黑加伦");
        FarmThread ahFarm = FarmThread.getInstance(queue, "安徽农场", "黄瓜");
        FarmThread hnFarm = FarmThread.getInstance(queue, "海南农场", "香蕉");

        pkFarm.start();
        njFarm.start();
        xaFarm.start();
        xjFarm.start();
        hljFarm.start();
        ahFarm.start();
        hnFarm.start();

        SchoolThread lxSchool = SchoolThread.getInstance(queue, "利辛一中");
        SchoolThread bjkjSchool = SchoolThread.getInstance(queue, "北京科技大学");

        lxSchool.start();
        bjkjSchool.start();
    }
}
