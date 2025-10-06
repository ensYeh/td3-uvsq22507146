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

}
