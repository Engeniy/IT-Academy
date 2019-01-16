package ru.mail.krivonos.al.lesson.twelve;

import ru.mail.krivonos.al.lesson.twelve.exceptions.DateFormatException;
import ru.mail.krivonos.al.lesson.twelve.exceptions.IllegalInvocationException;
import ru.mail.krivonos.al.lesson.twelve.exceptions.IllegalParameterTypeException;

public interface ExceptionService {

    void generateNullPointerException();

    void chooseThrowingException(int index) throws IllegalInvocationException, IllegalParameterTypeException, DateFormatException;
}
