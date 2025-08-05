import com.github.javafaker.Faker;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class GeneraCopia {

    public static void main(String[] args) {
        Faker faker = new Faker();
        Set<String> isbnSet = new HashSet<>();
        int count = 0;

        try (FileWriter writer = new FileWriter("datiCopia.csv")) {
            while (count < 30000000 ) {
                String annoFondazione = faker.date().birthday().toString();
                String isbn = generateISBN(faker);


                StringBuilder row = new StringBuilder();

                //correzione annofondazione (prelevo anno)
                annoFondazione = prelevoAnno(annoFondazione);
                if (isbnSet.add(isbn)) {
                    row.append(annoFondazione).append(";");
                    row.append(isbn).append("\n");

                    writer.write(row.toString());

                    count++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String generateISBN(Faker faker) {
        StringBuilder isbnBuilder = new StringBuilder("123");
        for (int i = 0; i < 9; i++) {
            isbnBuilder.append(faker.random().nextInt(10));
        }

        String isbn = isbnBuilder.toString();
        int sum = 0;
        for (int i = 0; i < 12; i++) {
            sum += Character.getNumericValue(isbn.charAt(i)) * (i % 2 == 0 ? 1 : 3);
        }
        int checkDigit = (10 - (sum % 10)) % 10;

        return isbn + checkDigit;
    }

    public static String prelevoAnno(String data) {
        int lunghezzaStringa = data.length();
        if (lunghezzaStringa == 28) {
            data = data.substring(24, lunghezzaStringa);
            return data;
        } else {
            data = data.substring(25, lunghezzaStringa);
            return data;
        }
    }
}
