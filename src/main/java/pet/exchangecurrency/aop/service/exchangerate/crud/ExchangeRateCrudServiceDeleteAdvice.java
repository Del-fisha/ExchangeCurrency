package pet.exchangecurrency.aop.service.exchangerate.crud;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import pet.exchangecurrency.dto.CurrencyDto;

@Aspect
@Slf4j
@Component
public class ExchangeRateCrudServiceDeleteAdvice {

    @Before("execution(* pet.exchangecurrency.service.ExchangeCrudService.deleteById(..))")
    public void beforeDeleteById(JoinPoint joinPoint) {
        log.info("\n{}\nМетод {}()\nПодготовка к удалению обменного плана по ID {}\n",
                joinPoint.getSignature().getDeclaringType().getSimpleName(),
                joinPoint.getSignature().getName(),
                joinPoint.getArgs());
    }

    @AfterReturning(value = "execution(* pet.exchangecurrency.service.ExchangeCrudService.deleteById(..))",
            returning = "result")
    public void afterDeleteById(JoinPoint joinPoint, CurrencyDto result) {
        log.info("\n{}\nМетод {}()\nУдалён обменный план по ID {}\n{}\n",
                joinPoint.getSignature().getDeclaringType().getSimpleName(),
                joinPoint.getSignature().getName(),
                result.getId(),
                result);
    }
}
