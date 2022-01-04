package lu.pata.ccl;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
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
        System.out.println("Loading class : " + name);

        if(name.startsWith("ccl://")){
            try {
                byte[] classData=Files.readAllBytes(Path.of(name.substring(6)+".class"));
                return defineClass(name.substring(6),classData,0,classData.length);
            } catch (IOException e) {
                throw new ClassNotFoundException("Could not load class "+name+" : "+e.getMessage());
            }
        }

        return super.loadClass(name);
    }

    void appendToClassPathForInstrumentation(String path) throws IOException {
        System.out.println("Instrumentation: "+path);
        add(Paths.get(path).toRealPath().toUri().toURL());
    }
}
