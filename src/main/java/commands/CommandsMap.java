package commands;

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


//    public static void replace(String className, String commandName, String byteString) {
//        byte[] byteArray = Base64.getDecoder().decode(byteString);
//        MyClassLoader loader = new MyClassLoader();
//        Class newCommand = loader.loadClass(byteArray, className);
//        cmdMap.put(commandName, newCommand);
//        System.out.println("replaced");
//    }

    public static void remove(String commandName) {
        cmdMap.remove(commandName);
        System.out.println("removed");
    }

    public static Class<?> getClass(String key) {
        return cmdMap.get(key);
    }
}
