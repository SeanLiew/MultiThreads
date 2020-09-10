package exe;

import com.alibaba.fastjson.JSON;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * desc:
 * author: liuxiaozheng
 * time: 2019/11/24  13:46
 **/
public class ExeTest {
    private static final Logger logger = LoggerFactory.getLogger(ExeTest.class);


    private static final ThreadPoolExecutor executorPool = new ThreadPoolExecutor(3, 6, 50, TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(100),
            new DefaultThreadFactory("test_pool"),
            new ThreadPoolExecutor.CallerRunsPolicy()
    );

    static {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                logger.warn(String.format(
                        "executorPool, activeCount:%s,completedTaskCount:%s,corePoolSize:%s,aliveTime:%s,largestPoolSize:%s,maximumPoolSize:%s,poolSize:%s,taskCount:%s,queue.size:%s",
                        executorPool.getActiveCount(), executorPool.getCompletedTaskCount(), executorPool.getCorePoolSize(), executorPool.getKeepAliveTime(TimeUnit.SECONDS),
                        executorPool.getLargestPoolSize(), executorPool.getMaximumPoolSize(), executorPool.getPoolSize(), executorPool.getTaskCount(), executorPool.getQueue().size()));
            }
        }, 1, 2, TimeUnit.SECONDS);
    }

    @Test
    public void testCreate() throws Exception{


        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

    }


    @Test
    public void testCompletion() throws Exception{

        CompletionService<User> completionService = new ExecutorCompletionService<>(executorPool);

        for (int i=0;i<500;i++){

            completionService.submit(() -> {
                int num = (int)(Math.random() * 1000 + 1);
                try {
                    Thread.sleep(num);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                User dto = new User();
                logger.info("执行时间 {} 秒", num);
                dto.setUserName("执行时间" + num);
                return dto;
            });
        }
        for (int i=0;i<500;i++){

            Future<User> future = completionService.poll(200, TimeUnit.MILLISECONDS);
            if (future == null){
                logger.info("队列poll为空 i: {}", i);
                continue;
            }
            User dto = future.get();
            if (dto == null){
                logger.info("未获取到执行结果 i: {}");
                continue;
            }
            logger.info("获取到值 userName: {}", dto.getUserName());
        }


        try {
            Thread.sleep(10000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testCompletionOne() throws Exception{

        CompletionService<User> completionService = new ExecutorCompletionService<>(executorPool);

        completionService.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            User dto = new User();
            logger.info("执行时间 {} 秒", 1000);
            dto.setUserName("执行时间" + 1000);
            return dto;
        });
        long startTime = System.currentTimeMillis();
        logger.info("startTime:{}", startTime);
        Future<User> future = completionService.poll(-1, TimeUnit.MILLISECONDS);
        long endTime = System.currentTimeMillis();
        logger.info("endTime:{}", endTime);
        long usedTime = endTime - startTime;
        logger.info("usedTime:{}", usedTime);


        if (future == null){
            logger.info("队列poll为空");
            return;
        }
        User dto = future.get();
        if (dto == null){
            logger.info("未获取到执行结");
            return;
        }
        logger.info("获取到值 userName: {}", dto.getUserName());


        try {
            Thread.sleep(10000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testExe() throws Exception{

        executorPool.submit(() -> {
            System.out.println("001");
            System.out.println("002");
            System.out.println("003");
        });



    }
    @Test
    public void testFuture(){
        final User user = new User();

        Future<User> future = executorPool.submit(() ->{
            int num = (int)(Math.random() * 500 + 1);
            try {
                Thread.sleep(num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("执行了 {} ms", num);
            user.setUserAge("30");

        }, user);


        User userResult = null;
        try {
            userResult = future.get(50L, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("结果:{}", JSON.toJSON(userResult));

        Future<String> ageFuture = executorPool.submit(() -> {
            int num = (int)(Math.random() * 100 + 1);
            try {
                Thread.sleep(num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("执行了{} ms", num);
            return "30";
        });

        String age = null;
        try {
            age = ageFuture.get(200L, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("年龄结果, {}", JSON.toJSON(age));

        try {
            Thread.sleep(10000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    @Test
    public void testMultiFuture(){

        Future<String> ageFuture = executorPool.submit(() -> {
            int num = (int)(Math.random() * 100 + 1);
            try {
                Thread.sleep(num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("年龄 执行了{} ms", num);
            return "30";
        });

        Future<String> nameFuture = executorPool.submit(() -> {
            int num = (int)(Math.random() * 100 + 1);
            try {
                Thread.sleep(num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("查姓名 执行了{} ms", num);
            return "张三";
        });
        Future<String> picFuture = executorPool.submit(() -> {
            int num = (int)(Math.random() * 100 + 1);
            try {
                Thread.sleep(num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("查照片 执行了{} ms", num);
            return "http://pic";
        });

        String age = null;
        try {
            age = ageFuture.get(100L, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String name = null;
        try {
            name = nameFuture.get(100L, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String pic = null;
        try {
            pic = picFuture.get(100L, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("年龄结果, {}", age);
        logger.info("姓名结果, {}", name);
        logger.info("照片结果, {}", pic);
    }


    @Test
    public void testUserCompletion(){
        CompletionService<User> completionService = new ExecutorCompletionService<>(executorPool);

        completionService.submit(() -> {
            int num = (int)(Math.random() * 1000 + 1);
            try {
                Thread.sleep(num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("查年龄 执行了{} ms", num);
            User user = new User();
            user.setUserAge("30");
            return user;
        });

        completionService.submit(() -> {
            int num = (int)(Math.random() * 1000 + 1);
            try {
                Thread.sleep(num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("查姓名 执行了{} ms", num);
            User user = new User();
            user.setUserName("张三");
            return user;
        });

        completionService.submit(() -> {
            int num = (int)(Math.random() * 1000 + 1);
            try {
                Thread.sleep(num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("查照片 执行了{} ms", num);
            User user = new User();
            user.setUserPic("http://");
            return user;
        });

        long leftTime = 500;

        for (int i=0;i<3;i++){
            try {
                long startTime = System.currentTimeMillis();
                logger.info("startTime:{}", startTime);

                logger.info("leftTime:{}", leftTime);
//                if (leftTime <= 0){
//                    logger.info("无剩余时间，结束执行");
//                    return;
//                }
                Future<User> future = completionService.poll(leftTime, TimeUnit.MILLISECONDS);
                long endTime = System.currentTimeMillis();
                logger.info("endTime:{}", endTime);
                long usedTime = endTime - startTime;
                logger.info("usedTime:{}", usedTime);
                leftTime = leftTime - usedTime;
                if (future == null){
                    logger.info("队列poll为空 i: {}", i);
                    continue;
                }
                User user = future.get();
                if (user == null){
                    logger.info("未获取到执行结果 i: {}");
                    continue;
                }
                logger.info("获取到结果 user: {}", JSON.toJSON(user));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            Thread.sleep(10000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testTask() throws Exception{

    }

    @Test
    public void testSchedule(){
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

        scheduledExecutorService.schedule(() ->{

            System.out.println("5秒后输出");
        }, 5, TimeUnit.SECONDS);

        try {
            Thread.sleep(10000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRandom(){
        int i = (int)(Math.random()*100+1);
        logger.info("i = {}", i);
    }

    public static void main(String[] args) {

        System.out.println(Integer.SIZE);
    }

}
