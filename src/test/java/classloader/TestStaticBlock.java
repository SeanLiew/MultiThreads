package classloader;

import org.junit.Test;

/**
 * desc:
 * author: liuxiaozheng
 * time: 2020/9/8  17:45
 **/
public class TestStaticBlock {
    static{
        System.out.println("static block init");
    }

    public static void main(String[] args) {

    }

    @Test
    public void test(){
        //new TestStaticBlock();

        Class<?> class0 = TestStaticBlock.class;
        try {
            System.out.println(class0.getClassLoader() instanceof MyClassLoader);
            Class<?> class1 = class0.getClassLoader().loadClass("classloader.TestStaticBlock");

            ClassLoader classLoader = new MyClassLoader();
            Class<?> class2 = classLoader.loadClass("classloader.TestStaticBlock");

            System.out.println(class1.hashCode());
            System.out.println(class2.hashCode());
            System.out.println(class1.equals(class2));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
