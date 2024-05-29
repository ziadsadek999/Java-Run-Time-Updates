package commands;

public class ByeCommand implements Command {
    @Override
    public void run() {
        System.out.println("Bye World!");
    }
}
