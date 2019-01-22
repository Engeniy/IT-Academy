package ru.mail.krivonos.al.test.impl;

import ru.mail.krivonos.al.test.TaskSixService;
import ru.mail.krivonos.al.test.model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TaskSixServiceImpl implements TaskSixService {

    private Random random;

    public TaskSixServiceImpl() {
        random = new Random();
    }

    @Override
    public void fillFileWithStudentsInfo(File file, int quantity) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write("number name course group");
            bw.newLine();
            for (int i = 0; i < quantity; i++) {
                bw.write((i + 1) + " " + "Name" + i + " " + (random.nextInt(5) + 1) + " " + random.nextInt(200));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // TODO add validation
    @Override
    public List<Student> getStudents(File file) {
        List<Student> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            int numberPosition = 0;
            int namePosition = 0;
            int groupPosition = 0;
            int coursePosition = 0;
            String[] annotation = br.readLine().split(" ");
            for (int i = 0; i < annotation.length; i++) {
                if (annotation[i].equals("number")) {
                    numberPosition = i;
                }
                if (annotation[i].equals("name")) {
                    namePosition = i;
                }
                if (annotation[i].equals("course")) {
                    coursePosition = i;
                }
                if (annotation[i].equals("group")) {
                    groupPosition = i;
                }
            }
            String input = br.readLine();
            String[] data;
            while (input != null) {
                data = input.split(" ");
                students.add(new Student(Integer.parseInt(data[numberPosition]), data[namePosition],
                        Integer.parseInt(data[coursePosition]), Integer.parseInt(data[groupPosition])));
                input = br.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return students;
    }

    @Override
    public void printStudents(List<Student> students) {
        for (Student student : students) {
            System.out.println(student);
        }
    }
}
