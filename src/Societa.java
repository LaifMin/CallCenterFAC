import java.util.HashMap;
import java.util.Date;

public class Societa {
    private HashMap<String, Dipendente> dipendenti;
    private HashMap<String, Cliente> clienti;
    private HashMap<String, String> telefonate;

    public Societa() {
        this.dipendenti = new HashMap<>();
        this.clienti = new HashMap<>();
        this.telefonate = new HashMap<>();
    }

    public void addDipendente(Dipendente d) {
        this.dipendenti.put(d.getId(), d);
    }

    public void addCliente(Cliente c) {
        this.clienti.put(c.getTelefono(), c);
    }

    public void addTelefonata(String telefono) {
        Date date = new Date();
        String data = date.toString();
        this.telefonate.put(telefono, data);
    }

    public void removeDipendente(String id) {
        this.dipendenti.remove(id);
    }

    public void removeCliente(String telefono) {
        this.clienti.remove(telefono);
    }

    public void removeTelefonata(String telefono) {
        this.telefonate.remove(telefono);
    }

    public String[] chiamata(Cliente cliente) {
        InfoClass info = new InfoClass();
        return info.chiamataInfo(cliente, dipendenti, clienti, telefonate);
    }
}