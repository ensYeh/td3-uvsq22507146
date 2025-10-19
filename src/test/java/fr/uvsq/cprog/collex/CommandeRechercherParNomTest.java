package fr.uvsq.cprog.collex;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class CommandeRechercherParNomTest {

    @Test
    public void testExecuteFound() {
        Dns dns = mock(Dns.class);
        DnsTUI tui = mock(DnsTUI.class);

        AdresseIP ip = new AdresseIP("10.0.0.1");
        NomMachine nom = new NomMachine("pikachu.uvsq.fr");
        DnsItem item = new DnsItem(nom, ip);

        when(dns.getItem(nom)).thenReturn(item);

        new CommandeRechercherParNom("pikachu.uvsq.fr").execute(dns, tui);
        verify(tui).affiche("10.0.0.1");
    }

    @Test
    public void testExecuteNotFound() {
        Dns dns = mock(Dns.class);
        DnsTUI tui = mock(DnsTUI.class);

        when(dns.getItem((AdresseIP) any())).thenReturn(null);

        new CommandeRechercherParNom("unknown.domain").execute(dns, tui);
        verify(tui).affiche("Machine inconnue");
    }
}

