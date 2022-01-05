package lu.pata.cva;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class Agent {
    public static void premain(String args, Instrumentation instrumentation){
        System.out.println("******* Arguments: "+args);

        instrumentation.addTransformer(new ClassFileTransformer() {
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
                System.out.println("Testing class: "+className);
                return classfileBuffer;
            }
        });
    }

    public static void agentmain(String args, Instrumentation inst) {
        System.out.println("******* AGENT");
    }
}
