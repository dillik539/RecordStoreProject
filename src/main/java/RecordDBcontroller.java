import java.util.ArrayList;

/**
 This class controls the database
 */
public class RecordDBcontroller {
    //Record object defined
    static Record record;
    //RecordDB object defined
    static RecordDB recordDB;
    public static void main(String[] args) {
        //new RecordDBcontroller
        RecordDBcontroller recordDBcontroller = new RecordDBcontroller();
        recordDBcontroller.startApp();//calls a method
    }
    //defines a method that triggers the process to create tables and other essentials
    private void startApp(){
        recordDB = new RecordDB();//new recordDB object
        recordDB.createTable();//calls to a method
        record = new Record(this);
    }
    //defines method that deletes recordObject from the list.
    void delete(RecordObject recordObject){
        recordDB.delete(recordObject);
    }
    //defines method that deletes bargain object from the list.
    void deleteBargainObject(BargainListObject bargainListObject){
        recordDB.deleteBargainListItem(bargainListObject);
    }
    //defines a method
    ArrayList<RecordObject> getAllData() {
        return recordDB.fetchAllRecords();//calls a method
    }
    //defines a method
    ArrayList<RecordObjectSold> getAllSoldData(){
        return recordDB.fetchAllSoldRecords();//calls a method
    }
    //defines a method
    ArrayList<PayInfoObject> getAllPayInfoData(){
        return recordDB.fetchAllPayInfoRecords();//calls a method
    }
    //defines a method
    ArrayList<BargainListObject> getAllBargainListData(){
        return recordDB.fetchAllBargainListRecords();//calls a method
    }
    //defines a method
    ArrayList<DonatedItemObject> getAllDonatedItemData(){
        return recordDB.fetchAllDonatedItemRecords();//calls a method
    }
    //defines a method
    void addRecordToDatabase(RecordObject recordObject) {
        recordDB.addRecord(recordObject);//calls a method
    }
    //defines a method
    void addRecordToSoldTable(RecordObjectSold recordObjectSold){
        recordDB.addRecordToSoldItemTable(recordObjectSold);//calls a method
    }
    //defines a method
    void addRecordToPayInfoTable(PayInfoObject payInfoObject){
        recordDB.addRecordToPayInfoItemTable(payInfoObject);//calls a method
    }
    //defines a method
   void addRecordToBargainTable(BargainListObject bargainListObject){
        recordDB.addRecordToBargainListTable(bargainListObject);//calls a method
    }
    //defines a method
    void addRecordToDonatedTable(DonatedItemObject donatedItemObject){
       recordDB.addRecordToDonatedItemTable(donatedItemObject);//calls a method
    }


}
