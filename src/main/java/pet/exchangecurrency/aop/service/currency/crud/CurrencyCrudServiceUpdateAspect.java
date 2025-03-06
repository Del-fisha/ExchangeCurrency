package pet.exchangecurrency.aop.service.currency.crud;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import pet.exchangecurrency.dto.CurrencyDto;

@Aspect
@Component
@Slf4j
public class CurrencyCrudServiceUpdateAspect {

    @Before("execution(* pet.exchangecurrency.service.CurrencyCrudService.update(..)) && args(dto)")
    public void beforeUpdate(JoinPoint joinPoint, CurrencyDto dto) {
        log.info("\n{}\nМетод {}()\nПодготовка к изменению валюты с ID {}\n",
                joinPoint.getSignature().getDeclaringType().getSimpleName(),
                joinPoint.getSignature().getName(),
                dto.getId());
    }

    @AfterReturning(value = "execution(* pet.exchangecurrency.service.CurrencyCrudService.update(..))",
            returning = "result")
    public void afterUpdateReturning(JoinPoint joinPoint, CurrencyDto result) {
        log.info("\n{}\nМетод {}()\nВалюта успешно изменена на {}\n",
                joinPoint.getSignature().getDeclaringType().getSimpleName(),
                joinPoint.getSignature().getName(),
                result);
    }
}
