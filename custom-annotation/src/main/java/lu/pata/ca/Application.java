package lu.pata.ca;

import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.util.Set;

public class Application {
    public static void main(String[] args) {
        Reflections reflections=new Reflections(
                new ConfigurationBuilder()
                        .setUrls(ClasspathHelper.forPackage("lu.pata.ca"))
                        .setScanners(Scanners.TypesAnnotated)
        );
        Set<Class<?>> classes=reflections.getTypesAnnotatedWith(Apa.class);

        for(Class c:classes){
            System.out.println("For class "+c.getName());
            Apa apa=(Apa)c.getAnnotation(Apa.class);
            System.out.println(apa.value());
        }
    }
}
