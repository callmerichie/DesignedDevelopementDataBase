import java.sql.*;

public class MainFunzionante{
  public static void main(String[] args){
    int codiceautore= 2;
    Date datadinascita = new Date(1444,11,11);
    int numerolibriscritti = 10;
    String nazionalita = "italiano";
    insertRow(codiceautore, datadinascita, numerolibriscritti, nazionalita);
  }

  public static Connection getConnection() {
    try {
      String url       = "jdbc:postgresql://localhost:5432/biblioteca";
      String user      = "guest1";
      String password  = "Basi2024";

      Class.forName("org.postgresql.Driver");

      Connection connection = DriverManager.getConnection(url, user, password);

      return connection;
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static void insertRow(int codiceautore, Date datadinascita, int numerolibriscritti, String nazionalita){
    System.out.println("asd");
    try {
      String sql = "INSERT INTO autore(codiceautore, datadinascita, numerolibriscritti, nazionalita) " + "VALUES(?, ?, ?, ?)";
      PreparedStatement preparedStatement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      preparedStatement.setInt(1, codiceautore);
      preparedStatement.setDate(2, datadinascita);
      preparedStatement.setInt(3, numerolibriscritti);
      preparedStatement.setString(4, nazionalita);
      int rowAffected = preparedStatement.executeUpdate();
      if(rowAffected == 1) {
        System.out.println("1 row affected!");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
