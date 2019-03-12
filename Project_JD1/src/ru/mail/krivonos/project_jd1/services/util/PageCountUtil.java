package ru.mail.krivonos.project_jd1.services.util;

public class PageCountUtil {

    public static final int PAGE_LIMIT = 9;

    private PageCountUtil() {
    }

    public static int countPages(int linesNumber) {
        int pagesNumber = linesNumber / PAGE_LIMIT;
        if (linesNumber % PAGE_LIMIT > 0) {
            pagesNumber += 1;
        }
        return pagesNumber;
    }
}
