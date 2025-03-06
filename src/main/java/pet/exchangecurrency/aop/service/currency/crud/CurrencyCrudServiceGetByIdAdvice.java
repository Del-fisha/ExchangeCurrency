package pet.exchangecurrency.aop.service.currency.crud;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import pet.exchangecurrency.dto.CurrencyDto;

@Aspect
@Slf4j
@Component
public class CurrencyCrudServiceGetByIdAdvice {

    @Before("execution(* pet.exchangecurrency.service.CurrencyCrudService.getById(..))")
    public void beforeGetById(JoinPoint joinPoint) {
        log.info("\n{}\nМетод {}()\nПодготовка к получению валюты по ID {}\n",
                joinPoint.getSignature().getDeclaringType().getSimpleName(),
                joinPoint.getSignature().getName(),
                joinPoint.getArgs());
    }

    @AfterReturning(value = "execution(* pet.exchangecurrency.service.CurrencyCrudService.getById(..))",
            returning = "result")
    public void afterGetById(JoinPoint joinPoint, CurrencyDto result) {
        log.info("\n{}\nМетод {}()\nПолучена валюта по ID {}\n{}\n",
                joinPoint.getSignature().getDeclaringType().getSimpleName(),
                joinPoint.getSignature().getName(),
                result.getId(),
                result);
    }
}
