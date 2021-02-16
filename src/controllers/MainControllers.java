package controllers;

import commons.read_write.ReadAndWrite;
import commons.validation.ForeignIDValidation;
import commons.validation.RequiredValidation;
import commons.validation.VietnamIDValidation;
import models.Bill;
import models.Customer;
import models.ForeignCustomer;
import models.VietnamCustomer;
import services.BillManagement;
import services.ForeignManagement;
import services.VietnamManagement;

import java.util.List;
import java.util.Scanner;

public class MainControllers {
    static Scanner scanner = new Scanner(System.in);
    private static final int VIETNAM = 1;
    private static final int FOREIGN = 2;

    static ReadAndWrite<Customer> readAndWrite = new ReadAndWrite<>();
    static ReadAndWrite<Bill> readAndWriteBill = new ReadAndWrite<>();
    static VietnamManagement vietnamManagement = new VietnamManagement();
    static ForeignManagement foreignManagement = new ForeignManagement();
    static BillManagement billManagement = new BillManagement();

    private static void displayMainMenu() {
        while (true) {
            System.out.println( "MAIN MENU\n" +
                                "1. Add New Customer\n" +
                                "2. Show Information Customer\n" +
                                "3. Search Customer\n" +
                                "4. Add New Bill\n" +
                                "5. Edit Information Bill\n" +
                                "6. Show Information Bill\n" +
                                "0. Exit\n");
            int choiceMenu;
            while (true) {
                try {
                    System.out.print("Your choice is : ");
                    choiceMenu = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Wrong format");
                }
            }
            switch (choiceMenu) {
                case 1:
                    addNewCustomer();
                    break;
                case 2:
                    showInformationCustomer();
                    break;
                case 3:
                    searchCustomerByName();
                    break;
                case 4:
                    addNewBill();
                    break;
                case 5:
                    editBill();
                    break;
                case 6:
                    showBillDetail();
                    break;
                case 0:
                    System.exit(choiceMenu);
                    break;
                default:
                    System.out.println("Failed");
                    break;
            }
        }
    }

    private static void addNewCustomer() {
        boolean flagAdd = true;
        do {
            System.out.println( "1. Add New Vietnamese Customer\n" +
                                "2. Add New Foreign Customer\n" +
                                "3. Back\n" +
                                "4. EXit\n");
            int choiceAdd;
            while (true) {
                try {
                    System.out.print("Your choice is : ");
                    choiceAdd = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Wrong format");
                }
            }
            switch (choiceAdd) {
                case 1:
                    addCustomer(VIETNAM);
                    break;
                case 2:
                    addCustomer(FOREIGN);
                    break;
                case 3:
                    flagAdd = false;
                    break;
                case 4:
                    System.exit(choiceAdd);
                    break;
                default:
                    System.out.println("Failed");
                    break;
            }
        } while (flagAdd);
    }

