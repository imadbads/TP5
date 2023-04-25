import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Product extends UnicastRemoteObject implements Stock {
    private String name;
    private int quantity;
    private List<ClientInterface> clients;
    private Map<String, Integer> stock;

    public Product(String name, int quantity) throws RemoteException {
        this.name = name;
        this.quantity = quantity;
        this.clients = new ArrayList<>();
    }

    @Override
    public HashMap getAllProducts() throws RemoteException {
        if (stock.isEmpty()) return null;
        else return new HashMap();
    }

    public void registerClient(ClientInterface client) throws RemoteException {
        clients.add(client);
    }

    public void unregisterClient(ClientInterface client) throws RemoteException {
        clients.remove(client);
    }

    @Override
    public void addProduct(String productName, int quantity) throws RemoteException {
        Integer oldQuantity = stock.get(productName);
        if (oldQuantity == null) {
            oldQuantity = 0;
        }
        stock.put(productName, oldQuantity + quantity);

        notifyClients(productName + " : " + quantity + " ajouté(s) au stock");
    }

    @Override
    public void removeProduct(String productName, int quantity) throws RemoteException {
        Integer oldQuantity = stock.get(productName);
        if (oldQuantity == null) {
            oldQuantity = 0;
        }
        stock.put(productName, Math.max(oldQuantity - quantity, 0));

        notifyClients(productName + " : " + quantity + " retiré(s) du stock");
    }


    @Override
    public int getQuantity(String productName) throws RemoteException {
        Integer quantity = stock.get(productName);
        return quantity != null ? quantity : 0;
    }

    public String getName() throws RemoteException {
        return name;
    }

    private void notifyClients(String message) throws RemoteException {
        for (ClientInterface client : clients) {
            client.notify(message);
        }
    }
}
