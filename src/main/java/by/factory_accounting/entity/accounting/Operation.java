package by.factory_accounting.entity.accounting;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.LifecycleState;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;


    @OneToMany
    private List<Product> spentProducts;
    @OneToMany
    private List<Product> manufacturedProducts;
    @ManyToMany
    private List<Worker> workers;
    private BigDecimal payment; //плата рабочему за оперецию


}
