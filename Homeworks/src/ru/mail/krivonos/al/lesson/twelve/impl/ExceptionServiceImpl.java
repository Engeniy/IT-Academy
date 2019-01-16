package ru.mail.krivonos.al.lesson.twelve.impl;

import ru.mail.krivonos.al.lesson.twelve.ExceptionService;
import ru.mail.krivonos.al.lesson.twelve.exceptions.DateFormatException;
import ru.mail.krivonos.al.lesson.twelve.exceptions.IllegalInvocationException;
import ru.mail.krivonos.al.lesson.twelve.exceptions.IllegalParameterTypeException;

import java.util.List;

public class ExceptionServiceImpl implements ExceptionService {
    @Override
    public void generateNullPointerException() {
        List<Integer> list = null;
        list.get(2);
    }

    @Override
    public void chooseThrowingException(int index) throws IllegalInvocationException, IllegalParameterTypeException, DateFormatException {
        switch (index) {
            case 1:
                throw new IllegalInvocationException("Method invocation can't been processed correctly");
            case 2:
                throw new IllegalParameterTypeException("Your parameter has illegal type");
            case 3:
                throw new DateFormatException("Invalid date format! Please input date again in correct form");
        }
    }
}
