/**
 * Created by dilli on 5/2/2017.
 */
public class PayInfoObject {
    String payee;
    double shareAmount;
    double amountPaid;
    double amountOwed;

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public double getShareAmount() {
        return shareAmount;
    }

    public void setShareAmount(double shareAmount) {
        this.shareAmount = shareAmount;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public double getAmountOwed() {
        return amountOwed;
    }

    public void setAmountOwed(double amountOwed) {
        this.amountOwed = amountOwed;
    }

    PayInfoObject(String cPayee, double share, double paid, double owed){
        payee = cPayee;
        shareAmount = share;
        amountPaid = paid;
        amountOwed = owed;
    }
    @Override
    public String toString(){
       return "Consignor: " + this.payee + "   Share: " + this.shareAmount + "   Amount Paid: " + this.amountPaid + "   Amount Owed: " + this.amountOwed;
    }
}
