package commands;

import classLoader.MyClassLoader;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


public class CommandsMap {
    private static ConcurrentMap<String, Class<?>> cmdMap;

    //Each new function you create, you must add it below
    public static void instantiate() {
        cmdMap = new ConcurrentHashMap<>();
        cmdMap.put("hello", HelloCommand.class);
        cmdMap.put("bye", ByeCommand.class);
    }

    public static Class<?> getCommand(String commandName) {
        return cmdMap.get(commandName);
    }

    public static void updateCommand(String commandName, byte[] byteCode) {
        // This method loads a class on the fly given its byte code.
        // Usually it is called when an object is created as it reads
        // the byte code from the memory to load the class,
        // create an instance of it then run it.
        // But here we give it the byte code directly instead of reading it from memory.
        MyClassLoader loader = new MyClassLoader(CommandsMap.class.getClassLoader());
        String className = CommandsMap.getCommand(commandName).getName();
        Class<?> newCommand = loader.loadClass(byteCode, className);
        cmdMap.put(commandName, newCommand);
    }

    public static void deleteCommand(String commandName) {
        cmdMap.remove(commandName);
    }

    public static void addCommand(String className, byte[] byteCode) {
        updateCommand(className, byteCode);
    }

    public static Class<?> getClass(String key) {
        return cmdMap.get(key);
    }
}
