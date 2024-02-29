import commands.Command;
import commands.CommandsMap;

public class Main {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        CommandsMap.instantiate();
        runCommand("hello");
        runCommand("bye");
    }
    public static  void runCommand(String s) throws InstantiationException, IllegalAccessException {
        Command command = (Command) CommandsMap.getClass(s).newInstance();
        command.run();
    }
}
