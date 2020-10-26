package create;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author: liuxiaozheng
 * @Created: 2020-10-24-22:51
 * @date 2020-10-25 12:47
 * @author sean
 */
public class TestCreate implements Serializable {


    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    public static void main(String[] args) {
        new String("");
        String.valueOf("");
//        Connection connection = DriverManager.getConnection("");


        Map emptyMap = Collections.EMPTY_MAP;

        List<String> list = new ArrayList<>();

        Boolean.valueOf("");

        Boolean aBoolean = new Boolean("");

        Thread thread = new Thread();

        thread.equals(null);


    }

    public static void copy1(String src, String dst) throws IOException {
        InputStream in = new FileInputStream(src);
        try {
            OutputStream out = new FileOutputStream(dst);
            try {
                byte[] buffer = new byte[1024];
                int n;
                while((n = in.read(buffer)) >= 0){
                    out.write(buffer, 0, n);
                }
            } finally {
                out.close();
            }

        } finally {
            in.close();
        }
    }


    //实现 AutoCloseable 接口
    public static void copy(String src, String dst) throws IOException {
        try (
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dst);
        ){
            byte[] buffer = new byte[1024];
            int n;
            while((n = in.read(buffer)) >= 0){
                out.write(buffer, 0, n);
            }
        }
    }


    static String firstLineOfFile(String path) throws IOException{
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                return br.readLine();
        }
    }



}
