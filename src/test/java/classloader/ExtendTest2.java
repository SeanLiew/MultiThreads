package classloader;

/**
 * desc:
 * author: liuxiaozheng
 * time: 2020/9/9  11:25
 **/
class A3{
    public A3() {
        System.out.println("A");
    }
}
class B3 extends A3{
    public B3() {
        System.out.println("B");
    }
}
class C3 extends B3 {
    private D3 d1 = new D3("d1");
    private D3 d2 = new D3("d2");
    public C3() {
        System.out.println("C");
    }
}
class D3 {
    public D3(String str) {
        System.out.println("D "+str);
    }
}

public class ExtendTest2 {

    public static void main(String[] args) {
        C3 c = new C3();

    }
}
