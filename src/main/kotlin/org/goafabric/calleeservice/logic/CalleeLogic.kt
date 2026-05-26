package org.goafabric.calleeservice.logic

import jakarta.enterprise.context.ApplicationScoped
import org.goafabric.calleeservice.controller.dto.Callee

@ApplicationScoped
class CalleeLogic {
    fun sayMyName(name: String?): Callee {
        return Callee("0", "Your name is: $name")
    }

    fun sayMyOtherName(name: String?): Callee {
        return Callee("0", "Your other name is: $name")
    }

    fun save(callee: Callee): Callee {
        return Callee("0", "Storing your message: " + callee.message)
    }
}