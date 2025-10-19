package fr.uvsq.cprog.collex;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class CommandeAjouterTest {

    @Test
    public void testExecuteSuccess() throws Exception {
        Dns dns = mock(Dns.class);
        DnsTUI tui = mock(DnsTUI.class);

        CommandeAjouter cmd = new CommandeAjouter("pikachu.uvsq.fr", "10.0.0.1");
        cmd.execute(dns, tui);

        verify(dns).addItem(new AdresseIP("10.0.0.1"), new NomMachine("pikachu.uvsq.fr"));
        verify(tui).affiche("Ajouté avec succès");
    }

    @Test
    public void testExecuteFailure() throws Exception {
        Dns dns = mock(Dns.class);
        DnsTUI tui = mock(DnsTUI.class);

        doThrow(new IllegalArgumentException("doublon")).when(dns)
                .addItem(any(AdresseIP.class), any(NomMachine.class));

        CommandeAjouter cmd = new CommandeAjouter("pikachu.uvsq.fr", "10.0.0.1");
        cmd.execute(dns, tui);

        verify(tui).affiche(contains("Erreur lors de l'ajout"));
    }
}

