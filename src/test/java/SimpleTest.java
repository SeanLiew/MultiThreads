import org.junit.Test;


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
}
