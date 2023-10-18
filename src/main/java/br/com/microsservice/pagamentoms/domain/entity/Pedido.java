package br.com.microsservice.pagamentoms.domain.entity;

import br.com.microsservice.pagamentoms.domain.StatusPedidoEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(precision = 8, scale = 2, nullable = false)
    @Setter(AccessLevel.PRIVATE)
    private BigDecimal valorTotal;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusPedidoEnum status;

    @Embedded
    private DataEmbeddable data = new DataEmbeddable();

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<Item> itemList;

    public Pedido(@NotNull List<Item> itemList) {
        setItemList(itemList);
        setStatus(StatusPedidoEnum.REALIZADO);
        setValorTotal(calcularValorTotal());
    }

    private BigDecimal calcularValorTotal() {
        return itemList
                .stream()
                .map(Item::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void setItemList(@NotNull List<Item> itemList) {
        this.itemList = itemList;
        this.itemList.forEach(item -> item.setPedido(this));
    }

    public void addItem(@NotNull Item item) {
        item.setPedido(this);
        itemList.add(item);
    }
}
