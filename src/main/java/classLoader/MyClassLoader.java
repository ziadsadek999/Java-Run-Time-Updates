package classLoader;

public class MyClassLoader extends ClassLoader {
    public MyClassLoader(ClassLoader classLoader) {
        super(classLoader);
    }

    public Class<?> loadClass(byte[] byteCode, String className) {
        return defineClass(className, byteCode, 0, byteCode.length);
    }
}

