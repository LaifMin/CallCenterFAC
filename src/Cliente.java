

public class Cliente {

    private String nome;
    private String cognome;
    private String codice;
    private String indirizzo;
    private String citta;
    private String telefono;


    public Cliente(String nome, String cognome, String codice, String indirizzo, String citta, String telefono) {
        this.nome = nome;
        this.cognome = cognome;
        this.codice = codice;
        this.indirizzo = indirizzo;
        this.citta = citta;
        this.telefono = telefono;
        
    }



    public String getTelefono() {
        return telefono;
    }





    public String getNome() {
        return nome;
    }



    public void setNome(String nome) {
        this.nome = nome;
    }



    public String getCognome() {
        return cognome;
    }



    public void setCognome(String cognome) {
        this.cognome = cognome;
    }



    public String getCodice() {
        return codice;
    }



    public void setCodice(String codice) {
        this.codice = codice;
    }



    public String getIndirizzo() {
        return indirizzo;
    }



    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }



    public String getCitta() {
        return citta;
    }



    public void setCitta(String citta) {
        this.citta = citta;
    }



    


    


    
    
}
