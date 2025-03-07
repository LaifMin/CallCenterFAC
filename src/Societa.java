import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Società {
    private HashMap<String, Dipendente> dipendenti;
    private HashMap<String, Cliente> clienti;
    private List<Telefonata> telefonate;
    public InfoClass info = new InfoClass();

    public Società() {
        this.dipendenti = new HashMap<>();
        this.clienti = new HashMap<>();
        this.telefonate = new ArrayList<>();
    }

    public void addDipendente(Dipendente d) {
        this.dipendenti.put(d.getId(), d);
    }

    public void addCliente(Cliente c) {
        this.clienti.put(c.getTelefono(), c);
    }

    public void addTelefonata(Cliente c) {
        if(!clienti.containsKey(c.getTelefono())){
            clienti.put(c.getTelefono(),c);
        }
        Dipendente d = null;
        if(dipendenti != null){
            d = info.assignEmployeeC(dipendenti);
        }
        this.telefonate.add(new Telefonata(c.getTelefono(),d.getId()));
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
        return info.chiamataInfo(cliente, dipendenti, clienti, telefonate);
    }
}
