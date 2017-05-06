/**
 * Created by dilli on 5/3/2017.
 */
public class BargainListObject {
    String Consignor;
    String Artist;
    String Title;
    double basePrice;
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


    BargainListObject(String Cname, String title, String aName, double bPrice){
        Consignor = Cname;
        Artist = aName;
        Title = title;
        basePrice = bPrice;

    }
    @Override
    public String toString(){
        return "Consignor: " + this.Consignor + "   Title: " + this.Title + "   Artist: " + this.Artist + "   Base Price: " + this.basePrice;
    }
}
