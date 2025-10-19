package fr.uvsq.cprog.collex;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NomMachineTest {

    @Test
    public void testValidNom() {
        NomMachine nom = new NomMachine("pikachu.uvsq.fr");
        assertEquals("pikachu", nom.getNomMachine());
        assertEquals("uvsq.fr", nom.getNomDomaine());
        assertEquals("pikachu.uvsq.fr", nom.toString());
    }

    @Test
    public void testInvalidNom() {
        assertThrows(IllegalArgumentException.class, () -> new NomMachine("invalide_nom"));
    }

    @Test
    public void testNomEquality() {
        NomMachine nom1 = new NomMachine("pikachu.uvsq.fr");
        NomMachine nom2 = new NomMachine("pikachu.uvsq.fr");
        assertEquals(nom1, nom2);
    }

    @Test
    public void testNomHashCode() {
        NomMachine nom1 = new NomMachine("pikachu.uvsq.fr");
        NomMachine nom2 = new NomMachine("pikachu.uvsq.fr");
        assertEquals(nom1.hashCode(), nom2.hashCode());
    }
}

