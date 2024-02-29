package commands;

import classLoader.MyClassLoader;

import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


public class CommandsMap {
    private static ConcurrentMap<String, Class<?>> cmdMap;
    private static ConcurrentMap<String, Class<?>> MapMap;

    //Each new function you create, you must add it below
    public static void instantiate() {
        cmdMap = new ConcurrentHashMap<>();
        cmdMap.put("hello", HelloCommand.class);
        cmdMap.put("bye", ByeCommand.class);
    }



    public static void updateCommand(String className, String commandName, String byteString) {
        // decoding the string back to byte array
        byte[] byteArray = Base64.getDecoder().decode(byteString);
        MyClassLoader loader = new MyClassLoader();
        // This method loads a class on the fly given its byte code.
        // Usually it is called when an object is created as it reads
        // the byte code from the memory to load the class,
        // create an instance of it then run it.
        // But here we give it the byte code directly instead of reading it from memory.
        Class<?> newCommand = loader.loadClass(byteArray, className);
        cmdMap.put(commandName, newCommand);
    }

    public static void deleteCommand(String commandName) {
        cmdMap.remove(commandName);
    }
    public static void addCommand(String className, String commandName, String byteString) {
        updateCommand(className,commandName,byteString);
    }

    public static Class<?> getClass(String key) {
        return cmdMap.get(key);
    }
}
