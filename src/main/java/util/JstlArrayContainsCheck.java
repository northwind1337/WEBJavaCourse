package util;

import java.util.List;


public class JstlArrayContainsCheck {
    public static boolean contains(List list, Object o) {
        if (list != null) {
            return list.contains(o);
        }
        return false;
    }
}
