package ru.mail.krivonos.al.lesson.thirteen;

import java.io.File;
import java.io.IOException;

public interface FileService {

    void printFileMinInteger(File targetFile);

    void writeIntegerArray(File targetFile, int[] targetArray);

    void rewriteWithoutWordsFromLengthScope(File input, File output, int lengthScopeStart, int lengthScopeEnd);

    void validateFile(File file);
}
