import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientImpl extends UnicastRemoteObject implements ClientInterface {
    private String name;

    public ClientImpl(String name) throws RemoteException {
        this.name = name;
    }

    @Override
    public void notify(String message) throws RemoteException {
        System.out.println("[" + name + "] " + message);
    }
}
