import com.github.javafaker.Faker;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

public class GeneraScaffale {
    public static void main(String[] args){
        GeneraScaffale scaffale = new GeneraScaffale();
        scaffale.generaDati();
    }
    public void generaDati() {

        //Impostare la localizzazione per generare dati appropriati per la nazionalita
        Faker faker = new Faker(new Locale("it-IT"));
        //Imposto formato del file e richiamo
        String csvFile = "datiScaffale.csv";
        try(FileWriter writer = new FileWriter(csvFile)){
            //genero dati
            for (int i = 0; i < 200000; i++) {

                int codicesScaffale = i;

                //Costruzione riga per CSV
                StringBuilder row = new StringBuilder();
                row.append(codicesScaffale).append("\n");

                //Inserimento riga nel CSV
                writer.write(row.toString());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
