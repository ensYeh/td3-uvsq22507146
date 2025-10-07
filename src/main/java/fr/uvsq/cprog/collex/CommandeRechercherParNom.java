package fr.uvsq.cprog.collex;

public class CommandeRechercherParNom implements Commande {
    private final String nom;

    public CommandeRechercherParNom(String nom) {
        this.nom = nom;
    }

    public void execute(Dns dns, DnsTUI tui) {
        try{
            NomMachine machine = new NomMachine(nom);
            DnsItem item = dns.getItem(machine);
            if(item != null){
                tui.affiche(item.getAdresseIP().toString());
            }else{
                tui.affiche("Machine inconnue");
            }
        }catch(Exception e){
            tui.affiche("Nom invalide");
        }
    }
}
