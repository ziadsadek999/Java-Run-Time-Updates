import commands.Command;
import commands.CommandsMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;

public class Main {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, IOException {
        CommandsMap.instantiate();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        loop:
        while (true) {
            String s = br.readLine();
            switch (s) {
                case "b":
                    runCommand("bye");
                    break;
                case "h":
                    runCommand("hello");
                    break;
                case "u":
                    // Make your updates to the class and build the project to
                    // create the .class files, then run this command.
                    // It will read the new byte code from the .class files and write it in memory.
                    System.out.println("Add command name:");
                    String commandName = br.readLine();
                    updateCommand(commandName);
                    break;
                case "e":
                    break loop;
            }
        }
    }

    public static void runCommand(String s) throws InstantiationException, IllegalAccessException {
        Command command = (Command) CommandsMap.getClass(s).newInstance();
        command.run();
    }

    public static void updateCommand(String commandName) throws InstantiationException, IllegalAccessException {
        ClassPool pool = ClassPool.getDefault();
        // this line defines the root class used to query classes. so when
        // we access a command to edit it, we write its name relative to the Main class,
        // that's why the format is commands.CommandName
        pool.insertClassPath(new ClassClassPath(Main.class));
        String className = CommandsMap.getCommand(commandName).getName();
        try {
            CtClass ctClass = pool.get(className);
            // That's the compiled class byte code
            byte[] byteCode = ctClass.toBytecode();
            CommandsMap.updateCommand(commandName, byteCode);
            System.out.println("Command updated!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
