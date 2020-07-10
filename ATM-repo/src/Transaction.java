public class Transaction  {

    private String type;
    private double value;

    public String getType() {
        return type;
    }

    public double getValue() {
        return value;
    }




    public Transaction(String type, double value) {
        this.type = type;
        this.value = value;
    }

}
