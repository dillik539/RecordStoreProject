/**
 this class creates a pay info object
 */
public class PayInfoObject {
    //variables
    private String payee;
    private double shareAmount;
    private double amountPaid;
    private double amountOwed;

    //getters and setters
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
    //constructor
    PayInfoObject(String cPayee, double share, double paid, double owed){
        payee = cPayee;
        shareAmount = share;
        amountPaid = paid;
        amountOwed = owed;
    }
    //a toString method
    @Override
    public String toString(){
       return "Consignor: " + this.payee + "   Share: " + this.shareAmount + "   Amount Paid: " + this.amountPaid + "   Amount Owed: " + this.amountOwed;
    }
}
