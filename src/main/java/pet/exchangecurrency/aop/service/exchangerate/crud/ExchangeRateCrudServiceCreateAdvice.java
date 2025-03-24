package pet.exchangecurrency.aop.service.exchangerate.crud;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import pet.exchangecurrency.dto.CurrencyDto;
import pet.exchangecurrency.dto.ExchangeRateDto;

@Aspect
@Component
@Slf4j
public class ExchangeRateCrudServiceCreateAdvice {

    @Before("execution(* pet.exchangecurrency.service.ExchangeCrudService.create(..)) && args(dto)")
    public void beforeCreate(JoinPoint joinPoint, ExchangeRateDto dto) {
        log.info("\n{}\nМетод {}()\nПодготовка к созданию нового плана обмена: \n{} на \n{}\n",
                joinPoint.getSignature().getDeclaringType().getSimpleName(),
                joinPoint.getSignature().getName(),
                dto.getBaseCurrencyCode(),
                dto.getTargetCurrencyCode());
    }

    @AfterReturning(value = "execution(* pet.exchangecurrency.service.CurrencyCrudService.create(..))",
            returning = "result")
    public void afterCreateReturning(JoinPoint joinPoint, ExchangeRateDto result) {
        log.info("\n{}\nМетод {}()\nПлан обмена успешно создан {}\n",
                joinPoint.getSignature().getDeclaringType().getSimpleName(),
                joinPoint.getSignature().getName(),
                result);
    }
}
