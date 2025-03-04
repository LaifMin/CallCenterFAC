import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class InfoClass {

    private final String[] AGGIUNTE_INFOS = {
            "nome", "cognome", "codice", "indirizzo", "citta", "telefono",
            "data ultima telefonata", "nome dipendente", "cognome dipendente", "id dipendente"
    };

    private String[] infos = new String[AGGIUNTE_INFOS.length];

    public InfoClass() {

    }






    public String[] chiamataInfo(Cliente cliente, HashMap<String, Dipendente> dipendenti,
            HashMap<String, Cliente> clienti, HashMap<String, String> telefonate) {

        resetInfoArray(); //resetto l'array di info
        String assignedEmployeeId = assignEmployee(dipendenti); //assegno un dipendente
        analyzeCliente(cliente, clienti, telefonate, dipendenti);    //analizzo il cliente
        aggiornaUltimaChiamata(assignedEmployeeId, cliente.getTelefono(), dipendenti); //aggiorno l'ultima chiamata del dipendente
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

    private void analyzeCliente(Cliente cliente, HashMap<String, Cliente> clienti,
            HashMap<String, String> telefonate, HashMap<String, Dipendente> dipendenti) {
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
            HashMap<String, String> telefonate, HashMap<String, Dipendente> dipendenti) {
        Cliente c = clienti.get(telefono);
        caricamentoInfoCliente(c);
        storicoChiamate(telefono, telefonate);
        trovaUltimaChiamata(telefono, dipendenti);
    }

    private void nuovoCliente(Cliente cliente, String telefono,
            HashMap<String, Cliente> clienti,
            HashMap<String, String> telefonate) {
        clienti.put(telefono, cliente);
        telefonate.put(telefono, "Nuovo cliente - nessuna chiamata precedente");
        caricamentoInfoCliente(cliente);
        infos[6] = "Cliente appena registrato";
    }

    private void caricamentoInfoCliente(Cliente c) {
        infos[0] = c.getNome();
        infos[1] = c.getCognome();
        infos[2] = c.getCodice();
        infos[3] = c.getIndirizzo();
        infos[4] = c.getCitta();
        infos[5] = c.getTelefono();
    }

    private void storicoChiamate(String telefono, HashMap<String, String> telefonate) {
        if (telefonate.containsKey(telefono)) {
            infos[6] = telefonate.get(telefono);
        } else {
            infos[6] = "Nessuna telefonata registrata";
        }
    }

    private void trovaUltimaChiamata(String telefono, HashMap<String, Dipendente> dipendenti) {
        for (Dipendente d : dipendenti.values()) {
            if (telefono.equals(d.getLastCallAnswered())) {
                salvaInfoDipendente(d);
                break;
            }
        }
    }

    private void salvaInfoDipendente(Dipendente d) {
        infos[7] = d.getNome();
        infos[8] = d.getCognome();
        infos[9] = d.getId();
    }

    private void aggiornaUltimaChiamata(String employeeId, String telefono,
            HashMap<String, Dipendente> dipendenti) {
        Dipendente d = dipendenti.get(employeeId);
        if (d != null) {
            d.setLastCallAnswered(telefono);
        }
    }

}