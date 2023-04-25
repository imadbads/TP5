import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Client <host>");
            System.exit(1);
        }

        String host = args[0];

        try {
            Registry registry = LocateRegistry.getRegistry(host);
            Stock stock = (Stock) registry.lookup("Stock");

            // Enregistrement du client auprès du serveur
            ClientImpl client = new ClientImpl(host);
            stock.registerClient(client);

            // Boucle d'interaction avec l'utilisateur
            Scanner scanner = new Scanner(System.in);
            String command = "";
            while (!command.equals("quit")) {
                System.out.print("Entrez une commande (help pour afficher la liste des commandes) : ");
                command = scanner.nextLine();

                switch (command) {
                    case "help":
                        System.out.println("Liste des commandes disponibles :");
                        System.out.println("  - stock : Afficher la liste des produits");
                        System.out.println("  - get <produit> : Obtenir la quantité d'un produit");
                        System.out.println("  - add <produit> <quantité> : Ajouter une quantité d'un produit");
                        System.out.println("  - remove <produit> <quantité> : Retirer une quantité d'un produit");
                        System.out.println("  - quit : Quitter l'application");
                        break;
                    
                    case "get":
                        System.out.print("Nom du produit : ");
                        String product1 = scanner.nextLine();
                        System.out.println("Quantité de " + product1 + " : " + stock.getQuantity(product1));
                        break;
                    case "add":
                        System.out.print("Nom du produit : ");
                        String product2 = scanner.nextLine();
                        System.out.print("Quantité à ajouter : ");
                        int quantity1 = scanner.nextInt();
                        scanner.nextLine(); // Ignorer la fin de ligne
                        stock.addProduct(product2, quantity1);
                        break;
                    case "remove":
                        System.out.print("Nom du produit : ");
                        String product3 = scanner.nextLine();
                        System.out.print("Quantité à retirer : ");
                        int quantity2 = scanner.nextInt();
                        scanner.nextLine(); // Ignorer la fin de ligne
                        stock.removeProduct(product3, quantity2);
                        break;
                    case "quit":
                        System.out.println("Au revoir !");
                        break;
                    default:
                        System.out.println("Commande inconnue. Tapez 'help' pour afficher la liste des commandes.");
                }
            }

            // Désenregistrement du client auprès du serveur
            stock.unregisterClient(client);
        } catch (Exception e) {
            System.err.println("Erreur : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
