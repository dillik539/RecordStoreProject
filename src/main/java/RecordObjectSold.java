/**
 * Created by dilli on 4/29/2017.
 */
public class RecordObjectSold {
    String Consignor;
    double SoldPrice;
    String title;

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

    RecordObjectSold(String cName, double sPrice, String t) {
        Consignor = cName;
        SoldPrice = sPrice;
        title = t;

    }
    @Override
    public String toString(){
        return "Consignor: "+ this.Consignor+ "Title: "+ this.title + "Sold Price: "+this.SoldPrice;
    }
}
