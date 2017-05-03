/**
 * Created by dilli on 5/3/2017.
 */
public class BargainListObject {
    String Consignor;
    String Artist;

    public String getConsignor() {
        return Consignor;
    }

    public void setConsignor(String consignor) {
        Consignor = consignor;
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

    String Title;
    BargainListObject(String Cname, String aName,String title){
        Consignor = Cname;
        Artist = aName;
        Title = title;
    }
    @Override
    public String toString(){
        return "Consignor: " + this.Consignor + "   Artist: " + this.Artist + "   Title: " + this.Title;
    }
}
