package by.factory_accounting.entity.accounting;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import java.math.BigDecimal;
//ордер оприходывания товаров для производства позволяет решить вопрос с разными ценами на один и тот же товар
@Entity
@Data @AllArgsConstructor @NoArgsConstructor

public class ReceiptOrder {

    @Id
    private long id;
    @ManyToOne
    private Product product;
    private BigDecimal quantity;//количество
    private BigDecimal prise;

    public ReceiptOrder(Product product, BigDecimal quantity, BigDecimal prise) {
        this.product = product;
        this.quantity = quantity;
        this.prise = prise;
    }
}
