import java.sql.Date;

/**
 * Created by dilli on 4/29/2017.
 */
public class RecordObjectSold {
    String Consignor;
    double SoldPrice;
    String title;
    java.sql.Date dateSold;

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

    RecordObjectSold(String cName, String t, double sPrice, java.sql.Date dSold) {
        Consignor = cName;
        SoldPrice = sPrice;
        title = t;
        dateSold = dSold;

    }
    @Override
    public String toString(){
        return "Consignor Name: "+ this.Consignor+   "  Sold Price: "
                + this.SoldPrice + "  Title: "+ this.title + "   Sold on: " + this.dateSold;
    }
}
