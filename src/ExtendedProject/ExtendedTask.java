package ExtendedProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class ExtendedTask {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter Database Username: ");
    String user = sc.nextLine();
    System.out.print("Enter Database Password: ");
    String password = sc.nextLine();
    System.out.print("Enter Database Name: ");
    String db = sc.nextLine();
    String url = "jdbc:mysql://localhost:3306/" + db;
    try {
      // 1. LOAD DRIVER
      Class.forName("com.mysql.cj.jdbc.Driver");
      // 2. ESTABLISH CONNECTION
      Connection conn = DriverManager.getConnection(url, user, password);
      System.out.println("Connection Established");
      System.out.println("\n===== Choose Operation =====");
      System.out.println("1. Add User");
      System.out.println("2. Remove User By ID");
      System.out.println("3. Update User");
      System.out.println("4. Find User By ID");
      System.out.print("Enter Choice: ");
      int choice = sc.nextInt();
      switch (choice) {
        // ADD USER
        case 1: {
          addUser(conn, sc);
        }
          break;
        // DELETE USER
        case 2: {
          deleteUser(conn, sc);
        }
          break;
        // UPDATE USER
        case 3: {
          updateUser(conn, sc);
        }
          break;
        // FIND USER
        case 4: {
          findUser(conn, sc);
        }
          break;
        default: {
          System.out.println("Invalid Choice");
        }
      }
      // CLOSE CONNECTION
      conn.close();
      sc.close();
      System.out.println("Connection Closed");
    }
    // CUSTOM EXCEPTION
    catch (UserAlreadyExistsException e) {
      System.out.println(e.getMessage());
    }
    // CUSTOM EXCEPTION
    catch (UserNotFoundException e) {
      System.out.println(e.getMessage());
    }
    // DRIVER EXCEPTION
    catch (ClassNotFoundException e) {
      System.out.println("Driver Not Found");
      e.printStackTrace();
    }
    // SQL EXCEPTION
    catch (SQLException e) {
      System.out.println("Database Error");
      e.printStackTrace();
    }
  }
  // ADD USER METHOD
  public static void addUser(Connection conn, Scanner sc) throws SQLException, UserAlreadyExistsException {
    System.out.print("Enter ID: ");
    int id = sc.nextInt();
    System.out.print("Enter Name: ");
    String name = sc.next();
    System.out.print("Enter Email: ");
    String email = sc.next();
    System.out.print("Enter Password: ");
    String pass = sc.next();
    // CHECK ID OR EMAIL EXISTS
    String checkQuery = "SELECT * FROM users WHERE id = ? OR email = ?";
    PreparedStatement checkPs = conn.prepareStatement(checkQuery);
    checkPs.setInt(1, id);
    checkPs.setString(2, email);
    ResultSet rs = checkPs.executeQuery();
    if (rs.next()) {
      // THROW EXCEPTION
      throw new UserAlreadyExistsException("ID or Email Already Exists");
    }
    // INSERT QUERY
    String query = "INSERT INTO users(id,name,email,password) VALUES(?,?,?,?)";
    PreparedStatement ps = conn.prepareStatement(query);
    ps.setInt(1, id);
    ps.setString(2, name);
    ps.setString(3, email);
    ps.setString(4, pass);
    int result = ps.executeUpdate();
    if (result > 0) {
      System.out.println("User Added Successfully");
    }
    rs.close();
    checkPs.close();
    ps.close();
  }
  // DELETE USER METHOD
  public static void deleteUser(Connection conn, Scanner sc) throws SQLException, UserNotFoundException {
    System.out.print("Enter ID: ");
    int id = sc.nextInt();
    // CHECK USER EXISTS
    String checkQuery = "SELECT * FROM users WHERE id = ?";
    PreparedStatement checkPs = conn.prepareStatement(checkQuery);
    checkPs.setInt(1, id);
    ResultSet rs = checkPs.executeQuery();
    if (!rs.next()) {
      // THROW EXCEPTION
      throw new UserNotFoundException("User ID Not Found");
    }
    // DELETE QUERY
    String query = "DELETE FROM users WHERE id = ?";
    PreparedStatement ps = conn.prepareStatement(query);
    ps.setInt(1, id);
    int result = ps.executeUpdate();
    if (result > 0) {
      System.out.println("User Deleted Successfully");
    }
    rs.close();
    checkPs.close();
    ps.close();
  }

  // UPDATE USER METHOD

  public static void updateUser(Connection conn, Scanner sc) throws SQLException, UserAlreadyExistsException, UserNotFoundException {
    System.out.print("Enter ID: ");
    int id = sc.nextInt();
    // CHECK USER EXISTS
    String userCheck = "SELECT * FROM users WHERE id = ?";
    PreparedStatement userPs = conn.prepareStatement(userCheck);
    userPs.setInt(1, id);
    ResultSet userRs = userPs.executeQuery();
    if (!userRs.next()) {
      // THROW EXCEPTION
      throw new UserNotFoundException("User ID Not Found");
    }
    System.out.println("1. Update Name");
    System.out.println("2. Update Email");
    System.out.println("3. Update Password");
    System.out.print("Enter Choice: ");
    int opt = sc.nextInt();

    // UPDATE NAME
    if (opt == 1) {
      System.out.print("Enter New Name: ");
      String name = sc.next();
      String query = "UPDATE users SET name = ? WHERE id = ?";
      PreparedStatement ps = conn.prepareStatement(query);
      ps.setString(1, name);
      ps.setInt(2, id);
      int result = ps.executeUpdate();
      if (result > 0) {
        System.out.println("Name Updated Successfully");
      }
      ps.close();
    }
    // ================= UPDATE EMAIL =================
    else if (opt == 2) {
      System.out.print("Enter New Email: ");
      String email = sc.next();
      // CHECK EMAIL EXISTS
      String checkEmail = "SELECT * FROM users WHERE email = ?";
      PreparedStatement emailPs = conn.prepareStatement(checkEmail);
      emailPs.setString(1, email);
      ResultSet emailRs = emailPs.executeQuery();
      if (emailRs.next()) {
        // THROW EXCEPTION
        throw new UserAlreadyExistsException("Email Already Exists");
      }
      // UPDATE EMAIL
      String query = "UPDATE users SET email = ? WHERE id = ?";
      PreparedStatement ps = conn.prepareStatement(query);
      ps.setString(1, email);
      ps.setInt(2, id);
      int result = ps.executeUpdate();
      if (result > 0) {
        System.out.println("Email Updated Successfully");
      }
      emailRs.close();
      emailPs.close();
      ps.close();
    }
    // ================= UPDATE PASSWORD =================
    else if (opt == 3) {
      System.out.print("Enter New Password: ");
      String pass = sc.next();
      String query = "UPDATE users SET password = ? WHERE id = ?";
      PreparedStatement ps = conn.prepareStatement(query);
      ps.setString(1, pass);
      ps.setInt(2, id);
      int result = ps.executeUpdate();
      if (result > 0) {
        System.out.println("Password Updated Successfully");
      }
      ps.close();
    }
    else {
      System.out.println("Invalid Choice");
    }
    userRs.close();
    userPs.close();
  }
  // ================= FIND USER METHOD =================
  public static void findUser(Connection conn, Scanner sc) throws SQLException, UserNotFoundException {
    System.out.print("Enter ID: ");
    int id = sc.nextInt();
    String query = "SELECT * FROM users WHERE id = ?";
    PreparedStatement ps = conn.prepareStatement(query);
    ps.setInt(1, id);
    ResultSet rs = ps.executeQuery();
    if (rs.next()) {
      int id2 = rs.getInt("id");
      String name = rs.getString("name");
      String email = rs.getString("email");
      String pass = rs.getString("password");
      System.out.println("User [ ID : " + id2 + ", Name : " + name + ", Email : " + email + ", Password : " + pass + " ]");

    } else {
      // THROW EXCEPTION
      throw new UserNotFoundException("User Not Found");
    }
    rs.close();
    ps.close();
  }
}