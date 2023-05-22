package dodat.server;

import dodat.user.User;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerControl {
    public static final int SERVER_PORT = 8080;
    private ServerView view;
    private ServerSocket myServer;
    public ServerControl(ServerView view) {
        this.view = view;

        try {
            openServer(SERVER_PORT);
            try {
                listening();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            myServer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void openServer(int serverPort) throws IOException {
        this.myServer = new ServerSocket(serverPort);
        System.out.println("Server open successfully!!!");
    }

    public void listening() throws IOException, ClassNotFoundException {
        Socket socket = this.myServer.accept();

        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

        User user = (User) inputStream.readObject();
        System.out.println("Receive Object!!!");
        System.out.println(user.getUsername() + " and " + user.getPassword());

        String msg;
        if (checkUser(user)) msg = "Login successfully!";
        else msg = "Invalid username and/or password!";
        this.view.showMessage(msg);
        dataOutputStream.writeUTF(msg);
    }
    public boolean checkUser(User user) {
        List<User> users = new ArrayList<>();
        users.add(new User("0987654321", "q2w2e3"));
        users.add(new User("0983313567", "ki98u7"));
        users.add(new User("0912345678", "ngaythu5"));
        users.add(new User("0987452100", "so1dcv"));

        return users.contains(user);
    }
}
