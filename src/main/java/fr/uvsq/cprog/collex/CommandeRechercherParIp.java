package fr.uvsq.cprog.collex;

public class CommandeRechercherParIp implements Commande {
    private final String ip;

    public CommandeRechercherParIp(String ip) {
        this.ip = ip;
    }

    public void execute(Dns dns, DnsTUI tui) {
        try {
            AdresseIP adresse = new AdresseIP(ip);
            DnsItem item = dns.getItem(adresse);
            if (item != null) {
                tui.affiche(item.getNomMachine().toString());
            } else {
                tui.affiche("Adresse IP inconnue");
            }
        } catch (Exception e) {
            tui.affiche("Adresse IP invalide");
        }
    }
}

