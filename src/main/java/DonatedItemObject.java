/**
 this class creates a donated item object
 */
public class DonatedItemObject {
    //variables
    private String ConsignorName;
    private String RecordTitle;
    private String ArtistName;

    //getters and setters
    public String getConsignorName() {
        return ConsignorName;
    }

    public void setConsignorName(String consignorName) {
        ConsignorName = consignorName;
    }

    public String getRecordTitle() {
        return RecordTitle;
    }

    public void setRecordTitle(String recordTitle) {
        RecordTitle = recordTitle;
    }

    public String getArtistName() {
        return ArtistName;
    }

    public void setArtistName(String artistName) {
        ArtistName = artistName;
    }
    //a constructor
    DonatedItemObject(String cName, String rTitle, String aName){
        ConsignorName = cName;
        RecordTitle = rTitle;
        ArtistName = aName;
    }
    //toString method
    @Override
    public String toString(){
        return "Consignor Name: " + this.ConsignorName + "   Record Title: " + this.RecordTitle + "   Artist Name: " + this.ArtistName;
    }
}
