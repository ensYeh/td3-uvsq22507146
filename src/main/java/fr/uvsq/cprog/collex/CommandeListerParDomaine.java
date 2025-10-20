package fr.uvsq.cprog.collex;

import java.util.List;

public class CommandeListerParDomaine implements Commande {
    private final String domaine;

    public CommandeListerParDomaine(String domaine) {
        this.domaine = domaine;
    }

    @Override
    public void execute(Dns dns, DnsTUI tui) {
        List<DnsItem> machines = dns.listerParDomaine(domaine);
        if (machines.isEmpty()) {
            tui.affiche("Aucune machine trouvée pour le domaine : " + domaine);
        } else {
            for (DnsItem item : machines) {
                String ip = item.getAdresseIP().toString();
                String fqdn = item.getNomMachine().toString();
                tui.affiche(ip + " " + fqdn);
            }
        }
    }
}

