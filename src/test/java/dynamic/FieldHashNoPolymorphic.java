package dynamic;

/**
 * desc:
 * author: liuxiaozheng
 * time: 2020/6/3  16:59
 **/
public class FieldHashNoPolymorphic {
    static class Father{
        public int money = 1;

        public Father(){
            money = 2;
            showMoney();
        }

        public void showMoney(){
            System.out.println("father,money: " + money);
        }
    }

    static class Son extends Father{
        public int money;

        public Son(){
            money = 4;
            showMoney();
        }

        public void showMoney(){
            System.out.println("son,money: " + money);
        }
    }

    public static void main(String[] args) {
        Father gay = new Son();

        System.out.println("this gay has " + gay.money);
    }
}
