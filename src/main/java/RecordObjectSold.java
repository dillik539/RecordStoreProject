/**
 * Created by dilli on 4/29/2017.
 */
public class RecordObjectSold {
    int ConsignorID;
    double SoldPrice;
    String title;

    public double getSoldPrice() {
        return SoldPrice;
    }

    public int getConsignor() {
        return ConsignorID;
    }

    public void setConsignor(int consignorID) {
        ConsignorID = consignorID;
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

    RecordObjectSold(int CID, double sPrice, String t) {
        ConsignorID = CID;
        SoldPrice = sPrice;
        title = t;

    }
    @Override
    public String toString(){
        return "Consignor: "+ this.ConsignorID+ "  Title: "+ this.title + "  Sold Price: "+this.SoldPrice;
    }
}
