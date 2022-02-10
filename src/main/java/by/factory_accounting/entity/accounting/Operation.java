package by.factory_accounting.entity.accounting;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToOne
    private Product spentProduct;
    private BigDecimal requiredQuantityForProduction;
    @OneToOne
    private Product manufacturedProduct;
    @OneToOne
    private Worker worker;

    private BigDecimal payment; //плата рабочему за оперецию(складывается цена израсходованный материалов + плата за операцию)
    private BigDecimal amountCost;//все затраты

     public Operation(String name, Product spentProduct, BigDecimal requiredQuantityForProduction, Product manufacturedProduct, Worker worker, BigDecimal payment) {
        this.name = name;
        this.spentProduct = spentProduct;
        this.requiredQuantityForProduction = requiredQuantityForProduction;
        this.manufacturedProduct = manufacturedProduct;
        this.worker = worker;
        this.payment = payment;
    }
}
