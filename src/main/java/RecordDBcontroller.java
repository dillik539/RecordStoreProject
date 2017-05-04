import java.util.ArrayList;

/**
 * Created by dilli on 4/17/2017.
 */
public class RecordDBcontroller {
    static Record record;
    static RecordDB recordDB;
    public static void main(String[] args) {
        RecordDBcontroller recordDBcontroller = new RecordDBcontroller();
        recordDBcontroller.startApp();
    }
    private void startApp(){
        recordDB = new RecordDB();
        recordDB.createTable();
        //ArrayList<RecordObject> allData = recordDB.fetchAllRecords();
        record = new Record(this);
        //record.setListData(allData);
    }
    void delete(RecordObject recordObject){
        recordDB.delete(recordObject);
    }
//    void updateRecord(RecordObject recordObject){
//        recordDB.updateRecord(recordObject);
//    }


    ArrayList<RecordObject> getAllData() {
        return recordDB.fetchAllRecords();
    }
    ArrayList<RecordObjectSold> getAllSoldData(){
        return recordDB.fetchAllSoldRecords();
    }
    ArrayList<PayInfoObject> getAllPayInfoData(){
        return recordDB.fetchAllPayInfoRecords();
    }
    ArrayList<BargainListObject> getAllBargainListData(){
        return recordDB.fetchAllBargainListRecords();
    }

    void addRecordToDatabase(RecordObject recordObject) {
        recordDB.addRecord(recordObject);
    }
    void addRecordToSoldTable(RecordObjectSold recordObjectSold){
        recordDB.addRecordToSoldItemTable(recordObjectSold);
    }
    void addRecordToPayInfoTable(PayInfoObject payInfoObject){
        recordDB.addRecordToPayInfoItemTable(payInfoObject);
    }
   void addRecordToBargainTable(BargainListObject bargainListObject){
        recordDB.addRecordToBargainListTable(bargainListObject);
    }


}
