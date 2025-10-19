package fr.uvsq.cprog.collex;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.*;

public class Dns {
    private Map<NomMachine, AdresseIP> nomToIp = new HashMap<>();
    private Map<AdresseIP, NomMachine> ipToNom = new HashMap<>();
    private String dnsFilePath;

    public Dns() throws IOException {
        Properties prop = new Properties();
        try(InputStream in = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if(in == null) throw new IOException("Fichier de propriété introuvable");
            prop.load(in);
            dnsFilePath = prop.getProperty("dns.file");
        }
        List<String> lignes = Files.readAllLines(Paths.get(dnsFilePath));
        for(String ligne: lignes) {
            String[] parts = ligne.split(" ");
            if(parts.length == 2) {
                NomMachine nom = new NomMachine(parts[0]);
                AdresseIP ip = new AdresseIP(parts[1]);
                nomToIp.put(nom, ip);
                ipToNom.put(ip, nom);
            }
        }
    }

    //getItem par nom
    public DnsItem getItem(NomMachine nom) {
        AdresseIP ip = nomToIp.get(nom);
        if(ip == null) return null;
        return new DnsItem(nom, ip);
    }

    //getItem par ip
    public DnsItem getItem(AdresseIP ip) {
        NomMachine nom = ipToNom.get(ip);
        if(nom == null) return null;
        return new DnsItem(nom, ip);
    }

    public List<DnsItem> getItems(String nomDomaine) {
        List<DnsItem> resultats = new ArrayList<>();
        for(NomMachine nom: nomToIp.keySet()) {
            if(nom.getNomDomaine().equals(nomDomaine)) {
                resultats.add(new DnsItem(nom, nomToIp.get(nom)));
            }
        }
        return resultats;
    }

    public void addItem(AdresseIP ip, NomMachine nom) throws IOException{
        if(nomToIp.containsKey(nom) || ipToNom.containsKey(ip)) {
            throw new IllegalArgumentException("Nom ou Ip déjà présent dans la base");
        }

        nomToIp.put(nom, ip);
        ipToNom.put(ip, nom);

        List<String> lignes = new ArrayList<>();
        for (Map.Entry<NomMachine, AdresseIP> entry : nomToIp.entrySet()) {
            lignes.add(entry.getKey().getNomMachine() + "." + entry.getKey().getNomDomaine() + " " + entry.getValue().toString());
        }
        Files.write(Paths.get(dnsFilePath), lignes);
    }

}
