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
    @OneToOne
    private Product manufacturedProduct;
    @OneToOne
    private Worker workers;

    private BigDecimal payment; //плата рабочему за оперецию(складывается цена израсходованный материалов + плата за операцию)
    private BigDecimal amountCost;//все затраты


    public Operation(String name, Product spentProduct, Product manufacturedProduct, Worker workers, BigDecimal payment) {
        this.name = name;
        this.spentProduct = spentProduct;
        this.manufacturedProduct = manufacturedProduct;
        this.workers = workers;
        this.payment = payment;
    }
}
