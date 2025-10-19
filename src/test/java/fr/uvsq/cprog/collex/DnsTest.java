package fr.uvsq.cprog.collex;

import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

public class DnsTest {
    private Path tempFile;
    private Dns dns;

    @BeforeEach
    public void setup() throws IOException {
        tempFile = Files.createTempFile("dns-test", ".txt");

        Properties props = new Properties();
        props.setProperty("dns.file", tempFile.toString());
        try (OutputStream out = Files.newOutputStream(Paths.get("src/test/resources/config.properties"))) {
            props.store(out, null);
        }

        dns = new Dns() {
            {
                this.dnsFilePath = tempFile.toString(); // override file path manually
            }
        };
    }

    @AfterEach
    public void teardown() throws IOException {
        Files.deleteIfExists(tempFile);
    }

    @Test
    public void testAddAndGetItem() throws IOException {
        AdresseIP ip = new AdresseIP("10.0.0.1");
        NomMachine nom = new NomMachine("pikachu.uvsq.fr");

        dns.addItem(ip, nom);

        DnsItem itemByNom = dns.getItem(nom);
        assertNotNull(itemByNom);
        assertEquals(ip, itemByNom.getAdresseIP());

        DnsItem itemByIp = dns.getItem(ip);
        assertNotNull(itemByIp);
        assertEquals(nom, itemByIp.getNomMachine());
    }

    @Test
    public void testDuplicateAdditionThrows() throws IOException {
        AdresseIP ip = new AdresseIP("10.0.0.1");
        NomMachine nom = new NomMachine("pikachu.uvsq.fr");

        dns.addItem(ip, nom);
        assertThrows(IllegalArgumentException.class, () -> dns.addItem(ip, nom));
    }
}

