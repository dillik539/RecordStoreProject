import java.util.Date;
/**
 * Created by dilli on 4/25/2017.
 */
public class RecordObject {
    String consignorName;
    String phone;
    String artistName;
    String title;
    double price;
    java.sql.Date date;

    public String getConsignorName() {
        return consignorName;
    }

    public void setConsignorName(String consignorName) {
        this.consignorName = consignorName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    public java.sql.Date getDate() {
        return date;
    }

    //constructor
    RecordObject(String cName, String pn, String aName, String t, double pr,  java.sql.Date d){
        consignorName = cName;
        phone = pn;
        artistName = aName;
        title = t;
        price = pr;
        date = d;
    }
    @Override
    public String toString(){
        return "Consignor: "+consignorName + "   Phone: "+phone + "   Artist: "+artistName + "   Title: "+title + "   Price: "+price;
    }
}
 class RecordObjectSold {
    String Consignor;
    double SoldPrice;
    String title;

     public double getSoldPrice() {
         return SoldPrice;
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

     RecordObjectSold(String cName,double sPrice, String t) {
         Consignor = cName;
         SoldPrice = sPrice;
         title = t;

     }
     @Override
     public String toString(){
         return "Consignor: "+ Consignor+ "Title: "+ title + "Sold Price: "+SoldPrice;
     }
 }