    private static void addCustomer(int type) {

        String idCustomer;
        String nameCustomer;
        String typeCustomer;
        int consume;
        String nationality;

        do {
            System.out.print("Enter Name : ");
            nameCustomer = scanner.nextLine();
            if (!RequiredValidation.requiredValidate(nameCustomer)) {
                System.out.println("Input a Name");
            }
        } while (!RequiredValidation.requiredValidate(nameCustomer));

        switch (type) {
            case VIETNAM:
                do {
                    System.out.print("Enter ID (KHVN-XXXXX) : ");
                    idCustomer = scanner.nextLine();
                    if (!VietnamIDValidation.vietnamIDValidate(idCustomer)) {
                        System.out.println("Invalid ID");
                    }
                } while (!VietnamIDValidation.vietnamIDValidate(idCustomer));
                int choiceVN;
                List<String[]> listTypeCustomer = readAndWrite.readFromFile("type_customer.csv");
                int i = 1;
                for (String[] line : listTypeCustomer) {
                    System.out.println(i + ". " + line[1]);
                    i++;
                }
                do {
                    while (true) {
                        try {
                            System.out.print("Your choice is : ");
                            choiceVN = Integer.parseInt(scanner.nextLine());
                            break;
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Invalid Choice");
                        }
                    }
                } while (choiceVN < 1 || choiceVN > listTypeCustomer.size());
                typeCustomer = listTypeCustomer.get(choiceVN - 1)[1];
                while (true) {
                    try {
                        System.out.print("Enter Consume : ");
                        consume = Integer.parseInt(scanner.nextLine());
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Wrong format");
                    }
                }
                VietnamCustomer vietnamCustomer = new VietnamCustomer(idCustomer, nameCustomer, typeCustomer, consume);
                vietnamManagement.add(vietnamCustomer);
                System.out.println("Successfully Add New Vietnamese Customer");
                break;

            case FOREIGN:
                do {
                    System.out.print("Enter ID (KHNN-XXXXX) : ");
                    idCustomer = scanner.nextLine();
                    if (!ForeignIDValidation.foreignIDValidate(idCustomer)) {
                        System.out.println("Invalid ID");
                    }
                } while (!ForeignIDValidation.foreignIDValidate(idCustomer));

                do {
                    System.out.print("Enter Nationality : ");
                    nationality = scanner.nextLine();
                    if (!RequiredValidation.requiredValidate(nationality)) {
                        System.out.println("Input a Nationality");
                    }
                } while (!RequiredValidation.requiredValidate(nationality));

                ForeignCustomer foreignCustomer = new ForeignCustomer(idCustomer, nameCustomer, nationality);
                foreignManagement.add(foreignCustomer);
                System.out.println("Successfully Add New Foreign Customer");
                break;
        }
    }

    private static void showInformationCustomer() {
        List<String[]> list = readAndWrite.readFromFile("customer.csv");
        int i = 1;
        for (String[] line : list) {
            common(line, i);
            i++;
        }
    }

    private static void searchCustomerByName() {
        String nameSearch;
        System.out.print("Enter Name to Search : ");
        nameSearch = scanner.nextLine();
        List<String[]> list = readAndWrite.readFromFile("customer.csv");
        boolean flagSearch = true;
        int i = 1;
        for (String[] line : list) {
            if ((line[1].contains(nameSearch))) {
                common(line, i);
                i++;
                flagSearch = false;
            }
        }
        if (flagSearch) {
            System.out.println("No result for " + nameSearch + " !\n");
        }
    }

    private static void common(String[] line, int i) {
        if (line[0].contains("KHVN")) {
            VietnamCustomer vietnamCustomer = new VietnamCustomer(line);
            System.out.println(i + ". " + vietnamCustomer.showInfor());
        } else {
            ForeignCustomer foreignCustomer = new ForeignCustomer(line);
            System.out.println(i + ". " + foreignCustomer.showInfor());
        }
    }

