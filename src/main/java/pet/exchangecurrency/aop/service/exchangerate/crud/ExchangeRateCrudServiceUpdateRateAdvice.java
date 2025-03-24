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
public class ExchangeRateCrudServiceUpdateRateAdvice {

    @Before("execution(* pet.exchangecurrency.service.ExchangeCrudService.updateRate(..)) && args(rate)")
    public void beforeCreate(JoinPoint joinPoint, double rate) {
        log.info("\n{}\nМетод {}()\nПодготовка к обновлению плана обмена (Новый rate {})\n",
                joinPoint.getSignature().getDeclaringType().getSimpleName(),
                joinPoint.getSignature().getName(),
                rate);
    }

    @AfterReturning(value = "execution(* pet.exchangecurrency.service.ExchangeCrudService.updateRate(..))",
            returning = "result")
    public void afterCreateReturning(JoinPoint joinPoint, ExchangeRateDto result) {
        log.info("\n{}\nМетод {}()\nПлан обмена успешно обновлён {}\n",
                joinPoint.getSignature().getDeclaringType().getSimpleName(),
                joinPoint.getSignature().getName(),
                result);
    }

}
