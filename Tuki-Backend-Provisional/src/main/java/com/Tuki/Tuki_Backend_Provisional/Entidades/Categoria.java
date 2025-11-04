package com.Tuki.Tuki_Backend_Provisional.Entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Categoria extends Base{
    private String nombre;
    private String descripcion;

    @OneToMany(mappedBy = "categoria")
    @Builder.Default
    private Set<Producto> productos = new HashSet<>();



    public void agregrarProducto(Producto p){
        productos.add(p);
    }

    public void eliminarTodosLosProductos(){
        for (Producto p: productos){
            p.setEliminado(true);
        }
    }
}
