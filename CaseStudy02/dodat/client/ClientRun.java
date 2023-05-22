package dodat.client;

public class ClientRun {
    public static void main(String[] args) {
        ClientView view = new ClientView();
        ClientControl controller = new ClientControl(view);
        view.setVisible(true);
    }
}