package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;

public class Sessions {

    private final List<Session> sessionList;

    public Sessions() {
        this(new ArrayList<>());
    }

    public Sessions(List<Session> sessionList) {
        this.sessionList = sessionList;
    }

    public List<Session> asList() {
        return sessionList;
    }

}
