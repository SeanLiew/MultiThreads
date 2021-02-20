package create;

/**
 * desc:
 * author: liuxiaozheng
 * time: 2020/10/27  15:22
 **/
public final class CloneTest implements Cloneable{

    public static void main(String[] args) {
        CloneTest c = new CloneTest();

    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
