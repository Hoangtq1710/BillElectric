package services;

import commons.read_write.ReadAndWrite;
import models.ForeignCustomer;
import models.VietnamCustomer;

import java.util.ArrayList;
import java.util.List;

public class ForeignManagement implements CRUDInterface<ForeignCustomer> {
    static ReadAndWrite<ForeignCustomer> readAndWrite = new ReadAndWrite<>();
    @Override
    public void add(ForeignCustomer foreignCustomer) {
        List<ForeignCustomer> list = new ArrayList<>();
        list.add(foreignCustomer);
        readAndWrite.writeToFile("customer.csv",list,true);

    }

    @Override
    public void addAll(List<ForeignCustomer> list) {

    }

    @Override
    public void show(List<ForeignCustomer> list) {

    }

    @Override
    public List<ForeignCustomer> findAll() {
        List<String[]> list = readAndWrite.readFromFile("customer.csv");
        List<ForeignCustomer> listForeign = new ArrayList<>();
        for(String[] line : list) {
            if (line[0].contains("KHNN")) {
                ForeignCustomer foreignCustomer = new ForeignCustomer(line);
                listForeign.add(foreignCustomer);
            }
        }
        return listForeign;
    }
}
