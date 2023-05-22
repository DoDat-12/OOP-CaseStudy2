package dodat.server;

public class ServerRun{
    public static void main(String[] args){
        ServerView view = new ServerView();
        ServerControl controller = new ServerControl (view);
    }
}