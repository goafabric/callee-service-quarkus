package org.goafabric.calleeservice.logic;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CalleeLogic {
    public Boolean isAlive() {
        return Boolean.TRUE;
    }


    public String sayMyName(String name) {
        return "Your name is: " + name;
    }

    public String sayMyOtherName(String name) {
        return "Your name is: " + name;
    }
}
