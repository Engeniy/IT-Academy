package ru.mail.krivonos.al.test;

import ru.mail.krivonos.al.test.model.Student;

import java.io.File;
import java.util.List;

public interface TaskSixService {

    void fillFileWithStudentsInfo(File file, int quantity);

    List<Student> getStudents(File file);

    void printStudents(List<Student> students);
}
