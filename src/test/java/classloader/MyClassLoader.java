package classloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * desc:
 * author: liuxiaozheng
 * time: 2020/9/8  17:38
 **/
public class MyClassLoader extends ClassLoader {



    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File f = new File(MyClassLoader.class.getResource("/").getPath());
        String myPath = f.getPath() + "\\" + name +  ".class";
        System.out.println(myPath);
        byte[] classBytes = null;
        FileInputStream in = null;

        try {
            File file = new File(myPath);
            in = new FileInputStream(file);
            classBytes = new byte[(int) file.length()];
            in.read(classBytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Class<?> clazz = defineClass(name, classBytes, 0, classBytes.length);
        return clazz;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
        if ("TestStaticBlock.class".equals(fileName)){
            File f = new File(MyClassLoader.class.getResource("/").getPath());
            fileName = f.getPath() + "\\classloader\\" + fileName;
            System.out.println(fileName);
        }
        byte[] classBytes = null;
        FileInputStream in = null;

        try {
            File file = new File(fileName);
            in = new FileInputStream(file);
            classBytes = new byte[(int) file.length()];
            in.read(classBytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println();
        Class<?> clazz = defineClass(name, classBytes, 0, classBytes.length);
        return clazz;
    }
}
