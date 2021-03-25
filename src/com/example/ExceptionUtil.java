package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExceptionUtil {

    public static Throwable merge(Throwable e, StackTraceElement[] stackTrace) {
        for (Throwable throwable : getCauseHierarchy(e))
            appendStackTrace(throwable, stackTrace);
        return e;
    }

    private static void appendStackTrace(Throwable t, StackTraceElement[] elements) {
        List<StackTraceElement> causeHierarchy = new ArrayList<>();
        addArrayElementsToList(causeHierarchy, t.getStackTrace());
        addArrayElementsToList(causeHierarchy, elements);
        t.setStackTrace(causeHierarchy.toArray(new StackTraceElement[0]));
    }

    private static <T> void addArrayElementsToList(List<T> list, T... elements) {
        //Collections.addAll(list, elements);
        for(T object : elements) {
            list.add(object);
        }
    }

    private static List<Throwable> getCauseHierarchy(Throwable t) {
        List<Throwable> result = new ArrayList<>();
        while (t != null) {
            result.add(t);
            t = t.getCause();
        }
        return result;
    }

}
