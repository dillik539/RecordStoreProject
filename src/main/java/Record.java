

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

/**
 * Created by dilli on 4/17/2017.
 */
public class Record extends JFrame{
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
    private JList<RecordObject> SoldItemJList;
    private JList<RecordObject> DonatedItemJList;
    private JList<RecordObject> PayInfoJList;
    private JLabel DisplayDaysJLabel;
    private JButton displayNoOfDaysButton;
    private DefaultListModel<RecordObject> DisplayListModel;
    private RecordDBcontroller recordDBcontroller;

    Record(RecordDBcontroller recordDBcontroller){
        setPreferredSize(new Dimension(800,400));
        this.recordDBcontroller = recordDBcontroller;
        DisplayListModel = new DefaultListModel<RecordObject>();
        InventoryJList.setModel(DisplayListModel);
        setContentPane(rootPanel);
        addItemsToComboBox();
        pack();
        setVisible(true);
        addListeners();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private void addItemsToComboBox(){
        AddToComboBox.addItem("Inventory");
        AddToComboBox.addItem("Sold Item");
        AddToComboBox.addItem("Bargain List");
        AddToComboBox.addItem("Donate Item");

        DisplayComboBox.addItem("All Inventory Records");
        DisplayComboBox.addItem("Sold Items");
        DisplayComboBox.addItem("Pay Information");
        DisplayComboBox.addItem("Donated Item");
        DisplayComboBox.addItem("No. of days in Inventory List");

        ReturnComboBox.addItem("Yes");
        ReturnComboBox.addItem("No");
    }
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
                java.sql.Date sqlrecordDate = new java.sql.Date(recordDate.getTime());

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
                if(AddToComboBox.getSelectedItem().equals("Inventory")) {
                    RecordObject recordObjectRecord = new RecordObject(cName, phone, aName, title, price,sqlrecordDate);
                    recordDBcontroller.addRecordToDatabase(recordObjectRecord);
                }else {
                    JOptionPane.showMessageDialog(Record.this,"Please select Inventory from Add To combo box");
                    return;
                }
                //Clear input JTextFields
                ConsignorNameTextField.setText("");
                PhoneNoTextField.setText("");
                ArtistNameTextField.setText("");
                TitleTextField.setText("");
                PriceTextField.setText("");

                //request all data from database to update list
                ArrayList<RecordObject> allData = recordDBcontroller.getAllData();
                setListData(allData);
            }
        });
        deleteRecordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RecordObject recordObject = InventoryJList.getSelectedValue();
                if(recordObject ==null) {
                    JOptionPane.showMessageDialog(Record.this, "Please select the record to delete");
                }else if(!ReturnComboBox.getSelectedItem().equals("Yes")){
                    JOptionPane.showMessageDialog(Record.this,"Please select 'Yes' from the 'Return to Consignor?' combox box");
                }else {
                    recordDBcontroller.delete(recordObject);
                    ArrayList<RecordObject> recordObject1 = recordDBcontroller.getAllData();
                    setListData(recordObject1);
                }
            }
        });
        displayNoOfDaysButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayNumberOfDays();
            }
        });
    }
    private void displayNumberOfDays(){
        java.sql.Date displayDays = InventoryJList.getSelectedValue().getDate();
        java.lang.String displayDaysString = displayDays.toString();
        DisplayDaysJLabel.setText(displayDaysString);
    }
    void setListData(ArrayList<RecordObject> data) {
        DisplayListModel.clear();
        //add cubes object to display list model.
        for (RecordObject rob : data) {
            DisplayListModel.addElement(rob);
        }
    }
}
