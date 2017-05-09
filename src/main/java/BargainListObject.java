
/**
 this class creates a bargain list object
 */
public class BargainListObject {
    //variables
    private String Consignor;
    private String Artist;
    private String Title;
    private double basePrice;
    private java.sql.Date dateAddedToBargainList;//sql date

    //getters and setters
    public java.sql.Date getDateAddedToBargainList() {
        return dateAddedToBargainList;
    }

    public void setDateAddedToBargainList(java.sql.Date dateAddedToBargainList) {
        this.dateAddedToBargainList = dateAddedToBargainList;
    }

    public String getConsignor() {
        return Consignor;
    }

    public void setConsignor(String consignor) {
        Consignor = consignor;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public String getArtist() {
        return Artist;
    }

    public void setArtist(String artist) {
        Artist = artist;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    //a constructor
    BargainListObject(String Cname, String title, String aName, double bPrice,java.sql.Date date){
        Consignor = Cname;
        Artist = aName;
        Title = title;
        basePrice = bPrice;
        dateAddedToBargainList = date;

    }
    //toString method
    @Override
    public String toString(){
        return "Consignor: " + this.Consignor + "   Title: " + this.Title + "   Artist: " + this.Artist + "   Base Price: " + this.basePrice
                + "   Date Added: " + this.dateAddedToBargainList;
    }
}
