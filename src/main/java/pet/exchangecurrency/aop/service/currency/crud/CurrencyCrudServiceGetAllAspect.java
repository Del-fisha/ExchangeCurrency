package pet.exchangecurrency.aop.service.currency.crud;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import pet.exchangecurrency.dto.CurrencyDto;

import java.util.Collection;

@Aspect
@Component
@Slf4j
public class CurrencyCrudServiceGetAllAspect {

    @Before("execution(* pet.exchangecurrency.service.CurrencyCrudService.getAll(..))")
    public void beforeGetAll(JoinPoint joinPoint) {
        log.info("\n{}\nМетод {}()\nПодготовка к получению всех валют\n",
                joinPoint.getSignature().getDeclaringType().getSimpleName(),
                joinPoint.getSignature().getName());
    }

    @AfterReturning(value = "execution(* pet.exchangecurrency.service.CurrencyCrudService.getAll(..))",
            returning = "result")
    public void afterGetAll(JoinPoint joinPoint, Collection<CurrencyDto> result) {
        log.info("\n{}\nМетод {}()\nПолучен список валют. Всего {} валют\n",
                joinPoint.getSignature().getDeclaringType().getSimpleName(),
                joinPoint.getSignature().getName(),
                result.size());
    }
}