    private static void addNewBill() {
        List<String[]> listCustomer = readAndWrite.readFromFile("customer.csv");
        String idBill;
        String idCus;
        String dateBill;
        int consumption;
        int unitPrice;
        int total;

        if (!listCustomer.isEmpty()) {
            idBill = "MHD-".concat(idRaising());
            showInformationCustomer();
            int choiceAddBill;
            while (true) {
                try {
                    System.out.print("Choose a Customer : ");
                    choiceAddBill = Integer.parseInt(scanner.nextLine());
                    idCus = listCustomer.get(choiceAddBill - 1)[0];
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Wrong format");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid Choice");
                }
            }

            do {
                System.out.print("Enter day of Bill (dd/MM/yyyy) : ");
                dateBill = scanner.nextLine();
                if (!RequiredValidation.requiredValidate(dateBill)) {
                    System.out.println("Input a Day of Birth");
                }
            } while (!RequiredValidation.requiredValidate(dateBill));
            do {
                System.out.print("Enter Consumption : ");
                consumption = Integer.parseInt(scanner.nextLine());
                if (!RequiredValidation.requiredValidate(Integer.toString(consumption))) {
                    System.out.println("Input a Consumption");
                }
            } while (!RequiredValidation.requiredValidate(Integer.toString(consumption)));

            do {
                System.out.print("Enter Unit Price : ");
                unitPrice = Integer.parseInt(scanner.nextLine());
                if (!RequiredValidation.requiredValidate(Integer.toString(unitPrice))) {
                    System.out.println("Input a Unit Price");
                }
            } while (!RequiredValidation.requiredValidate(Integer.toString(unitPrice)));


            int consumeVN = Integer.parseInt(listCustomer.get(choiceAddBill - 1)[3]);

            if (listCustomer.get(choiceAddBill - 1)[0].contains("KHVN")) {
                if (consumption <= consumeVN) {
                    total = consumption * unitPrice;
                } else {
                    total = consumption * unitPrice * consumeVN + (consumption - consumeVN) * unitPrice * 5 / 2;
                }
            } else {
                total = consumption * unitPrice;
            }
            Bill bill = new Bill(idBill, idCus, dateBill, consumption, unitPrice, total);
            billManagement.add(bill);
        } else {
            System.out.println("No Customer to choose! Add some customer first!");
        }

    }

    private static String idRaising() {
        String result = "";
        List<Bill> listBill = billManagement.findAll();
        if (listBill.isEmpty()) {
            return "001";
        }
        int id = Integer.parseInt(listBill.get(listBill.size() - 1).getIdBill().substring(4, 7)) + 1; // id tang dan
        if (id < 10) {
            result = "00" + id;
        } else if (id < 100) {
            result = "0" + id;
        }
        return result;
    }

    private static void editBill() {
        List<Bill> listBill = billManagement.findAll();
        List<String[]> listCustomer = readAndWrite.readFromFile("customer.csv");

        int choiceBill;
        show();

        while (true) {
            try {
                System.out.print("Choose a Bill to edit ID Customer : ");
                choiceBill = Integer.parseInt(scanner.nextLine());
                if (choiceBill < 1 || choiceBill > listBill.size()) {
                    throw new ArrayIndexOutOfBoundsException("Invalid Choice");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Wrong format");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        }
        String IDCusChose = listBill.get(choiceBill - 1).getIdCus();
        System.out.println("LIST ");
        int i = 1;
        for (String[] line : listCustomer) {
            if (IDCusChose.equals(line[0])) {
                continue;
            }
            System.out.println(i + ". " + line[0]);
            i++;
        }
        int choiceEdit;

        while (true) {
            try {
                System.out.print("Choose a ID Customer : ");
                choiceEdit = Integer.parseInt(scanner.nextLine());
                if (choiceEdit < 1 || choiceEdit > listCustomer.size()) {
                    throw new ArrayIndexOutOfBoundsException("Invalid Choice");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Wrong format");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        }
        listBill.get(choiceBill - 1).setIdCus(listCustomer.get(choiceEdit - 1)[0]);
        readAndWriteBill.writeToFile("bill.csv", listBill, false);

        System.out.println(listBill.get(choiceBill - 1).showInfor());

    }

    private static void show() { // show chua xong
        List<Bill> listBill = billManagement.findAll();
        int i = 1;
        for (Bill bill : listBill) {
            System.out.println(i + ". " + bill.showInfor());
            i++;
        }
    }

    private static void showBillDetail() {
        show();
        List<Bill> listBill = billManagement.findAll();
        List<String[]> listCustomer = readAndWrite.readFromFile("customer.csv");

        int choiceBill;

        while (true) {
            try {
                System.out.print("Choose a Bill to show Detail : ");
                choiceBill = Integer.parseInt(scanner.nextLine());
                if (choiceBill < 1 || choiceBill > listBill.size()) {
                    throw new ArrayIndexOutOfBoundsException("Invalid Choice");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Wrong format");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        }

        Bill billToShow = listBill.get(choiceBill - 1);
        String idCusShow = billToShow.getIdCus();
        String nameCusShow = "";
        for (String[] line : listCustomer) {
            if (line[0].equals(idCusShow)) {
                nameCusShow = line[1];
                break;
            }
        }
        System.out.println( "\tBILL DETAIL\t\n"+
                            (choiceBill) + ". BILL\n" +
                            "ID Bill : " + billToShow.getIdBill() + "\n" +
                            "Name Customer : " + nameCusShow + "\n" +
                            "Date Of Bill : " + billToShow.getDateBill() + "\n" +
                            "Consumption : " + billToShow.getConsumption() + "\n" +
                            "Unit Price : " + billToShow.getUnitPrice() + "\n" +
                            "Total : " + billToShow.getTotal() + "\n");

    }

    public static void main(String[] args) {
        displayMainMenu();
    }
}
