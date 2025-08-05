import com.github.javafaker.Faker;
import java.util.Locale;
import java.io.FileWriter;
import java.io.IOException;

public class GeneraCasaEditrice {

  public static void main(String[] args){
    GeneraCasaEditrice casaEditrice = new GeneraCasaEditrice();
    casaEditrice.generaDati();
  }

  public void generaDati(){
    //Impostare la localizzazione per generare dati appropriati per la nazionalita
    Faker faker = new Faker(new Locale("it-IT"));
    //Imposto formato del file e richiamo
    String csvFile = "datiCasaEditrice.csv";

    try(FileWriter writer = new FileWriter(csvFile)){
      //genero dati
      for(int i = 0; i < 5000; i++){
        //String nome = faker.name().lastName();
        String nazione = faker.country().name();
        String direttore = faker.name().fullName();
        String annoFondazione = faker.date().birthday().toString();
        int cap = faker.number().numberBetween(10000,99999);
        String capString = "" + cap;
        String paese = faker.address().cityName();
        String via = faker.address().streetName();
        String provincia = faker.address().cityPrefix();
        String numeroCivico = faker.address().streetAddressNumber();
        String telefono = faker.phoneNumber().cellPhone();

        //correzione nazione (Costa d'avorio)
        nazione = correggiNazione(nazione);
        //correzione annofondazione (prelevo anno)
        annoFondazione = prelevoAnno(annoFondazione);

        //System.out.println(nome+";"+nazione+";"+direttore);
        //System.out.println("Dati Creati:"+nome+";"+nazione+";"+direttore+";"+telefono+";"+annofondazione+";"+capString);
        //System.out.println("Dati Creati:"+capString+";"+paese+";"+via+";"+provincia+";"+numerocivico);

        //Costruzione riga per CSV
        StringBuilder row = new StringBuilder();
        //row.append(nome).append(";");
        row.append(nazione).append(";");
        row.append(direttore).append(";");
        row.append(annoFondazione).append(";");
        row.append(capString).append(";");
        row.append(paese).append(";");
        row.append(via).append(";");
        row.append(provincia).append(";");
        row.append(numeroCivico).append(";");
        row.append(telefono).append("\n");

        //Inserimento riga nel CSV
        writer.write(row.toString());
      }
    }catch (IOException e){
      e.printStackTrace();
    }
  }

  public static String prelevoAnno(String data){
    int lunghezzaStringa = data.length();
    if(lunghezzaStringa==28){
      data = data.substring(24,lunghezzaStringa);
      return data;
    } else{
      data = data.substring(25,lunghezzaStringa);
      return data;
    }
  }

  public static String correggiNazione(String nazionalita) {
    if(nazionalita.length()==14){
      if(nazionalita.substring(6,nazionalita.length()).equalsIgnoreCase("d'Ivoire")){
        //System.out.println("Coste d'Ivoire");
        return "Coste D'Ivoire";
      }
    }
    return nazionalita;
  }
}
