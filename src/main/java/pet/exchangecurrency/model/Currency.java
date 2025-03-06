package pet.exchangecurrency.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "currencies")
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "sign")
    private String sign;

    @Override
    public String toString() {
        return String.format("id=%d, code=%s, fullName=%s, sign=%s", id, code, fullName, sign);
    }
}