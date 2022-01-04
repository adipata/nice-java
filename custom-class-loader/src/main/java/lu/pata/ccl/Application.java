package lu.pata.ccl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Application {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // -Djava.system.class.loader=lu.pata.test.CustomClassLoader

        ClassLoader cl=ClassLoader.getSystemClassLoader();
        System.out.println("System class loader: "+cl);

        System.out.println("Application class loader: "+Application.class.getClassLoader());

        cl.loadClass("ccl://ExtClass"); //Load class definition from binary file

        Class c=Class.forName("ExtClass",true,cl);
        Method cm=c.getDeclaredMethod("test",String.class);

        Object o=c.getDeclaredConstructor().newInstance();
        System.out.println("Loaded class: "+o);

        String r=(String)cm.invoke(o,"Adi");
        System.out.println("Response: "+r);
    }
}
