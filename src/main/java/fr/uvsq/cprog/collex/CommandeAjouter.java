package fr.uvsq.cprog.collex;

public class CommandeAjouter implements Commande {
    private final String nom;
    private final String ip;

    public CommandeAjouter(String nom, String ip) {
        this.nom = nom;
        this.ip = ip;
    }

    public void execute(Dns dns, DnsTUI tui) {
        try{
            NomMachine machine = new NomMachine(nom);
            AdresseIP adresse = new AdresseIP(ip);
            dns.addItem(adresse, machine);
            tui.affiche("Ajouté avec succès");
        }catch(Exception e){
            tui.affiche("Erreur lors de l'ajout" + e.getMessage());
        }
    }
}
