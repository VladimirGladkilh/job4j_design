package tdd;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class Cinema3D implements Cinema {
    List<Session> sessions = new LinkedList<>();
    @Override
    public List<Session> find(Predicate<Session> filter) {
        return sessions;
    }

    @Override
    public Ticket buy(Account account, int row, int column, Calendar date) {
        if (row > 1) {
            return null;
        }
        if (column > 100) {
            throw new IllegalArgumentException();
        }
        return new Ticket3D();
    }

    @Override
    public void add(Session session) {
        sessions.add(session);
    }
}
