package DAO;

import DTO.Student;
import DTO.Valid_Input;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Student_List extends ArrayList<Student> {

    ArrayList<Student> list = new ArrayList<Student>();

    public Student_List() {
    }

    public Student_List(ArrayList<Student> list) {
        this.list = list;
    }

    Scanner sc = new Scanner(System.in);

    String studentData = "student.txt";

    public void writeToFile(){
        try {
            String fName = "student.txt";
            FileOutputStream fs = new FileOutputStream(fName);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fs));
            for (Student st : list){
                bw.write(st.toString());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void readFromFile(){
        try (BufferedReader br = new BufferedReader(new FileReader("student.txt"))) {
            String id, name;
            int sex = 0;
            LocalDate birthdate;
            int semester;
            String line = br.readLine();
            while (line != null){
                String arr[] = line.split(", ");
                String a0[] = arr[0].split("'");
                id = a0[1];
                String a1[] = arr[1].split("'");
                name = a1[1];
                String a2[] = arr[2].split("'");
                if (a2[1].equals("male")){
                    sex = 1;
                } else if (a2[1].equals("female")){
                    sex = 2;
                } else sex = 3;
                String a3[] = arr[3].split("'");
                birthdate = LocalDate.parse(a3[1]);
                String a4[] = arr[4].split("'");
                semester = Integer.parseInt(a4[1]);
                Student student = new Student(id, name, sex, birthdate, semester);
                list.add(student);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } ;
    }

    public int findStudentByID(String ID) {
        ID = ID.trim().toUpperCase();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getStudentID().equals(ID))
                return i;
        }
        return -1;
    }

    private boolean isIDDuplicated(String ID) {
        ID = ID.trim().toUpperCase();
        return findStudentByID(ID) != -1;
    }

    public void createStudent(){
        String nID, nName;
        int nSex, nSemester;
        LocalDate nBirthdate;
        do {
            nID = Valid_Input.inputPattern("===-> Enter new student ID: <-===" +
                    "\nFollow the pattern: SE130001, HA150001,...","([C|D|H|Q|S])([A|E|S])(1[3-8])\\d{4}");
            if (isIDDuplicated(nID)){
                System.out.println("This ID has already exist! Please enter another ID!");
            }
        }while (isIDDuplicated(nID));
        nName = Valid_Input.inputNonBlankString("===-> Enter new student Name: <-===");
        nSex = Valid_Input.inputInt("===-> Enter student sex: <-===\n1: Male\n2: Female\n3: Other");
        nBirthdate = Valid_Input.inputDate("===-> Enter the student birthdate: <-===" +
                                                            "\n[dd-mm-yyyy]");
        nSemester = Integer.parseInt(Valid_Input.inputPattern("===-> Enter the semester that " + nName + " is studying: <-===", "[1-9]"));
        Student st = new Student(nID, nName, nSex, nBirthdate, nSemester);
        list.add(st);
        writeToFile();
        System.out.println("The student has been created!");
    }

    public void findStudentByName(){
        String fName = Valid_Input.inputNonBlankString("Enter the name of student you want to find: ");
        Collections.sort(list);
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().toUpperCase().contains(fName.toUpperCase())){
                System.out.println(list.get(i).toString());
                count++;
            }
        }
        if (count == 0){
            System.out.println("The name does not exist!");
        }
    }


    public void updateStudent(){
        String uID, uName;
        int uSex;
        LocalDate uBirthdate;
        int uSemester;
        do {
            uID = Valid_Input.inputPattern("===-> Enter student ID you want to update: <-===" +
                    "\nFollow the pattern: SE130001, HA150001,...","([C|D|H|Q|S])([A|E|S])(1[3-8])\\d{4}");
            if (!isIDDuplicated(uID)){
                System.out.println("The student ID does not exist! Please try again!");
            }
        } while (!isIDDuplicated(uID));
        for (int i = 0; i < list.size(); i++){
            if (list.get(i).getStudentID().equals(uID)){
                uName = Valid_Input.inputNonBlankString("===-> Enter the update student name: <-===");
                uSex = Valid_Input.inputInt("===-> Enter the update student sex: <-===" +
                        "\n1: Male\n2: Female\n3: Other");
                uBirthdate = Valid_Input.inputDate("===-> Enter the update student birthdate: <-===" +
                        "\n[dd-mm-yyyy]");
                uSemester = Valid_Input.inputInt("===-> Enter the update student semester: <-===");
                list.get(i).setStudentID(uID);
                list.get(i).setName(uName);
                list.get(i).setSex(uSex);
                list.get(i).setBirthdate(uBirthdate);
                list.get(i).setSemester(uSemester);
                System.out.println("The student " + uName + " has been updated!");
            }
        }
    }

    public void deleteStudent(){
        String uID;
        do {
            uID = Valid_Input.inputPattern("===-> Enter student ID you want to delete: <-===" +
                    "\nFollow the pattern: SE130001, HA150001,...","([C|D|H|Q|S])([A|E|S])(1[3-8])\\d{4}");
            if (!isIDDuplicated(uID)){
                System.out.println("The student ID does not exist! Please try again!");
            }
        } while (!isIDDuplicated(uID));
        int pos = findStudentByID(uID);
        list.remove(pos);
    }

    public void showReport(){
        for (int i = 0; i < list.size(); i++){
            System.out.println(list.get(i).toString());;
        }
    }
}
