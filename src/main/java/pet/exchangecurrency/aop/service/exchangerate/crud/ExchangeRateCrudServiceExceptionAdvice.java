package pet.exchangecurrency.aop.service.exchangerate.crud;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class ExchangeRateCrudServiceExceptionAdvice {

    @AfterThrowing(value = "execution(* pet.exchangecurrency.service.ExchangeCrudService.*(..))",
            throwing = "exception")
    public void afterCreateThrowing(JoinPoint joinPoint, Throwable exception) {
        log.error("\nИсключение {}\nКласс: {}\nМетод: {}()\n",
                exception.getMessage(),
                joinPoint.getSignature().getDeclaringType().getSimpleName(),
                joinPoint.getSignature().getName());
    }
}
