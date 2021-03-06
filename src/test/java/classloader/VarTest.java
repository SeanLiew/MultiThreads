package classloader;

/**
 * desc:
 * author: liuxiaozheng
 * time: 2020/9/9  11:22
 **/

class A2{
    B2 b2 = new B2();
    static{
        System.out.println("A static");
    }
    public A2() {
        System.out.println("A2()");
    }
}
class B2{
    C2 c2 = new C2();
    D2 d2 = new D2();
    static{
        System.out.println("B static");
    }
    public B2() {
        System.out.println("B2()");
    }
}
class C2{
    static{
        System.out.println("C static");
    }
    public C2() {
        System.out.println("C2()");
    }
}
class D2{
    static{
        System.out.println("D static");
    }
    public D2() {
        System.out.println("D2()");
    }
}

public class VarTest {
    public static void main(String[] args) {
        A2 a2 = new A2();

    }
}
