package lu.pata.ccl;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;

public class CustomClassLoader extends URLClassLoader {

    static {
        registerAsParallelCapable();
    }

    public CustomClassLoader(String name, ClassLoader parent) {
        super(name, new URL[0], parent);
    }

    public CustomClassLoader(ClassLoader parent) {
        this("classpath", parent);
    }

    public CustomClassLoader() {
        this(Thread.currentThread().getContextClassLoader());
    }

    void add(URL url) {
        addURL(url);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        System.out.println("Loading class :" + name);
        return super.loadClass(name);
    }

    void appendToClassPathForInstrumentation(String path) throws IOException {
        System.out.println("Instrumentation: "+path);
        add(Paths.get(path).toRealPath().toUri().toURL());
    }
}
