import com.github.javafaker.Faker;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class GeneraLibro {

  public static void main(String[] args) {
    Faker faker = new Faker();
    Set<String> isbnSet = new HashSet<>();
    int count = 0;

    try (FileWriter writer = new FileWriter("isbn_list.csv")) {
      while (count < 102426) {
        String isbn = generateISBN(faker);
        if (isbnSet.add(isbn)) {
          writer.write(isbn + "\n");
          count++;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static String generateISBN(Faker faker) {
    StringBuilder isbnBuilder = new StringBuilder("978");
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
}
