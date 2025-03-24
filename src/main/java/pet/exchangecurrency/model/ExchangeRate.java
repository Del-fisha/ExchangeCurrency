package pet.exchangecurrency.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "exchange_rate")
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "base_currency_id", nullable = false)
    private Currency baseCurrency;

    @ManyToOne
    @JoinColumn(name = "target_currency_id", nullable = false)
    private Currency targetCurrency;

    @Column(name = "rate")
    private double rate;

    @Override
    public String toString() {
        return String.format("id=%d, baseCurrencyId=%s, targetCurrencyId=%s, Rate=%f",
                id, baseCurrency, targetCurrency, rate);
    }
}