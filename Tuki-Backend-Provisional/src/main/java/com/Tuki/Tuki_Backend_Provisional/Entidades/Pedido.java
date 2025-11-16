package com.Tuki.Tuki_Backend_Provisional.Entidades;

import com.Tuki.Tuki_Backend_Provisional.Entidades.Enum.Estado;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pedido extends Base{
    private LocalDate fecha;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    private double total;

    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "pedido")
    @Builder.Default
    private List<DetallePedido> detalles = new ArrayList<>();

    public void agregarDetalle(DetallePedido detalle) {
        detalle.setPedido(this);
        this.detalles.add(detalle);
    }


    public void calcularTotal(){
        this.total = detalles.stream()
                .mapToDouble(DetallePedido::getSubtotal)
                .sum();
    }

}
