package pet.exchangecurrency.aop.controller.currency.crud;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class CurrencyControllerAspect {

    @Pointcut("execution(* pet.exchangecurrency.controller.CurrencyController.*(..))")
    public void createCurrencyMethod() {
    }

    @Before("createCurrencyMethod()")
    public void logBeforeCreateCurrency(JoinPoint joinPoint) {
        log.info("\n{}\nМетод {}()\nОбращение в контроллер\n",
                joinPoint.getSignature().getDeclaringType().getSimpleName(),
                joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "createCurrencyMethod()", returning = "result")
    public void logAfterReturningCreateCurrency(JoinPoint joinPoint, Object result) {
        log.info("\n{}\nМетод {}()\nМетод выполнен.\nРезультат: {}\n",
                joinPoint.getSignature().getDeclaringType().getSimpleName(),
                joinPoint.getSignature().getName(),
                result);
    }

    @AfterThrowing(pointcut = "createCurrencyMethod()", throwing = "exception")
    public void logAfterThrowingCreateCurrency(JoinPoint joinPoint, Throwable exception) {
        log.error("\nCONTROLLER:\nИсключение в методе {}: {}\n", joinPoint.getSignature(), exception.getMessage());
    }

    @Around("createCurrencyMethod()")
    public Object logAroundCreateCurrency(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - startTime;
        log.info("({} - Метод {}() - Время выполнения: {} ms)",
                joinPoint.getSignature().getDeclaringType().getSimpleName(),
                joinPoint.getSignature().getName(),
                executionTime);
        return result;
    }
}
