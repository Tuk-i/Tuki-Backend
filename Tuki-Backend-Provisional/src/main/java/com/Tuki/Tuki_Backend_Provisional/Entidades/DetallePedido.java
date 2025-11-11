package com.Tuki.Tuki_Backend_Provisional.Entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DetallePedido extends Base{
    private int cantidad;
    private double subtotal;

    @ManyToOne
    private Pedido pedido;

    @ManyToOne
    private Producto producto;

    public void calcularSubtotal(){
        if (producto != null && cantidad > 0){
            subtotal = producto.getPrecio() * cantidad;
        }
    }
}
