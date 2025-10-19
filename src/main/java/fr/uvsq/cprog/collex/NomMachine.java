package fr.uvsq.cprog.collex;

import java.util.Objects;

public class NomMachine {
    private String nomMachine;
    private String nomDomaine;

    public NomMachine(String nomQualifie) {
        int index = nomQualifie.indexOf(".");
        if (index == -1) throw new IllegalArgumentException("Nom qualifié non valide");
        this.nomMachine = nomQualifie.substring(0, index);
        this.nomDomaine = nomQualifie.substring(index + 1);
    }

    public String getNomMachine() {
        return nomMachine;
    }

    public String getNomDomaine() {
        return nomDomaine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NomMachine)) return false;
        NomMachine other = (NomMachine) o;
        return nomMachine.equals(other.nomMachine) &&
                nomDomaine.equals(other.nomDomaine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomMachine, nomDomaine);
    }

    @Override
    public String toString() {
        return nomMachine + "." + nomDomaine;
    }


}
