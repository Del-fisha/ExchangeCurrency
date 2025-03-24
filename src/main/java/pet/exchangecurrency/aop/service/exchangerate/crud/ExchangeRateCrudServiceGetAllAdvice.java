package pet.exchangecurrency.aop.service.exchangerate.crud;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import pet.exchangecurrency.dto.ExchangeRateDto;

import java.util.Collection;

@Aspect
@Component
@Slf4j
public class ExchangeRateCrudServiceGetAllAdvice {

    @Before("execution(* pet.exchangecurrency.service.ExchangeCrudService.getAll(..))")
    public void beforeGetAll(JoinPoint joinPoint) {
        log.info("\n{}\nМетод {}()\nПодготовка к получению всех обменных пар\n",
                joinPoint.getSignature().getDeclaringType().getSimpleName(),
                joinPoint.getSignature().getName());
    }

    @AfterReturning(value = "execution(* pet.exchangecurrency.service.ExchangeCrudService.getAll(..))",
            returning = "result")
    public void afterGetAll(JoinPoint joinPoint, Collection<ExchangeRateDto> result) {
        log.info("\n{}\nМетод {}()\nПолучен список обменных пар. Всего {} обменных пар\n",
                joinPoint.getSignature().getDeclaringType().getSimpleName(),
                joinPoint.getSignature().getName(),
                result.size());
    }
}
