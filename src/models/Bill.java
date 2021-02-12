package models;

public class Bill {
    private String idBill;
    private String idCus;
    private String dateBill;
    private int consumption;
    private int unitPrice;
    private int total;

    public Bill() {
    }

    public Bill(String idBill, String idCus, String dateBill, int consumption, int unitPrice, int total) {
        this.idBill = idBill;
        this.idCus = idCus;
        this.dateBill = dateBill;
        this.consumption = consumption;
        this.unitPrice = unitPrice;
        this.total = total;
    }

    public Bill(String[] line) {
        this.idBill = line[0];
        this.idCus = line[1];
        this.dateBill = line[2];
        this.consumption = Integer.parseInt(line[3]);
        this.unitPrice = Integer.parseInt(line[4]);
        this.total = Integer.parseInt(line[5]);
    }

    public String getIdBill() {
        return idBill;
    }

    public void setIdBill(String idBill) {
        this.idBill = idBill;
    }

    public String getIdCus() {
        return idCus;
    }

    public void setIdCus(String idCus) {
        this.idCus = idCus;
    }

    public String getDateBill() {
        return dateBill;
    }

    public void setDateBill(String dateBill) {
        this.dateBill = dateBill;
    }

    public int getConsumption() {
        return consumption;
    }

    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return idBill + "," + idCus + "," + dateBill + "," + consumption + "," + unitPrice + "," + total;
    }

    public String showInfor() {
        return "BILL\n" +
                "ID Bill " + idBill +
                ", ID Customer " + idCus +
                ", Date " + dateBill + "\n" +
                "Consumption " + consumption +
                ", Unit price " + unitPrice +
                ", Total " + total + "\n";
    }
}
