package org.goafabric.calleeservice.logic;

import lombok.SneakyThrows;
import org.goafabric.calleeservice.crossfunctional.DurationLog;

import javax.enterprise.context.ApplicationScoped;

@DurationLog
@ApplicationScoped
public class CalleeLogic {
    private Long sleepTime = 0l;

    @SneakyThrows
    public Boolean isAlive() {
        Thread.sleep(sleepTime);
        return Boolean.TRUE;
    }

    public String setSleepTime(Long sleepTime) {
        this.sleepTime = sleepTime;
        return "set sleepTime to: " + sleepTime;
    }

    public String sayMyName(String name) {
        return "Your name is: " + name;
    }

    public String sayMyOtherName(String name) {
        return "Your name is: " + name;
    }
}
