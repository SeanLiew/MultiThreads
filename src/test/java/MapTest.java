import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

/**
 * desc:
 * author: liuxiaozheng
 * time: 2020/4/29  19:57
 **/
public class MapTest {
    /**
     * 存放交易账户的map
     */
//    private static Map<String, User> tradeAccountNoMap = new ConcurrentHashMap<>(1024 * 1024 * 2);
//    private static Map<String, User> tradeAccountNoMap = new Hashtable<>(1024 * 1024 * 2);
    private static Map<String, User> tradeAccountNoMap = new HashMap<>(1024 * 1024 * 10);
    private static Set<Integer> SET_KEY = new ConcurrentSkipListSet<>();


    private static AtomicInteger integer = new AtomicInteger();

    public static void main(String[] args) {
        for (int j = 0; j<5 ; j++) {
            Thread t = new Thread(()->{
                for (int i = 0; i<100000; i++) {
                    int andIncrement = integer.getAndIncrement();

                    String key = "unique_trade_acc_no_15002001000001_小争" + andIncrement;

                    tradeAccountNoMap.put(key, new User("22191106135510000" + andIncrement));

                    int h;
                    int hashCode = (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
                    SET_KEY.add(hashCode);
                    if (andIncrement % 1000 == 0){
                        try {
                            sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }, "线程" + j
            );

            t.start();
        }

        new Thread(()->{
            while (true){
                int size = tradeAccountNoMap.size();
                System.out.println("map_size:" + size);
                System.out.println("key_size:" + tradeAccountNoMap.keySet().size());
                System.out.println("set_size:" + SET_KEY.size());
                if (size % 1000 == 0){
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "计数线程"
        ).start();
    }
    @Test
    public void testUser(){
        User key = new User("22191106135510000");
        System.out.println(key);

        int h;
        int hashCode = (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
        System.out.println(hashCode);
    }

    private static class User{
        private String name;
        public User(String name){
            this.name = name;

        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
