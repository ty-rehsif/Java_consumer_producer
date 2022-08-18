
import java.sql.*;
public class Consumer implements Runnable{
    //instance variables and Buffer object creation
    private final Buffer syncCObj;
    private int id;
   //initializing buffer and id to the passed values/address
    public Consumer(Buffer syncCObj, int id){
        this.syncCObj = syncCObj;
        this.id = id;
    }

    public void run() {
            try{
                //creating connection to the jdbc
                String conURL = "jdbc:mysql://localhost/java2";
                String user = "root";
                String pass = "1111";
                Connection connection = null;
                connection = DriverManager.getConnection(conURL,user,pass);
                //format to insert results into the database
                String query = "insert into A3Table(Object_id, Message_Sent)" + " values (?, ?)";
                //setting the query to the prepared statement object
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                //by two to not causing n^2 time operations
                for (int i=0; i<(Driver.size/2);i++) {
                    //for parameter one input the id
                    preparedStatement.setInt(1, id);
                    //get the string from method insert into variable and
                    //for parameter 2 insert the message into the table column
                    String message = syncCObj.take();
                    preparedStatement.setString(2, message);
                    //execute the prepared statement
                    preparedStatement.execute();
                }
                //closing connection for safety
                connection.close();
                //exception to catch if the thread gets interrupted while sleeping or waiting
                //or error in sql code

        } catch (InterruptedException | SQLException e) {
            System.out.println("Error occurred! Reason: " + e);
            Thread.currentThread().interrupt();
        }
    }
}
