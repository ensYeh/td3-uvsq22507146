package fr.uvsq.cprog.collex;

public class CommandeQuitter implements Commande {
    public void execute(Dns dns, DnsTUI tui) {
        tui.affiche("Fermeture de l'application");
        System.exit(0);
    }
}
