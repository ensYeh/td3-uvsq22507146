package fr.uvsq.cprog.collex;

public class NomMachine {
    private String nomMachine;
    private String nomDomaine;

    public NomMachine(String nomQualifie) {
        int index = nomQualifie.indexOf(".");
        if (index != -1) throw new IllegalArgumentException("Nom qualifié non valide");
        this.nomMachine = nomQualifie.substring(0, index);
        this.nomDomaine = nomQualifie.substring(index + 1);
    }

    public String getNomMachine() {
        return nomMachine;
    }

    public String getNomDomaine() {
        return nomDomaine;
    }
}
