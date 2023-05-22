package dodat.client;

import dodat.server.ServerControl;
import dodat.user.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class ClientControl {
    private String serverHost = "localhost";
    private int serverPort = ServerControl.SERVER_PORT;
    private ClientView view;

    public ClientControl(ClientView view) {
        this.view = view;
        LoginListener loginListener = new LoginListener();
    }
    class LoginListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Socket socket = new Socket(serverHost, serverPort);

                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

                User user = view.getUser();
                System.out.println(user.getUsername());
                System.out.println(user.getPassword());
                outputStream.writeObject(user);
                System.out.println("Upload user!!!");

                String msg = dataInputStream.readUTF();
                System.out.println(msg);
                view.showMessage(msg);

                outputStream.close();
                socket.close();
            } catch (IOException e1) {
                throw new RuntimeException(e1);
            }
        }
    }
    public String getServerHost() {
        return serverHost;
    }
    public void setServerHost(String serverHost) {
        this.serverHost = serverHost;
    }
    public int getServerPort() {
        return serverPort;
    }
    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }
    public ClientView getView() {
        return view;
    }
    public void setView(ClientView view) {
        this.view = view;
    }
}