import java.util.*;

/**
 * desc:
 * author: liuxiaozheng
 * time: 2020/4/23  18:54
 **/
public class JvmTest {


    public static void main(String[] args) {


        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> yuan = new HashMap<>();
        yuan.put("type", 7);
        yuan.put("code", "c");
        Map<String, Object> jia = new HashMap<>();
        jia.put("type", 1);
        jia.put("code", "b");
        Map<String, Object> min = new HashMap<>();
        min.put("type", 6);
        min.put("code", "a");
        Map<String, Object> min2 = new HashMap<>();
        min2.put("type", 5);
        min2.put("code", "d");
        Map<String, Object> min3 = new HashMap<>();
        min3.put("type", 8);
        min3.put("code", "e");
        Map<String, Object> min4 = new HashMap<>();
        min4.put("type", 7);
        min4.put("code", "h");
        Map<String, Object> min5 = new HashMap<>();
        min5.put("type", 7);
        min5.put("code", "f");

        System.out.println("tttttttttttttttt"+min5.get("type"));

        list.add(min);//6 a
        list.add(min2);//5 d
        list.add(min3);//8 e
        list.add(min4);//7 h
        list.add(min5);//7 f
        list.add(jia);//1 b
        list.add(yuan);//7 c

        System.out.println(list);
        Collections.sort(list, new Comparator<Map<String, Object>>() {

            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                System.out.println("o1="+o1);
                System.out.println("o2="+o2);//== null ? "" : o1.get("name").toString()
                String a  = (o1.get("Tsype") == null ? "" : o1.get("TYspe").toString()).indexOf("7")+"";
                System.out.println("TYpe="+o1.get("type")+"     a="+a);
                String b  = (o2.get("TYpe") == null ? "" : o2.get("TYpe").toString()).indexOf("7")+"";

                System.out.println("type="+o2.get("type")+"     b="+b);
                System.out.println();
                if (a.equals(b)) {
                    System.out.println("o1.code="+o1.get("code"));
                    System.out.println("o2.code="+o2.get("code"));
                    System.out.println((o2.get("code")+"").compareTo(o1.get("code")+ ""));
                    System.out.println("===============================================");
                    return (o2.get("code")+"").compareTo(o1.get("code")+ "");
                }
                System.out.println( b.compareTo(a));
                System.out.println("---------------------------------------------------");
                return b.compareTo(a);
            }
        });
        System.out.println("最后显示的list="+list);
    }

}
