package services;

import commons.read_write.ReadAndWrite;
import models.Bill;

import java.util.ArrayList;
import java.util.List;

public class BillManagement implements CRUDInterface<Bill>{
    static ReadAndWrite<Bill> readAndWrite = new ReadAndWrite<>();

    @Override
    public void add(Bill bill) {
        List<Bill> list = new ArrayList<>();
        list.add(bill);
        readAndWrite.writeToFile("bill.csv",list,true);
    }

    @Override
    public void addAll(List<Bill> list) {
        readAndWrite.writeToFile("bill.csv",list,false);
    }

    @Override
    public void show(List<Bill> list) {

    }

    @Override
    public List<Bill> findAll() {
        List<String[]> list = readAndWrite.readFromFile("bill.csv");
        List<Bill> listBill = new ArrayList<>();
        for(String[] line : list) {
            Bill bill = new Bill(line);
            listBill.add(bill);
        }
        return listBill;
    }
}
