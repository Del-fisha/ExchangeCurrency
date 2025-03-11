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
class CurrencyCrudServiceCreateAdvice {

    @Before("execution(* pet.exchangecurrency.service.CurrencyCrudService.create(..)) && args(dto)")
    public void beforeCreate(JoinPoint joinPoint, CurrencyDto dto) {
        log.info("\n{}\nМетод {}()\nПодготовка к созданию валюты: {}\n",
                joinPoint.getSignature().getDeclaringType().getSimpleName(),
                joinPoint.getSignature().getName(),
                dto);
    }

    @AfterReturning(value = "execution(* pet.exchangecurrency.service.CurrencyCrudService.create(..))",
            returning = "result")
    public void afterCreateReturning(JoinPoint joinPoint, CurrencyDto result) {
        log.info("\n{}\nМетод {}()\nВалюта успешно создана {}\n",
                joinPoint.getSignature().getDeclaringType().getSimpleName(),
                joinPoint.getSignature().getName(),
                result);
    }
}
