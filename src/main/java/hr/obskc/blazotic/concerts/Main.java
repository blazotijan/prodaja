
package hr.obskc.blazotic.concerts;

public class Main {

    public static void main(String[] args) {
        Proizvod caj = new Proizvod("Čaj od kamilice", 3.50, 12);
        Proizvod kava = new Proizvod("Kava", 8.25);
        Proizvod secer = new Proizvod("Šećer", 1.20, 30);

        caj.dodaj(5);
        caj.prodaj(5);
        kava.dodaj(10);
        secer.prodaj(4);
        kava.setCijena(8.50);

        System.out.println("Proizvodi:");

        Proizvod[] proizvodi = {caj, kava, secer};

        for (Proizvod proizvod : proizvodi) {
            System.out.println(proizvod.opis());
        }

        System.out.println(
            "Naziv proizvoda " + caj.getSifra() + ": " + caj.getNaziv()
        );

        System.out.println("Cijena kave: " + kava.getCijena() + " EUR");
        System.out.println(
            "Količina šećera na skladištu: " + secer.getKolicina() + " kom"
        );

        System.out.printf(
            "Ukupna vrijednost čaja: %.2f EUR%n",
            caj.ukupnaVrijednost()
        );

        System.out.printf(
            "Vrijednost kave uz 10 %% popusta: %.2f EUR%n",
            kava.ukupnaVrijednost(10)
        );

        System.out.println(
            "Broj stvorenih proizvoda: " + Proizvod.getBrojProizvoda()
        );

        try {
            caj.prodaj(1000);
        } catch (IllegalArgumentException e) {
            System.out.println("Odbijeno: " + e.getMessage());
        }

        try {
            secer.dodaj(-2);
        } catch (IllegalArgumentException e) {
            System.out.println("Odbijeno: " + e.getMessage());
        }

        try {
            secer.setNaziv("   ");
        } catch (IllegalArgumentException e) {
            System.out.println("Odbijeno: " + e.getMessage());
        }

        try {
            kava.setCijena(-2);
        } catch (IllegalArgumentException e) {
            System.out.println("Odbijeno: " + e.getMessage());
        }
    }
}
