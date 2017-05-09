import java.util.Date;
/**
 This class creates inventory record object
 */
public class RecordObject {
    //variables
    private String consignorName;
    private String phone;
    private String artistName;
    private String title;
    private double price;
    private java.sql.Date date;

    //getters and setters
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
    //a toString method
    @Override
    public String toString(){
        return "Consignor: " + consignorName + "   Phone: " + phone + "   Artist: "+ artistName + "   Title: " + title + "   Price: "+price;
    }
}