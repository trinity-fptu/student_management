package Main;

import DAO.Menu;
import DAO.Student_List;

public class Main {
    public static void main(String[] args) {
        Student_List list = new Student_List();
        list.readFromFile();
        String[] opt = {"Create new student", "Find and sort the list of student", "Update or Delete", "Report", "Exit"};
        int choice = 0;
        System.out.println("Welcome to Student Management - @ 2022 by SE150263 - Pham Trong Tai");
        do {
            System.out.println("Select the option below: ");
            choice = Menu.getChoice(opt);
            int c = 0;
            String[] ynOpt = {"Yes","No"};
            switch (choice){
                case 1:
                    do {
                        list.createStudent();
                        System.out.println("Do you want to continue?");
                        c = Menu.getChoice(ynOpt);
                    } while (c<2);
                    break;
                case 2:
                    do {
                        list.findStudentByName();
                        System.out.println("Do you want to continue?");
                        c = Menu.getChoice(ynOpt);
                    } while (c<2);
                    break;
                case 3:
                    do {
                        String[] case2Opt = {"Update", "Delete"};
                        c = Menu.getChoice(case2Opt);
                        switch (c){
                            case 1:
                                list.updateStudent();
                                break;
                            case 2:
                                list.deleteStudent();
                                break;
                        }
                        System.out.println("Do you want to continue?");
                        c = Menu.getChoice(ynOpt);
                    } while (c<2);
                case 4:
                    list.showReport();
                    break;
                default:
                    System.out.println("Goodbye! Thanks for using!");
            }
        } while (choice > 0 && choice < 5);
    }
}
