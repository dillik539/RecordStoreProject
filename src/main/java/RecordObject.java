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

    public java.sql.Date getDate() {
        return date;
    }

    //constructor
    RecordObject(String cName, String pn, String aName, String t, double pr, java.sql.Date d){
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
