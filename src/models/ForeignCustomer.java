package models;

public class ForeignCustomer extends Customer {
    private String nationality;

    public ForeignCustomer() {
    }

    public ForeignCustomer(String idCustomer, String nameCustomer, String nationality) {
        super(idCustomer, nameCustomer);
        this.nationality = nationality;
    }

    public ForeignCustomer(String[] line) {
        super(line[0],line[1]);
        this.nationality = line[2];
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return super.toString()+","+nationality;
    }

    @Override
    public String showInfor() {
        return "FOREIGN Customer \n"+
                super.showInfor()+
                ", Nationality "+nationality+"\n";
    }
}
