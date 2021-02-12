package services;

import commons.read_write.ReadAndWrite;
import models.VietnamCustomer;

import java.util.ArrayList;
import java.util.List;

public class VietnamManagement implements CRUDInterface<VietnamCustomer>{
    static ReadAndWrite<VietnamCustomer> readAndWrite = new ReadAndWrite<>();
    @Override
    public void add(VietnamCustomer vietnamCustomer) {
        List<VietnamCustomer> list = new ArrayList<>();
        list.add(vietnamCustomer);
        readAndWrite.writeToFile("customer.csv",list,true);
    }

    @Override
    public void addAll(List<VietnamCustomer> list) {
        readAndWrite.writeToFile("customer.csv",list,false);
    }

    @Override
    public void show(List<VietnamCustomer> list) {

    }

    @Override
    public List<VietnamCustomer> findAll() {
        List<String[]> list = readAndWrite.readFromFile("customer.csv");
        List<VietnamCustomer> listVN = new ArrayList<>();
        for(String[] line : list) {
            if (line[0].contains("KHVN")) {
                VietnamCustomer vietnamCustomer = new VietnamCustomer(line);
                listVN.add(vietnamCustomer);
            }
        }
        return listVN;
    }
}
