package soda;

import java.util.List;

public interface DatabaseEngine {
    void set(Object o);

    List<Object> get(Object id);
}
