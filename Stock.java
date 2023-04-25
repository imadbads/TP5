import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Stock extends Remote {
    // Consultation du stock
    int getQuantity(String productName) throws RemoteException;
    HashMap getAllProducts() throws RemoteException;

    // Modification du stock
    void addProduct(String productName, int quantity) throws RemoteException;
    void removeProduct(String productName, int quantity) throws RemoteException;

    // Enregistrement des clients
    void registerClient(ClientInterface client) throws RemoteException;
    void unregisterClient(ClientInterface client) throws RemoteException;
}

