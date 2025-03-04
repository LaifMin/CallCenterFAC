import java.util.HashMap;
import java.util.Date;

public class Societa {
     private HashMap<String, Dipendente> dipendenti; // id come chiave e dipendente come valore
    private HashMap<String, Cliente> clienti; // numero di telefono come chiave e cliente come valore
    private HashMap<String, String> telefonate; // numero di telefono come chiave e data come valore
    



    public Societa() {
        this.dipendenti = new HashMap<String, Dipendente>();
        this.clienti = new HashMap<String, Cliente>();
        this.telefonate = new HashMap<String, String>();
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

    public String[] chiamata (Cliente cliente){
        String[] infos =  new String[10]; 
        String idDipendenteAttuale = Integer.toString((int) (Math.random() * 5)); //Simulo la scelta di un dipendente a caso
        if (idDipendenteAttuale.equals("0") || !dipendenti.containsKey(idDipendenteAttuale)){
            idDipendenteAttuale = "1";
        }

        String numeroDelClienteInteressato = cliente.getTelefono();
        if (this.clienti.containsKey(numeroDelClienteInteressato)){
            Cliente clienteTmp = this.clienti.get(numeroDelClienteInteressato);
            infos[0] = clienteTmp.getNome();
            infos[1] = clienteTmp.getCognome();
            infos[2] = clienteTmp.getCodice();
            infos[3] = clienteTmp.getIndirizzo();
            infos[4] = clienteTmp.getCitta();
            infos[5] = clienteTmp.getTelefono();
            if (this.telefonate.containsKey(numeroDelClienteInteressato)){
                infos[6] = this.telefonate.get(numeroDelClienteInteressato);
                for (String id: dipendenti.keySet()) {
                    if (dipendenti.get(id).getLastCallAnswered() == numeroDelClienteInteressato){
                        infos[7] = dipendenti.get(id).getNome();
                        infos[8] = dipendenti.get(id).getCognome();
                        infos[9] = dipendenti.get(id).getId();
                    }
                }
            }
            else{
                infos[6] = "Nessuna telefonata effettuata recentemente";
                addTelefonata(numeroDelClienteInteressato); //Aggiungo la telefonata mancante nel registro 
                infos[7] = "null";
                infos[8] = "null";
                infos[9] = "null";
                
            }
        }else{
            addCliente(cliente); //Aggiungo il cliente al registro
            Cliente clienteTmp = this.clienti.get(numeroDelClienteInteressato);
            infos[0] = clienteTmp.getNome();
            infos[1] = clienteTmp.getCognome();
            infos[2] = clienteTmp.getCodice();
            infos[3] = clienteTmp.getIndirizzo();
            infos[4] = clienteTmp.getCitta();
            infos[5] = clienteTmp.getTelefono();
            infos[6] = "Nessuna telefonata effetuata di recente in quanto appena aggiunto";
            addTelefonata(numeroDelClienteInteressato); //Aggiungo la telefonata mancante nel registro
            infos[7] = "null";
            infos[8] = "null";
            infos[9] = "null";
                

        }
        
        dipendenti.get(idDipendenteAttuale).setLastCallAnswered(numeroDelClienteInteressato);
        
        infos[0] = "nome: " + infos[0];
        infos[1] = "cognome: " + infos[1];
        infos[2] = "codice: " + infos[2];
        infos[3] = "indirizzo: " + infos[3];
        infos[4] = "citta: " + infos[4];
        infos[5] = "telefono: " + infos[5];
        infos[6] = "data ultima telefonata: " + infos[6];
        infos[7] = "nome dipendente: " + infos[7];
        infos[8] = "cognome dipendente: " + infos[8];
        infos[9] = "id dipendente: " + infos[9];

        return infos;
    }

}
