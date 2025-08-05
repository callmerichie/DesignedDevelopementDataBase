import com.github.javafaker.Faker;
import java.util.Locale;
import java.io.FileWriter;
import java.io.IOException;
public class GeneraAutore {
  public static void main(String[] args) {
    GeneraAutore autore = new GeneraAutore();
    autore.generaDati();
  }
  public void generaDati() {

      //Impostare la localizzazione per generare dati appropriati per la nazionalita
      Faker faker = new Faker(new Locale("it-IT"));
      //Imposto formato del file e richiamo
      String csvFile = "datiAutore.csv";
      try(FileWriter writer = new FileWriter(csvFile)){
        //genero dati
        for (int i = 0; i < 10000; i++) {

          int codiceAutore = i;
          String dataDiNascita = faker.date().birthday().toString();
          int numeroLibriScritti = faker.number().numberBetween(1, 30);
          String nazionalita = faker.country().name();
          String nome = faker.name().firstName().toString();
          String cognome = faker.name().lastName().toString();

          //correzione data
          //System.out.println("Dati inseriti Prima:" + dataDiNascita + "-Lunghezza stringa"+ dataDiNascita.length());
          int lunghezzaStringa = dataDiNascita.length();
          dataDiNascita = dataCorretta(dataDiNascita, lunghezzaStringa);

          //correzione nazionalita
          nazionalita = correggiNazionalita(nazionalita);

          //System.out.println("Dati inseriti:" + dataDiNascita);
          //System.out.println("Dati inseriti:"+nazionalita);
          //System.out.println("Dati inseriti:"+codiceAutore+" "+dataDiNascita+" "+numeroLibriScritti+" "+nazionalita+" "+nome+" "+cognome);

          //Costruzione riga per CSV
          StringBuilder row = new StringBuilder();
          row.append(codiceAutore).append(";");
          row.append(dataDiNascita).append(";");
          row.append(numeroLibriScritti).append(";");
          row.append(nazionalita).append(";");
          row.append(nome).append(";");
          row.append(cognome).append("\n");

          //Inserimento riga nel CSV
          writer.write(row.toString());
        }
      }catch (IOException e){
        e.printStackTrace();
      }
  }
  //formato data che ricevo: "Tue Feb 21 21:25:03 CET 2806" o "Tue Apr 26 05:14:15 CEST 1994"
  public static String dataCorretta(String dataInserita, int lunghezzaStringa){
    String giorno = dataInserita.substring(8, 10);
    String mese = dataInserita.substring(4, 7);
    mese = daLettereNumero(mese);
    if(lunghezzaStringa==28){
      String anno = dataInserita.substring(24, dataInserita.length());
      String dataCorretta;
      return dataCorretta = anno + "-" + mese + "-" + giorno;
    }else{
      String anno = dataInserita.substring(25, dataInserita.length());
      String dataCorretta;
      return dataCorretta = anno + "-" + mese + "-" + giorno;
    }
  }
  //cambio di mese in stringa
  public static String daLettereNumero(String mese){
    switch (mese){
      case "Jan":
        return "01";
      case "Feb":
        return "02";
      case "Mar":
        return "03";
      case "Apr":
        return "04";
      case "May":
        return "05";
      case "Jun":
        return "06";
      case "Jul":
        return "07";
      case "Aug":
        return "08";
      case "Sep":
        return "09";
      case "Oct":
        return "10";
      case "Nov":
        return "11";
      case "Dec":
        return "12";
    }
    return "ERRORE MESE - VEDERE STRINGA";
  }

  public static String correggiNazionalita(String nazionalita) {
    if(nazionalita.length()==14){
      if(nazionalita.substring(6,nazionalita.length()).equalsIgnoreCase("d'Ivoire")){
        //System.out.println("Coste d'Ivoire");
        return "Coste D'Ivoire";
      }
    }
    return nazionalita;
  }
}
