package ru.mail.krivonos.al.additional.thirteen;

import java.io.File;
import java.util.List;

public interface FileService {

    List<String> readFile(File file);

    void writeWithYears(File file, List<String> content, int[] years);
}
