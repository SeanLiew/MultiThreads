import org.junit.Test;

/**
 * desc:
 * author: liuxiaozheng
 * time: 2020/1/9  10:04
 **/
public class ThreadLocalTest {

    ThreadLocal<String> threadLocal = new ThreadLocal<>();

    @Test
    public void test (){

        threadLocal.set("qweqwewqe");

        String s = threadLocal.get();

        System.out.println(s);

    }

}
