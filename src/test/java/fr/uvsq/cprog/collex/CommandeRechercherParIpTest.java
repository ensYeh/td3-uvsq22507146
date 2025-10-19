package fr.uvsq.cprog.collex;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class CommandeRechercherParIpTest {

    @Test
    public void testExecuteFound() {
        Dns dns = mock(Dns.class);
        DnsTUI tui = mock(DnsTUI.class);

        AdresseIP ip = new AdresseIP("10.0.0.1");
        NomMachine nom = new NomMachine("pikachu.uvsq.fr");
        DnsItem item = new DnsItem(nom, ip);

        when(dns.getItem(ip)).thenReturn(item);

        new CommandeRechercherParIp("10.0.0.1").execute(dns, tui);
        verify(tui).affiche("pikachu.uvsq.fr");
    }

    @Test
    public void testExecuteNotFound() {
        Dns dns = mock(Dns.class);
        DnsTUI tui = mock(DnsTUI.class);

        when(dns.getItem((NomMachine) any())).thenReturn(null);

        new CommandeRechercherParIp("10.0.0.1").execute(dns, tui);
        verify(tui).affiche("Adresse IP inconnue");
    }
}

