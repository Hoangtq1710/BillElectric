package models;

public class VietnamCustomer extends Customer {
    private String typeCustomer;
    private int consume;

    public VietnamCustomer() {
    }

    public VietnamCustomer(String idCustomer, String nameCustomer, String typeCustomer, int consume) {
        super(idCustomer, nameCustomer);
        this.typeCustomer = typeCustomer;
        this.consume = consume;
    }

    public VietnamCustomer(String[] line) {
        super(line[0],line[1]);
        this.typeCustomer = line[2];
        this.consume = Integer.parseInt(line[3]);
    }

    public String getTypeCustomer() {
        return typeCustomer;
    }

    public void setTypeCustomer(String typeCustomer) {
        this.typeCustomer = typeCustomer;
    }

    public int getConsume() {
        return consume;
    }

    public void setConsume(int consume) {
        this.consume = consume;
    }

    @Override
    public String toString() {
        return super.toString()+","+typeCustomer+","+consume;
    }

    @Override
    public String showInfor() {
        return "VIETNAM\n"+
                super.showInfor()+
                ", type Customer "+typeCustomer+
                ", Consume "+consume+"\n";
    }
}
