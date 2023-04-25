import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            Product stock = new Product(null, 0);

            // Enregistrement du serveur RMI
            Registry registry = LocateRegistry.createRegistry(1099);
            Naming.rebind("Stock", stock);

            System.out.println("Serveur RMI démarré");

        } catch (RemoteException e) {
            System.err.println("Erreur : " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }
}
