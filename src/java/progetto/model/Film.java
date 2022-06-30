package progetto.model;

import java.util.Locale;
import java.util.Optional;

public class Film {
    private String id, nome, locandina, info, prezzo;

    public Film(String id, String nome, String locandina, String info, String prezzo) {
        this.id = id;
        this.nome = nome;
        this.locandina = locandina;
        this.info = info;
        this.prezzo = prezzo;
    }

    @Override
    public String toString() {
        return id + ',' + nome + ',' + locandina + ',' + info + ',' + prezzo;
    }

    public String getId() {
        return id.trim();
    }

    public String getLocandina() {
        return locandina;
    }

    public String getInfo() {
        return info;
    }

    public String getNome() {
        return nome;
    }

    public String getPrezzo() {
        return prezzo;
    }

    public int compareTo(Film daConfrontare){
        int i = this.nome.toLowerCase().compareTo(daConfrontare.getNome().toLowerCase());
        if(i!=0){
            return i;
        }
        int thisId = Integer.parseInt(this.id);
        int IDdaConfrontare  = Integer.parseInt((daConfrontare.getId()));
        return thisId - IDdaConfrontare;
    }

    /**
     * Funzione che data una stringa con le informazioni di un film, lo trasforma in un oggetto di tipo Film
     * @param filmString Stringa con le informazioni del film
     * @return Un oggetto Film creato tramite le informazioni nella stringa
     */
    public static Optional<Film> stringToFilm(String filmString){
        String[] datiFilm = filmString.split(",");
        if(datiFilm.length>2){
            Film elemento = new Film(datiFilm[0], datiFilm[1], datiFilm[2], datiFilm[3], datiFilm[4]);
            return Optional.of(elemento);
        }
        return Optional.empty() ;
    }
}


