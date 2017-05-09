
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;

/*
this class prepares GUI and sets limits and validation on it.
 */
public class Record extends JFrame{
    //defines GUI components
    private JTextField ConsignorNameTextField;
    private JTextField PriceTextField;
    private JTextField TitleTextField;
    private JTextField ArtistNameTextField;
    private JTextField PhoneNoTextField;
    private JComboBox AddToComboBox;
    private JComboBox DisplayComboBox;
    private JPanel rootPanel;
    private JButton addRecordButton;
    private JButton showRecordButton;
    private JComboBox ReturnComboBox;
    private JButton deleteRecordButton;
    private JList<RecordObject> InventoryJList;
    private JList<RecordObjectSold> SoldItemJList;
    private JList<BargainListObject> BargainJList;
    private JList<PayInfoObject> PayInfoJList;
    private JList<DonatedItemObject> DonatedItemJList;
    private JLabel DisplayDaysJLabel;
    private JButton displayNoOfDaysButton;
    private JLabel NotifyConsignorLabel;
    private JLabel BargianListLabel;
    //defines ListModels
    private DefaultListModel<RecordObject> InventoryListModel;
    private DefaultListModel<RecordObjectSold> SoldItemListModel;
    private DefaultListModel<PayInfoObject> PayInfoListModel;
    private DefaultListModel<BargainListObject> BargainListModel;
    private DefaultListModel<DonatedItemObject> DonatedListModel;
    private RecordDBcontroller recordDBcontroller;
    //defines a constructor
    Record(RecordDBcontroller recordDBcontroller){
        setPreferredSize(new Dimension(900,400));
        this.recordDBcontroller = recordDBcontroller;
        //creates new ListModel objects
        InventoryListModel = new DefaultListModel<RecordObject>();
        SoldItemListModel = new DefaultListModel<RecordObjectSold>();
        PayInfoListModel = new DefaultListModel<PayInfoObject>();
        BargainListModel = new DefaultListModel<BargainListObject>();
        DonatedListModel = new DefaultListModel<DonatedItemObject>();
        InventoryJList.setModel(InventoryListModel);
        SoldItemJList.setModel(SoldItemListModel);
        PayInfoJList.setModel(PayInfoListModel);
        BargainJList.setModel(BargainListModel);
        DonatedItemJList.setModel(DonatedListModel);
        setContentPane(rootPanel);
        //calls a method
        addItemsToComboBox();
        pack();
        setVisible(true);
        //calls method
        addListeners();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    //defines a method that adds items to combo boxes
    private void addItemsToComboBox(){
        AddToComboBox.addItem("Inventory List");
        AddToComboBox.addItem("Sold Items");
        AddToComboBox.addItem("Bargain List");
        AddToComboBox.addItem("Donated Items");

        DisplayComboBox.addItem("All Inventory Records");
        DisplayComboBox.addItem("Sold Items");
        DisplayComboBox.addItem("Pay Information");
        DisplayComboBox.addItem("Bargain List Items");
        DisplayComboBox.addItem("Donated Items");
        DisplayComboBox.addItem("No.of days in Inventory List");

        ReturnComboBox.addItem("Yes");
        ReturnComboBox.addItem("No");
    }
    //defines a method that adds listeners to various JButtons.
    private void addListeners(){
        addRecordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Read data, send message to database via controller
                String cName = ConsignorNameTextField.getText();
                String phone = PhoneNoTextField.getText();
                String aName = ArtistNameTextField.getText();
                String title = TitleTextField.getText();
                java.util.Date recordDate = new java.util.Date();
                //converts java utility date to sql date
                java.sql.Date sqlrecordDate = new java.sql.Date(recordDate.getTime());

                if(AddToComboBox.getSelectedItem().equals("Inventory List")) {
                    if (cName.isEmpty()) {
                        JOptionPane.showMessageDialog(Record.this, "Enter Consignor's name");
                        return;
                    }else if(phone.isEmpty()){
                        JOptionPane.showMessageDialog(Record.this,"Enter the phone number");
                        return;
                    }else if(aName.isEmpty()){
                        JOptionPane.showMessageDialog(Record.this,"Enter name of the artist");
                        return;
                    }else if(title.isEmpty()){
                        JOptionPane.showMessageDialog(Record.this,"Enter the title");
                        return;
                    }
                    double price;

                    try {
                        price = Double.parseDouble(PriceTextField.getText());
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(Record.this, "Enter the number for price");
                        return;
                    }
                    //creates new RecordObject object
                    RecordObject recordObjectRecord = new RecordObject(cName, phone, aName, title, price,sqlrecordDate);
                    recordDBcontroller.addRecordToDatabase(recordObjectRecord);//add object to database table
                    //Clear input JTextFields
                    ConsignorNameTextField.setText("");
                    PhoneNoTextField.setText("");
                    ArtistNameTextField.setText("");
                    TitleTextField.setText("");
                    PriceTextField.setText("");
                    JOptionPane.showMessageDialog(Record.this,"Record added to Inventory Records table");

                }
                else if (AddToComboBox.getSelectedItem().equals("Sold Items")){
                    if(InventoryJList.getSelectedValue()== null){
                        JOptionPane.showMessageDialog(Record.this, "Please select an item from the Inventory Records");
                    }else {
                        //takes user input for selling price
                        String sPriceString = JOptionPane.showInputDialog(Record.this, "Please enter the selling price");
                        double sPrice = Double.parseDouble(sPriceString);//converts string to double
                        double sharePercent = 0.4; //consignor's share percent
                        double consignorShare = sPrice * sharePercent; //calculates actual share
                        RecordObject recordSold = InventoryJList.getSelectedValue();//assigns selected JList object to new recordObject object
                        String conName = recordSold.getConsignorName(); //gets consignor name from new recordObject's object
                        String sTitle = recordSold.getTitle();
                        Date soldDate = new Date();
                        java.sql.Date soldDateSQL = new java.sql.Date(soldDate.getTime());//converts date to sqldate format
                        //new RecordObjectSold object
                        RecordObjectSold recordObjectSold = new RecordObjectSold(conName, sTitle, sPrice, soldDateSQL);
                        recordDBcontroller.addRecordToSoldTable(recordObjectSold);//adds new recordsObjectSold's object to database table
                        recordDBcontroller.delete(recordSold);//deletes selected object from Inventory list after it is added to sold item list.
                        JOptionPane.showMessageDialog(Record.this, "A record added to Sold Items table");
                        String payNowString = JOptionPane.showInputDialog(Record.this, "Please enter the amount you want to pay now to the consignor");
                        double payNow = Double.parseDouble(payNowString);
                        double amountOwed = consignorShare - payNow;
                        //creates new PayInfoObject object
                        PayInfoObject payInfoObject = new PayInfoObject(conName, consignorShare, payNow, amountOwed);
                        recordDBcontroller.addRecordToPayInfoTable(payInfoObject); //adds object to database table
                        JOptionPane.showMessageDialog(Record.this,"Record added to Sold Item table");
                    }
                }
                else if(AddToComboBox.getSelectedItem().equals("Bargain List")) {
                    java.sql.Date recordAddedDate = InventoryJList.getSelectedValue().getDate();
                    Date now = new Date();
                    long date = now.getTime() - recordAddedDate.getTime(); //gets time difference of two dates
                    long msInDay = 1000*60*60*24;//millisecond in one day
                    long days = date/msInDay;//calculates number of days
                    String StringDays = Long.toString(days);
                    int intDays = Integer.parseInt(StringDays);//converts to int
                    int maxDaysInInventory = 30;//number of days a record can stay in inventory list before it gets moved to bargain list
                    if (InventoryJList.getSelectedValue() == null) {
                        JOptionPane.showMessageDialog(Record.this, "Please select an item from the Inventory Records");
                    }
                    else{
                        if (intDays < maxDaysInInventory) {
                        JOptionPane.showMessageDialog(Record.this, "Oops! can't add this item to Bargain list before 30 days\n" +
                                "of it's stay in Inventory List");
                        } else {
                            RecordObject bargainRecord = InventoryJList.getSelectedValue();//selected value assigns to new RecordObject's object
                        String conName = bargainRecord.getConsignorName();
                        String artistName = bargainRecord.getArtistName();
                        String rTitle = bargainRecord.getTitle();
                        double basePrice = 1;//base price of record set to $1.
                        Date dataAddedToBargainList = new Date();
                        java.sql.Date sqlBargainDate = new java.sql.Date(dataAddedToBargainList.getTime());
                        //new bargainlist object
                        BargainListObject bargainListObject = new BargainListObject(conName, rTitle, artistName, basePrice,sqlBargainDate);
                        recordDBcontroller.addRecordToBargainTable(bargainListObject);//adds bargainlist object to database table.
                        recordDBcontroller.delete(bargainRecord);//deletes bargainRecord object from the database table
                        JOptionPane.showMessageDialog(Record.this,"Record added to Bargain List table");
                        }
                    }
                }
                else if(AddToComboBox.getSelectedItem().equals("Donated Items")){
                    if(BargainJList.getSelectedValue() == null){
                        JOptionPane.showMessageDialog(Record.this, "Please select an item from the Bargain List");
                    }
                    else {
                        java.sql.Date recordAddedDate = BargainJList.getSelectedValue().getDateAddedToBargainList();
                        Date now = new Date();
                        long date = now.getTime() - recordAddedDate.getTime();
                        long msInDay = 1000 * 60 * 60 * 24;
                        long days = date / msInDay;
                        String StringDays = Long.toString(days);
                        int intDays = Integer.parseInt(StringDays);
                        int maxDaysInBargainList = 365; // number of days to stay in Bargain List before moving to donated item.
                            if (intDays< maxDaysInBargainList){
                                JOptionPane.showMessageDialog(Record.this, "Oops! can't add this item to Donated Items before one year\n" +
                                        "of it's stay in Bargain List");
                            } else {
                                BargainListObject donatedRecord = BargainJList.getSelectedValue();
                                String conName = donatedRecord.getConsignor();
                                String Title = donatedRecord.getTitle();
                                String artistName = donatedRecord.getArtist();
                                //new donated item object
                                DonatedItemObject donatedItemObject = new DonatedItemObject(conName,Title,artistName);
                                recordDBcontroller.addRecordToDonatedTable(donatedItemObject);//adds object to database table
                                recordDBcontroller.deleteBargainObject(donatedRecord);//deletes record from the database table
                                JOptionPane.showMessageDialog(Record.this,"Record added to Donated Item table");
                            }
                        }
                }
            }
        });
        showRecordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //displays inventory table records
                if(DisplayComboBox.getSelectedItem().equals("All Inventory Records")){
                        //request all data from database to update list
                        ArrayList<RecordObject> allData = recordDBcontroller.getAllData();
                        setListData(allData);
                    if(InventoryListModel.isEmpty()){
                        JOptionPane.showMessageDialog(Record.this,"Inventory table is empty");
                    }

                }
                //displays sold item table records
                if(DisplayComboBox.getSelectedItem().equals("Sold Items")){
                        ArrayList<RecordObjectSold> allSoldData = recordDBcontroller.getAllSoldData();
                        setListOfSoldData(allSoldData);
                    if(SoldItemListModel.isEmpty()){
                        JOptionPane.showMessageDialog(Record.this,"Sold Item table is empty");
                    }
                }
                //displays pay information table records.
                if(DisplayComboBox.getSelectedItem().equals("Pay Information")){
                        ArrayList<PayInfoObject> allPayInfoData = recordDBcontroller.getAllPayInfoData();
                        setListOfPayInfoData(allPayInfoData);
                    if(PayInfoListModel.isEmpty()){
                        JOptionPane.showMessageDialog(Record.this,"Pay information table is empty");
                    }
                }
                //displays number of days a record has stayed in inventory list
                if(DisplayComboBox.getSelectedItem().equals("No.of days in Inventory List")){
                    if(InventoryJList.getSelectedValue()!=null) {
                        displayNumberOfDays();
                    }else {
                        JOptionPane.showMessageDialog(Record.this,"Please select an item from the Inventory list to display number of days");
                    }
                }
                //displays bargain list table records.
                if(DisplayComboBox.getSelectedItem().equals("Bargain List Items")){
                        ArrayList<BargainListObject> allBargainListData = recordDBcontroller.getAllBargainListData();
                        setListOfBargainListData(allBargainListData);
                    if(BargainListModel.isEmpty()){
                        JOptionPane.showMessageDialog(Record.this,"Bargain list table is empty");
                    }
                }
                //displays donated item table records.
                if(DisplayComboBox.getSelectedItem().equals("Donated Items")){
                        ArrayList<DonatedItemObject> allDonatedItemData = recordDBcontroller.getAllDonatedItemData();
                        setListOfDonatedItemData(allDonatedItemData);
                    if(DonatedListModel.isEmpty()){
                        JOptionPane.showMessageDialog(Record.this,"Donated Item table is empty");
                    }
                }
            }
        });
        deleteRecordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //deletes selected record object from the inventory JList.
                RecordObject recordObject = InventoryJList.getSelectedValue();
                if (recordObject == null) {
                    JOptionPane.showMessageDialog(Record.this, "Please select the record to delete");
                } else if (!ReturnComboBox.getSelectedItem().equals("Yes")) {
                    JOptionPane.showMessageDialog(Record.this, "Please select 'Yes' from the 'Return to Consignor?' combox box");
                }
                if (recordObject!=null && ReturnComboBox.getSelectedItem().equals("Yes")){
                    int delete = JOptionPane.showConfirmDialog(Record.this, "Are you sure you want to delete this item?",
                            "Delete", JOptionPane.OK_CANCEL_OPTION);
                    if(delete == JOptionPane.OK_OPTION) {
                        recordDBcontroller.delete(recordObject);
                        ArrayList<RecordObject> recordObject1 = recordDBcontroller.getAllData();
                        setListData(recordObject1);
                        JOptionPane.showMessageDialog(Record.this, "The selected item has been deleted from the list");
                    }
                }
            }
        });
    }
    //defines method that displays number of days a record has stayed in inventory list
    private void displayNumberOfDays(){
        java.sql.Date displayDays = InventoryJList.getSelectedValue().getDate();
        Date now = new Date();
        long date = now.getTime() - displayDays.getTime();
        long msInDay = 1000*60*60*24;
        long days = date/msInDay;
        String StringDays = Long.toString(days);
        int intDays = Integer.parseInt(StringDays);
        DisplayDaysJLabel.setText(StringDays);
        if(intDays>30){
            NotifyConsignorLabel.setText("Notify consignor to pick item");
        }else{
            //displays remaining days to stay in the list
            int daysDiff = 30-intDays;
            String daysDiffString = Integer.toString(daysDiff);
            NotifyConsignorLabel.setText(daysDiffString+" days to stay in this list");
        }
    }
    void setListData(ArrayList<RecordObject> data) {
        InventoryListModel.clear();//clears the List Model before adding any object
        //adds RecordObject object to inventory list model.
        for (RecordObject rob : data) {
            InventoryListModel.addElement(rob);
        }
    }
    void setListOfSoldData(ArrayList<RecordObjectSold> soldData){
        SoldItemListModel.clear();//clears the list model
        //adds object to sold item list model
        for(RecordObjectSold robs : soldData){
            SoldItemListModel.addElement(robs);
        }
    }
    void setListOfPayInfoData(ArrayList<PayInfoObject> payInfoData){
        PayInfoListModel.clear();//clears the list
        //adds objects to pay info list model
        for(PayInfoObject pob : payInfoData){
            PayInfoListModel.addElement(pob);
        }
    }
    void setListOfBargainListData(ArrayList<BargainListObject> bargainListData){
        BargainListModel.clear();//clears the list
        //adds objects to bargain list model
        for(BargainListObject bob: bargainListData){
            BargainListModel.addElement(bob);
        }
    }
    void setListOfDonatedItemData(ArrayList<DonatedItemObject> donatedItemData){
        DonatedListModel.clear();//clears the list
        //adds object to donated list model
        for(DonatedItemObject dob: donatedItemData){
            DonatedListModel.addElement(dob);
        }
    }
}