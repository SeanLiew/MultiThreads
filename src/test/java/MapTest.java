import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
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
    private static Map<String, User> tradeAccountNoMap = new HashMap<>(1024 * 1024 * 10);


    private static AtomicInteger integer = new AtomicInteger();

    public static void main(String[] args) {
        for (int j = 0; j<5 ; j++) {
            Thread t = new Thread(()->{
                for (int i = 0; i<100000; i++) {
                    int andIncrement = integer.getAndIncrement();

                    tradeAccountNoMap.put("unique_trade_acc_no_15002001000001_小争" + andIncrement, new User("22191106135510000" + andIncrement));
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
                System.out.println("size:" + size);
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
    public void testMap(){

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
