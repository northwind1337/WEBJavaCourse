package util;

import java.util.List;


public class UserListContainsCheck {
    public static boolean contains(List list, Object o) {
        if (list != null) {
            return list.contains(o);
        }
        return false;
    }
}
