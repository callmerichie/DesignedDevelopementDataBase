import com.github.javafaker.Faker;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

//6 ripiani a scaffale
public class GeneraRipiano {
    public static void main(String[] args){
        GeneraRipiano ripiano = new GeneraRipiano();
        ripiano.generaDati();
    }
    public void generaDati() {

        //Impostare la localizzazione per generare dati appropriati per la nazionalita
        Faker faker = new Faker(new Locale("it-IT"));
        //Imposto formato del file e richiamo
        String csvFile = "datiRipiano.csv";
        try(FileWriter writer = new FileWriter(csvFile)){
            //genero dati
            for (int i = 0; i < 1200000; i++) {

                int codicesRipiano = i;

                //Costruzione riga per CSV
                StringBuilder row = new StringBuilder();
                row.append(codicesRipiano).append("\n");

                //Inserimento riga nel CSV
                writer.write(row.toString());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
