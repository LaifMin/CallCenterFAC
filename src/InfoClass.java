import java.util.*;

public class InfoClass {

    private final String[] AGGIUNTE_INFOS = {
            "nome", "cognome", "codice", "indirizzo", "citta", "telefono",
            "data ultima telefonata", "nome dipendente", "cognome dipendente", "id dipendente"
    };

   //--------------------------
    private final int NOME = 0;
    private final int COGNOME = 1;
    private final int CODICE = 2;
    private final int INDIRIZZO = 3;
    private final int CITTA = 4;
    private final int TELEFONO = 5;
    private final int DATA_ULTIMA_TELEFONATA = 6;
    private final int NOME_DIPENDENTE = 7;
    private final int COGNOME_DIPENDENTE = 8;
    private final int ID_DIPENDENTE = 9;

    //---------------------------
    private String[] infos = new String[AGGIUNTE_INFOS.length];
    

    public InfoClass() {

    }






    public String[] chiamataInfo(Cliente cliente, HashMap<String, Dipendente> dipendenti,
                                 HashMap<String, Cliente> clienti, List<Telefonata> telefonate) {

        resetInfoArray(); //resetto l'array di info
        assignEmployee(dipendenti); //assegno un dipendente
        analyzeCliente(cliente, clienti, telefonate, dipendenti);    //analizzo il cliente
      
        return assignEtichette(); // output delle info con le etichette accanto
    }





    private void resetInfoArray() {
        Arrays.fill(infos, null);
    }

    private String assignEmployee(HashMap<String, Dipendente> dipendenti) {
        List<String> employeeIds = new ArrayList<>(dipendenti.keySet());

        if (employeeIds.isEmpty()) {
            throw new IllegalStateException("Nessun dipendente disponibile");
        }

        return employeeIds.get((int) (Math.random() * employeeIds.size())); //scelgo a random un dipendente

    }

    public Dipendente assignEmployeeC(HashMap<String, Dipendente> dipendenti) {
        ArrayList<Dipendente> employeeIds = new ArrayList<>(dipendenti.values());

        if (employeeIds.isEmpty()) {
            throw new IllegalStateException("Nessun dipendente disponibile");
        }

        return employeeIds.get((int) (Math.random() * employeeIds.size())); //scelgo a random un dipendente

    }

    private void analyzeCliente(Cliente cliente, HashMap<String, Cliente> clienti,
                                List <Telefonata> telefonate, HashMap<String, Dipendente> dipendenti) {
        String telefono = cliente.getTelefono();

        if (clientExists(telefono, clienti)) {
            clienteEsistente(telefono, clienti, telefonate, dipendenti);
        } else {
            nuovoCliente(cliente, telefono, clienti, telefonate);
        }
    }

    private boolean clientExists(String telefono, HashMap<String, Cliente> clienti) {
        return clienti.containsKey(telefono);
    }

    private String[] assignEtichette() {
        String[] formatted = new String[AGGIUNTE_INFOS.length];
        for (int i = 0; i < AGGIUNTE_INFOS.length; i++) {
            String value;
            if (infos[i] != null) {
                value = infos[i];
            } else {
                value = "N/D";
            }
            formatted[i] = AGGIUNTE_INFOS[i] + ": " + value;
        }
        return formatted;
    }

    private void clienteEsistente(String telefono, HashMap<String, Cliente> clienti,
                                  List<Telefonata>telefonate, HashMap<String, Dipendente> dipendenti) {
        Cliente c = clienti.get(telefono);
        trovaUltimaChiamata(telefono, dipendenti, telefonate);
        caricamentoInfoCliente(c);
        storicoChiamate(telefono, telefonate);

    }

    private void nuovoCliente(Cliente cliente, String telefono,
                              HashMap<String, Cliente> clienti,
                              List <Telefonata> telefonate) {
        clienti.put(telefono, cliente);
        telefonate.add(new Telefonata(telefono, infos[9]));
        caricamentoInfoCliente(cliente);
        infos[DATA_ULTIMA_TELEFONATA] = "Cliente appena registrato";
    }

    private void caricamentoInfoCliente(Cliente c) {
        infos[NOME] = c.getNome();
        infos[COGNOME] = c.getCognome();
        infos[CODICE] = c.getCodice();
        infos[INDIRIZZO] = c.getIndirizzo();
        infos[CITTA] = c.getCitta();
        infos[TELEFONO] = c.getTelefono();
    }

    private void storicoChiamate(String telefono, List<Telefonata> telefonate) {
        boolean trovata = false;
        Telefonata te = null;
        for (Telefonata t: telefonate){
            if(t.getNumTelefono().equals(telefono)){
                trovata = true;
                te = t;
            }
        }

        if (trovata) {
            infos[DATA_ULTIMA_TELEFONATA] = te.getData();
        } else {
            infos[DATA_ULTIMA_TELEFONATA] = "Nessuna telefonata registrata";
        }
    }

    private void trovaUltimaChiamata(String telefono, HashMap<String, Dipendente> dipendenti, List<Telefonata> telefonate) {
        for (Telefonata t: telefonate) {
            if (t.getNumTelefono().equals(telefono)) {
                salvaInfoDipendente(dipendenti.get(t.getIdDipendente()));
                break;
            }
        }
    }

    private void salvaInfoDipendente(Dipendente d) {
        infos[NOME_DIPENDENTE] = d.getNome();
        infos[COGNOME_DIPENDENTE] = d.getCognome();
        infos[ID_DIPENDENTE] = d.getId();
    }

    
} 
