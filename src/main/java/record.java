import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.LinkedList;

/**
 * Created by dilli on 4/17/2017.
 */
public class record extends JFrame{
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
    private JTable InventoryTable;
    private JTable SoldItemTable;
    private JTable DonatedItemsTable;
    private JTable PayInformationTable;

    record(){
        this.setContentPane(rootPanel);
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

        pack();
        setVisible(true);

        //creates a data model and tells the table to use it.
        InventoryTableModel inventoryTableModel = new InventoryTableModel();
        InventoryTable.setModel(inventoryTableModel);
    }
}
class  InventoryTableModel extends AbstractTableModel{
    LinkedList<String> InventoryList;
    InventoryTableModel(){
        InventoryList = new LinkedList<String>();
        ///String consignorName = record.ConsignorNameTextField.getText();
        //InventoryList.add(consignorName);
        //String phoneNo = (String)record.PhoneNoTextField.getText();
        //InventoryList.add(phoneNo);
    }
    @Override
    public int  getRowCount(){
        return InventoryList.size();
    }
    @Override
    public int getColumnCount(){
        return 2;
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        if(columnIndex ==0){
            return rowIndex;
        }else if (columnIndex == 1){
            return InventoryList.get(rowIndex);
        }else {
            return null;
        }
    }
}
