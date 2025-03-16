package git.MatheusOliveira04.utils;

import java.util.Collection;

public class ListUtils {

    public static <T> boolean isNullOrEmpty(Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }

}
