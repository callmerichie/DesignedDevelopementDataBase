import com.github.javafaker.Faker;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

public class GeneraMagazzino {
    public static void main(String[] args){
        GeneraMagazzino magazzino = new GeneraMagazzino();
        magazzino.generaDati();
    }
    public void generaDati() {

        //Impostare la localizzazione per generare dati appropriati per la nazionalita
        Faker faker = new Faker(new Locale("it-IT"));
        //Imposto formato del file e richiamo
        String csvFile = "datiMagazzino.csv";
        try(FileWriter writer = new FileWriter(csvFile)){
            //genero dati
            for (int i = 0; i < 6000; i++) {

                int codiceMagazzino = i;
                int cap = faker.number().numberBetween(10000,99999);
                String capString = "" + cap;
                String paese = faker.address().cityName();
                String via = faker.address().streetName();
                String provincia = faker.address().cityPrefix();
                String numeroCivico = faker.address().streetAddressNumber();

                //System.out.println("Dati Creati:"+codiceMagazzino+";"+capString+";"+paese+";"+via+";"+provincia+";"+numeroCivico);

                //Costruzione riga per CSV
                StringBuilder row = new StringBuilder();
                row.append(codiceMagazzino).append(";");
                row.append(capString).append(";");
                row.append(paese).append(";");
                row.append(numeroCivico).append(";");
                row.append(via).append(";");
                row.append(provincia).append("\n");

                //Inserimento riga nel CSV
                writer.write(row.toString());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
