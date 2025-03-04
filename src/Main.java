


public class Main {


    public static void main(String[] args) throws Exception {

        Societa societa = new Societa();
        Dipendente d1 = new Dipendente("1", "Mario", "Rossi");
        Dipendente d2 = new Dipendente("2", "Luca", "Bianchi");
        Cliente c1 = new Cliente("mearco", "essel", "3", "Via Roma 1", "Roma", "1234567890");
        Cliente c2 = new Cliente("pastiglia", "Neri", "4", "Via Milano 2", "Milano", "0987654321");
        societa.addDipendente(d1);
        societa.addDipendente(d2);
        societa.addCliente(c1);
        societa.addTelefonata("1234567890");
        d1.setLastCallAnswered("1234567890");
    
        String[] infos = societa.chiamata(c1);
        for (String info: infos){
            System.out.println(info);
        }
        /*societa.removeDipendente("1");
        societa.removeCliente("1");
        societa.removeTelefonata("1234567890"); */
        
        
    }



}
