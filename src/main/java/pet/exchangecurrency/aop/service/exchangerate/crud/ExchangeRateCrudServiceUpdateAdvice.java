package pet.exchangecurrency.aop.service.exchangerate.crud;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import pet.exchangecurrency.dto.ExchangeRateDto;

@Aspect
@Component
@Slf4j
public class ExchangeRateCrudServiceUpdateAdvice {

    @Before("execution(* pet.exchangecurrency.service.ExchangeCrudService.update(..)) && args(dto)")
    public void beforeCreate(JoinPoint joinPoint, ExchangeRateDto dto) {
        log.info("\n{}\nМетод {}()\nПодготовка к обновлению плана обмена ({} на {})\n",
                joinPoint.getSignature().getDeclaringType().getSimpleName(),
                joinPoint.getSignature().getName(),
                dto.getBaseCurrency(),
                dto.getTargetCurrency());
    }

    @AfterReturning(value = "execution(* pet.exchangecurrency.service.ExchangeCrudService.update(..))",
            returning = "result")
    public void afterCreateReturning(JoinPoint joinPoint, ExchangeRateDto result) {
        log.info("\n{}\nМетод {}()\nПлан обмена успешно обновлён {}\n",
                joinPoint.getSignature().getDeclaringType().getSimpleName(),
                joinPoint.getSignature().getName(),
                result);
    }
}
