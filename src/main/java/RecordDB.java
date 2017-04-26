import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Created by dilli on 4/25/2017.
 */
public class RecordDB {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";        //Configure the driver needed
    private static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/RecordStore";     //Connection to the database
    private static final String USER = "khatiwoda";   //MYSQL username
    private static final String PASSWORD = "Saanvi";   //MYSQL password
    private static final String TABLE_NAME = "Inventory"; //table name
    private static final String PK_COLUMN = "ID";
    private static final String CONSIGNOR_NAME_COLUMN = "ConsignorName";//column name
    private static final String PHONE_NO_COLUMN = "PhoneNo";//column name
    private static final String ARTIST_NAME_COLUMN = "ArtistName";
    private static final String TITLE_COLUMN = "Title";
    private static final String PRICE_COLUMN = "Price";
    private static final String DATE_COLUMN = "RecordDate";
    //constructor
    RecordDB() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Can't instantiate driver class; check you have drives and classpath configured correctly?");
            cnfe.printStackTrace();
            System.exit(-1);  //quit the program
        }
    }
    //defines method
    void createTable() {

        try (Connection connection = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            String createSQLTableTemplate = "CREATE TABLE IF NOT EXISTS %s (%s int NOT NULL AUTO_INCREMENT, %s VARCHAR (50), %s VARCHAR(20), %s VARCHAR(50)," +
                    "%s VARCHAR(20), %s DOUBLE, %s TIMESTAMP, PRIMARY KEY(%s))";
            String createSQLTable = String.format(createSQLTableTemplate, TABLE_NAME, PK_COLUMN, CONSIGNOR_NAME_COLUMN,PHONE_NO_COLUMN,ARTIST_NAME_COLUMN,TITLE_COLUMN,PRICE_COLUMN,DATE_COLUMN,PK_COLUMN);

            statement.executeUpdate(createSQLTable);

            statement.close();
            connection.close();

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    //defines method that add records to the database
    void addRecord(RecordObject recordObject)  {

        try (Connection connection = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD)) {

            String addSQLrecordObject = "INSERT INTO " + TABLE_NAME + " VALUES ( ? , ? , ? , ? , ? , ? , ?) " ;
            PreparedStatement addSQLrecordObjectRecord = connection.prepareStatement(addSQLrecordObject);
            addSQLrecordObjectRecord.setInt(1,2);
            addSQLrecordObjectRecord.setString(2, recordObject.consignorName);
            addSQLrecordObjectRecord.setString(3, recordObject.phone);
            addSQLrecordObjectRecord.setString(4,recordObject.artistName);
            addSQLrecordObjectRecord.setString(5,recordObject.title);
            addSQLrecordObjectRecord.setDouble(6,recordObject.price);
            addSQLrecordObjectRecord.setDate(7,recordObject.date);

            addSQLrecordObjectRecord.execute();

            //TO DO add a message dialog box with "Added cube solver record for 'cubesolver name'" message.

            addSQLrecordObjectRecord.close();
            connection.close();

        } catch (SQLException se) {
            se.printStackTrace();
        }

    }
   // defines method that deletes data from the database
    void delete(RecordObject recordObject){
        try(Connection connection = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD)){
            String deleteSQLrecordObject = "DELETE FROM %s WHERE %s = ? AND %s = ? AND %s = ? AND %s = ? AND %s = ? AND %s = ?";
            String deleteSQLrecordObjectRecord = String.format(deleteSQLrecordObject,TABLE_NAME,CONSIGNOR_NAME_COLUMN,PHONE_NO_COLUMN,ARTIST_NAME_COLUMN,TITLE_COLUMN,PRICE_COLUMN,DATE_COLUMN);
            PreparedStatement deletePreparedStatement = connection.prepareStatement(deleteSQLrecordObjectRecord);
            deletePreparedStatement.setString(1,recordObject.consignorName);
            deletePreparedStatement.setString(2,recordObject.phone);
            deletePreparedStatement.setString(3,recordObject.artistName);
            deletePreparedStatement.setString(4,recordObject.title);
            deletePreparedStatement.setDouble(5,recordObject.price);
            deletePreparedStatement.setDate(6,recordObject.date);
            deletePreparedStatement.execute();
            deletePreparedStatement.close();
            connection.close();
        }catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }
    //defines method that updates record in the database.
//    void updateRecord(RecordObject recordObject){
//        try(Connection connection = DriverManager.getConnection(DB_CONNECTION_URL,USER,PASSWORD)){
//            String updateSQLcubes = "UPDATE %s SET %s = ? WHERE %s = ?";
//            String updateSQLcubesRecord = String.format(updateSQLcubes,TABLE_NAME,TIME_TAKEN_COLUMN,CUBE_SOLVER_COLUMN);
//            PreparedStatement updatePreparedStatement = connection.prepareStatement(updateSQLcubesRecord);
//            updatePreparedStatement.setDouble(1,cubes.time);
//            updatePreparedStatement.setString(2,cubes.name);
//            updatePreparedStatement.execute();
//            updatePreparedStatement.close();
//            connection.close();
//        }catch (SQLException se){
//            se.printStackTrace();
//        }
    //}
    //defines method that displays record from the database
    ArrayList<RecordObject> fetchAllRecords() {
        ArrayList<RecordObject> allRecords = new ArrayList();
        try (Connection connection = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            String selectSQLtable = "SELECT * FROM " + TABLE_NAME;
            ResultSet rs = statement.executeQuery(selectSQLtable);
            while (rs.next()) {
                String cName = rs.getString(CONSIGNOR_NAME_COLUMN);
                String phone = rs.getString(PHONE_NO_COLUMN);
                String aName = rs.getString(ARTIST_NAME_COLUMN);
                String title = rs.getString(TITLE_COLUMN);
                double price = rs.getDouble(PRICE_COLUMN);
                Date date = rs.getDate(DATE_COLUMN);
                RecordObject recordObject = new RecordObject(cName,phone,aName,title,price, (java.sql.Date) date);
                allRecords.add(recordObject);}
            rs.close();
            statement.close();
            connection.close();
            return allRecords;
        } catch (SQLException se) {
            se.printStackTrace();
            return null;
        }
    }
}
