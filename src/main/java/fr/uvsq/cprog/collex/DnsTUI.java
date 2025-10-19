package fr.uvsq.cprog.collex;

import java.util.Scanner;

public class DnsTUI {
    private final Scanner scanner = new Scanner(System.in);

    public Commande nextCommande() {
        System.out.println("> ");
        String input = scanner.nextLine().trim();

        if(input.equalsIgnoreCase("exit")){
            return new CommandeQuitter();
        }

        if(input.startsWith("add")){
            String[] parts = input.substring(4).trim().split(" ");
            if(parts.length == 2){
                String ip = parts[0];
                String nom = parts[1];
                return new CommandeAjouter(nom, ip);
            }else{
                return new CommandeErreur("Format attendu : add.nom_qualifie_domaine adr.es.se.ip");
            }
        }
        if (input.matches("\\d{1,3}(\\.\\d{1,3}){3}")) {
            return new CommandeRechercherParIp(input);
        }

        if (input.contains(".")) {
            return new CommandeRechercherParNom(input);
        }

        return new CommandeErreur("Commande non reconnue");
    }

    public void affiche(String message){
        System.out.println(message);
    }


}
