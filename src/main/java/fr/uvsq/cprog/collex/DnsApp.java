package fr.uvsq.cprog.collex;

import java.io.IOException;
import java.nio.file.*;
import java.util.Properties;

public class DnsApp {
    private final Dns dns;
    private final DnsTUI tui;

    public DnsApp() throws IOException {
        this.dns = new Dns();
        this.tui = new DnsTUI();
    }

    public void run() {
        boolean continuer = true;

        while (continuer) {
            Commande commande = tui.nextCommande();

            if (commande == null) {
                tui.affiche("Commande invalide.");
                continue;
            }

            try {
                commande.execute(dns, tui);

                if (commande instanceof CommandeQuitter) {
                    continuer = false;
                }
            } catch (Exception e) {
                tui.affiche("Erreur : " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("Fichier DNS : " + Paths.get("donnees_dns.txt").toAbsolutePath());
            new DnsApp().run();
        } catch (IOException e) {
            System.err.println("Erreur au démarrage : " + e.getMessage());
        }
    }
}
