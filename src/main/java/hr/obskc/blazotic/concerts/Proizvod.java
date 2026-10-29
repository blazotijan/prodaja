package hr.obskc.blazotic.concerts;

import java.util.Locale;

public class Proizvod {

    private static int brojProizvoda = 0;

    private final String sifra;
    private String naziv;
    private double cijena;
    private int kolicina;

    public Proizvod(String naziv, double cijena, int kolicina) {
        setNaziv(naziv);
        setCijena(cijena);
        provjeriKolicinu(kolicina);
        this.kolicina = kolicina;

        brojProizvoda++;
        this.sifra = "P" + String.format(Locale.ROOT, "%03d", brojProizvoda);
    }

    public Proizvod(String naziv, double cijena) {
        this(naziv, cijena, 0);
    }

    public String getSifra() {
        return sifra;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        if (naziv == null || naziv.trim().isEmpty()) {
            throw new IllegalArgumentException("Naziv ne smije biti null ni prazan.");
        }

        this.naziv = naziv.trim();
    }

    public double getCijena() {
        return cijena;
    }

    public void setCijena(double cijena) {
        if (!Double.isFinite(cijena) || cijena < 0) {
            throw new IllegalArgumentException("Cijena mora biti konačan broj koji nije negativan.");
        }

        this.cijena = cijena;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void dodaj(int kolicina) {
        provjeriKolicinu(kolicina);
        if (kolicina > Integer.MAX_VALUE - this.kolicina) {
            throw new IllegalArgumentException("Ukupna količina prelazi najveću dopuštenu vrijednost.");
        }

        this.kolicina += kolicina;
    }

    public void prodaj(int kolicina) {
        provjeriKolicinu(kolicina);
        if (kolicina > this.kolicina) {
            throw new IllegalArgumentException(
                    "Nema dovoljno proizvoda na skladištu. Dostupno: " + this.kolicina + ", traženo: " + kolicina + "."
            );
        }

        this.kolicina -= kolicina;
    }

    public double ukupnaVrijednost() {
        return cijena * kolicina;
    }

    public double ukupnaVrijednost(double popust) {
        if (!Double.isFinite(popust) || popust < 0 || popust > 100) {
            throw new IllegalArgumentException("Popust mora biti između 0 i 100 posto.");
        }

        return ukupnaVrijednost() * (1 - popust / 100);
    }

    public String opis() {
        return sifra + " | " + naziv + " | " + String.format(Locale.ROOT, "%.2f", cijena)
                + " EUR | " + kolicina + " kom";
    }

    public static int getBrojProizvoda() {
        return brojProizvoda;
    }

    private static void provjeriKolicinu(int kolicina) {
        if (kolicina < 0) {
            throw new IllegalArgumentException("Količina ne smije biti negativna.");
        }
    }
}
