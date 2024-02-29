package commands;

public class HelloCommand implements Command{
    @Override
    public void run() {
        System.out.println("Hello World!");
    }
}
