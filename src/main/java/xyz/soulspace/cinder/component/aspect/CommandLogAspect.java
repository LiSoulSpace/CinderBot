package xyz.soulspace.cinder.component.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import xyz.soulspace.cinder.component.aspect.WebLogAspect;

@Aspect
@Component
@Order(1)
public class CommandLogAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebLogAspect.class);

    @Pointcut("execution(public * xyz.soulspace.cinder.command.everywhere.*.execute(..))")
    public void CommandLog() {
    }

    @Before("CommandLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
    }

    @AfterReturning(value = "CommandLog()", returning = "ret")
    public void doAfterReturning(Object ret) throws Throwable {
    }
}
