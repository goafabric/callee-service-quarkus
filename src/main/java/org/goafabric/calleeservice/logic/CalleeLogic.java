package org.goafabric.calleeservice.logic;

import jakarta.enterprise.context.ApplicationScoped;
import org.goafabric.calleeservice.controller.Callee;

@ApplicationScoped
public class CalleeLogic {
    public Callee sayMyName(String name) {
        return new Callee("0", "Your name is: " + name);
    }

    public Callee sayMyOtherName(String name) {
        return new Callee("0", "Your other name is: " + name);
    }

    public Callee save(Callee callee) {
        return new Callee("0", "Storing your message: " + callee.message());
    }
}