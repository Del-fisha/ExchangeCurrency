package pet.exchangecurrency.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link pet.exchangecurrency.model.Currency}
 */
//@Value
@NoArgsConstructor
@Getter
@Setter
public class CurrencyDto implements Serializable {
    Long id;
    String code;
    String fullName;
    String sign;

    public CurrencyDto(String code, String fullName, String sign) {
        this.code = code;
        this.fullName = fullName;
        this.sign = sign;
    }

    public CurrencyDto(Long entityId, String code, String fullName, String sign) {
        this(code, fullName, sign);
        this.id = entityId;
    }

    @Override
    public String toString() {
        return String.format("id=%d, code=%s, fullName=%s, sign=%s", id, code, fullName, sign);
    }
}