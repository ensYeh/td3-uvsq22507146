package fr.uvsq.cprog.collex;

public class CommandeErreur implements Commande {
    private final String message;

    public CommandeErreur(String message) {
        this.message = message;
    }

    public void execute(Dns dns, DnsTUI tui) {
        tui.affiche("Erreur : " + message);
    }
}

