package fr.uvsq.cprog.collex;

public class AdresseIP {
    private int[] octets = new int[4];

    public AdresseIP(String ip) {
        String[] parts = ip.split("\\.");
        if (parts.length != 4) throw new IllegalArgumentException("Adresse IP invalide");
        for (int i = 0; i < 4; i++) {
            octets[i] = Integer.parseInt(parts[i]);
        }
    }

    public String toString() {
        return String.format("%d.%d.%d.%d", octets[0], octets[1], octets[2], octets[3]);
    }

}
