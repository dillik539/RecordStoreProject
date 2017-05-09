import java.sql.Date;

/**
 This class creates sold item object
 */
public class RecordObjectSold {
    //variables
    private String Consignor;
    private double SoldPrice;
    private String title;
    private java.sql.Date dateSold;

    //getters and setters
    public Date getDateSold() {
        return dateSold;
    }

    public void setDateSold(Date dateSold) {
        this.dateSold = dateSold;
    }

    public double getSoldPrice() {

        return SoldPrice;
    }

    public String getConsignor() {
        return Consignor;
    }

    public void setConsignor(String consignor) {
        Consignor = consignor;
    }

    public void setSoldPrice(double soldPrice) {
        SoldPrice = soldPrice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    //a constructor
    RecordObjectSold(String cName, String t, double sPrice, java.sql.Date dSold) {
        Consignor = cName;
        SoldPrice = sPrice;
        title = t;
        dateSold = dSold;
    }
    //a toString method
    @Override
    public String toString(){
        return "Consignor Name: "+ this.Consignor+   "  Sold Price: "
                + this.SoldPrice + "  Title: "+ this.title + "   Sold on: " + this.dateSold;
    }
}
