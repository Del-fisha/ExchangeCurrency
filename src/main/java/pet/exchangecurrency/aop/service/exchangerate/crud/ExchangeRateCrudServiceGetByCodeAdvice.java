package pet.exchangecurrency.aop.service.exchangerate.crud;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import pet.exchangecurrency.dto.ExchangeRateDto;

@Aspect
@Slf4j
@Component
public class ExchangeRateCrudServiceGetByCodeAdvice {

    @Before("execution(* pet.exchangecurrency.service.ExchangeCrudService.getByCode(..))")
    public void beforeGetById(JoinPoint joinPoint) {
        log.info("\n{}\nМетод {}()\nПодготовка к получению валюты по code {}\n",
                joinPoint.getSignature().getDeclaringType().getSimpleName(),
                joinPoint.getSignature().getName(),
                joinPoint.getArgs());
    }

    @AfterReturning(value = "execution(* pet.exchangecurrency.service.ExchangeCrudService.getByCode(..))",
            returning = "result")
    public void afterGetById(JoinPoint joinPoint, ExchangeRateDto result) {
        log.info("\n{}\nМетод {}()\nПолучена обменная пара по code {} {}\n{}\n",
                joinPoint.getSignature().getDeclaringType().getSimpleName(),
                joinPoint.getSignature().getName(),
                result.getBaseCurrency(),
                result.getTargetCurrency(),
                result);
    }
}
