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
    private static final String TABLE_NAME = "Inventory",PK_COLUMN = "ID",CONSIGNOR_NAME_COLUMN = "ConsignorName",PHONE_NO_COLUMN = "PhoneNo",
            ARTIST_NAME_COLUMN = "ArtistName",TITLE_COLUMN = "Title",PRICE_COLUMN = "Price",DATE_COLUMN = "RecordDate";

    private static final String SOLD_TABLE_NAME = "SoldItem",SOLD_PK_COL = "SoldID",SOLD_CON_COL = "ConsignorName",
            SOLD_TITLE_COL = "Title",SOLD_SOLD_PRICE_COL = "SoldPrice", SOLD_DATE_COL = "DateSold";

    private static final String PAYEE_TABLE_NAME = "PayInfo",PAYEE_CON_NAME_COL = "Consignor",PAYEE_SHARE_COL = "ShareAmount",
            PAYEE_AMOUNT_PAID_COL = "AmountPaid",PAYEE_BALANCE_DUE_COL = "BalanceDue";

    private static final String BARGAIN_TABLE_NAME = "BargainList",BARGAIN_CON_COL = "ConsignorName",BARGAIN_TITLE_COL = "Title",
            BARGAIN_ARTIST_NAME_COL = "ArtistName", BARGAIN_BASE_PRICE_COL = "BasePrice";
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
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE)) {

            String createInventoryTableSQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + PK_COLUMN + " int NOT NULL AUTO_INCREMENT,"
                    + CONSIGNOR_NAME_COLUMN + " VARCHAR(50)," + PHONE_NO_COLUMN + " VARCHAR(12)," + ARTIST_NAME_COLUMN + " VARCHAR(50),"
                    + TITLE_COLUMN + " VARCHAR(50)," + PRICE_COLUMN + " DOUBLE,"+ DATE_COLUMN +" TIMESTAMP, PRIMARY KEY(" + PK_COLUMN + "))";
            statement.executeUpdate(createInventoryTableSQL);

            String createSoldItemTableSQL = "CREATE TABLE IF NOT EXISTS " + SOLD_TABLE_NAME + "(" + SOLD_PK_COL + " INT NOT NULL AUTO_INCREMENT,"
                    + SOLD_CON_COL + " VARCHAR(20)," + SOLD_TITLE_COL +" VARCHAR(50)," + SOLD_SOLD_PRICE_COL + " DOUBLE," + SOLD_DATE_COL
                    + " TIMESTAMP, PRIMARY KEY (" + SOLD_PK_COL + "))";
            statement.executeUpdate(createSoldItemTableSQL);

            String createPayInfoSQLTable = "CREATE TABLE IF NOT EXISTS " + PAYEE_TABLE_NAME + "(" + PAYEE_CON_NAME_COL + " VARCHAR(50) NOT NULL," +
                    PAYEE_SHARE_COL + " DOUBLE NOT NULL," + PAYEE_AMOUNT_PAID_COL + " DOUBLE NOT NULL," + PAYEE_BALANCE_DUE_COL + " DOUBLE NOT NULL)";
            statement.executeUpdate(createPayInfoSQLTable);

            String createBargainListSQLTable = "CREATE TABLE IF NOT EXISTS " + BARGAIN_TABLE_NAME + "(" + BARGAIN_CON_COL + " VARCHAR(50) NOT NULL," +
                     BARGAIN_TITLE_COL + " VARCHAR(50) NOT NULL," + BARGAIN_ARTIST_NAME_COL + " VARCHAR(20) NOT NULL," + BARGAIN_BASE_PRICE_COL + " DOUBLE NOT NULL)";
            statement.executeUpdate(createBargainListSQLTable);

            statement.close();
            connection.close();

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    //defines method that add records to the database
    void addRecord(RecordObject recordObject)  {

        try (Connection connection = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            String addSQLrecordObject = "INSERT INTO " + TABLE_NAME + "(" + CONSIGNOR_NAME_COLUMN + ", "+ PHONE_NO_COLUMN + ", " + ARTIST_NAME_COLUMN + ", " + TITLE_COLUMN + ", " + PRICE_COLUMN + ", " + DATE_COLUMN + ")" + " VALUES ('" + recordObject.consignorName + "' , '"
                    + recordObject.phone + "','" + recordObject.artistName + "','" + recordObject.title + "','"
                    + recordObject.price +"','" + recordObject.date + "')" ;

            statement.executeUpdate(addSQLrecordObject);

            //TO DO add a message dialog box with "Added cube solver record for 'cubesolver name'" message.

            statement.close();
            connection.close();

        } catch (SQLException se) {
            se.printStackTrace();
        }

    }
    void addRecordToSoldItemTable(RecordObjectSold recordObjectSold){
        try (Connection connection = DriverManager.getConnection(DB_CONNECTION_URL,USER,PASSWORD)){
            Statement statement = connection.createStatement();
            String addSQLDataToSoldTable = "INSERT INTO " + SOLD_TABLE_NAME + "(" + SOLD_CON_COL + ","+
                    SOLD_TITLE_COL+","+ SOLD_SOLD_PRICE_COL+"," + SOLD_DATE_COL + ")" + " VALUES ('"+recordObjectSold.Consignor + "','"
                    + recordObjectSold.title +"','"+recordObjectSold.SoldPrice+ "','"+ recordObjectSold.dateSold +"')";
            statement.executeUpdate(addSQLDataToSoldTable);
            statement.close();
            connection.close();
        }catch (SQLException sql){
            sql.printStackTrace();
        }
    }
    void addRecordToPayInfoItemTable(PayInfoObject payInfoObject){
        try (Connection connection = DriverManager.getConnection(DB_CONNECTION_URL,USER,PASSWORD)){
            Statement statement = connection.createStatement();
            String addSQLDataToPayInfoTable = "INSERT INTO " + PAYEE_TABLE_NAME + "(" + PAYEE_CON_NAME_COL + "," + PAYEE_SHARE_COL + "," + PAYEE_AMOUNT_PAID_COL + "," + PAYEE_BALANCE_DUE_COL + ")" + " VALUES ('" +
                    payInfoObject.payee + "','" + payInfoObject.shareAmount + "','" + payInfoObject.amountPaid + "','" +payInfoObject.amountOwed + "')";
            statement.executeUpdate(addSQLDataToPayInfoTable);
            statement.close();
            connection.close();
        }catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }
    void addRecordToBargainListTable(BargainListObject bargainListObject){
        try(Connection connection = DriverManager.getConnection(DB_CONNECTION_URL,USER,PASSWORD);
            Statement statement = connection.createStatement()){
            String addSQLDataToBargainListTable = "INSERT INTO " + BARGAIN_TABLE_NAME + "(" + BARGAIN_CON_COL + "," +
                    BARGAIN_TITLE_COL + "," + BARGAIN_ARTIST_NAME_COL + "," + BARGAIN_BASE_PRICE_COL + ") VALUES ('" + bargainListObject.Consignor
                    + "','" + bargainListObject.Title + "','"+ bargainListObject.Artist +"','" + bargainListObject.basePrice + "')";
            statement.executeUpdate(addSQLDataToBargainListTable);
            statement.close();
            connection.close();
        }catch (SQLException SQL){
            SQL.printStackTrace();
        }
    }
    // defines method that deletes data from the database
    void delete(RecordObject recordObject){
        try(Connection connection = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD)){
            Statement statement = connection.createStatement();
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
                int id = rs.getInt(PK_COLUMN);
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
    ArrayList<RecordObjectSold> fetchAllSoldRecords(){
        ArrayList<RecordObjectSold> allSoldRecords = new ArrayList();
        try(Connection connection = DriverManager.getConnection(DB_CONNECTION_URL,USER,PASSWORD);
            Statement statement = connection.createStatement()){
            String selectSQLSoldTable = "SELECT * FROM " + SOLD_TABLE_NAME;
            ResultSet rs = statement.executeQuery(selectSQLSoldTable);
            while (rs.next()){
                String cName = rs.getString(SOLD_CON_COL);
                double sPrice = rs.getDouble(SOLD_SOLD_PRICE_COL);
                String Title = rs.getString(SOLD_TITLE_COL);
                Date soldDate = rs.getDate(SOLD_DATE_COL);
                RecordObjectSold recordObjectSold = new RecordObjectSold(cName,Title,sPrice,(java.sql.Date)soldDate);
                allSoldRecords.add(recordObjectSold);}
            rs.close();
            statement.close();
            connection.close();
            return allSoldRecords;
        }catch (SQLException sql){
            sql.printStackTrace();
            return null;
        }
    }
    ArrayList<PayInfoObject> fetchAllPayInfoRecords(){
        ArrayList<PayInfoObject> allPayeeRecords = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(DB_CONNECTION_URL,USER,PASSWORD);
            Statement statement = connection.createStatement()){
            String selectSQLPayInfoTable = "SELECT * FROM "+ PAYEE_TABLE_NAME;
            ResultSet rs = statement.executeQuery(selectSQLPayInfoTable);
            while (rs.next()){
                String payeeName = rs.getString(PAYEE_CON_NAME_COL);
                double share = rs.getDouble(PAYEE_SHARE_COL);
                double amountPaid = rs.getDouble(PAYEE_AMOUNT_PAID_COL);
                double amountOwed = rs.getDouble(PAYEE_BALANCE_DUE_COL);
                PayInfoObject payInfoObject = new PayInfoObject(payeeName,share,amountPaid,amountOwed);
                allPayeeRecords.add(payInfoObject);
            }
            rs.close();
            statement.close();
            connection.close();
            return allPayeeRecords;
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }
    }
    ArrayList<BargainListObject> fetchAllBargainListRecords(){
        ArrayList<BargainListObject> allBargainListRecords = new ArrayList<>();
        try(Connection connection= DriverManager.getConnection(DB_CONNECTION_URL,USER,PASSWORD);
            Statement statement = connection.createStatement()){
            String selectSQLBargainListTable = "SELECT * FROM " + BARGAIN_TABLE_NAME;
            ResultSet rs = statement.executeQuery(selectSQLBargainListTable);
            while (rs.next()){
                String consignorName = rs.getString(BARGAIN_CON_COL);
                String artistName = rs.getString(BARGAIN_ARTIST_NAME_COL);
                String title = rs.getString(BARGAIN_TITLE_COL);
                double basePrice = rs.getDouble(BARGAIN_BASE_PRICE_COL);
                BargainListObject bargainListObject = new BargainListObject(consignorName,title,artistName,basePrice);
                allBargainListRecords.add(bargainListObject);
            }
            rs.close();
            statement.close();
            connection.close();
            return allBargainListRecords;
        }catch (SQLException SQLE){
            SQLE.printStackTrace();
            return null;
        }

    }
}