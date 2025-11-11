package com.Tuki.Tuki_Backend_Provisional.Repositorys;

import com.Tuki.Tuki_Backend_Provisional.Entidades.Enum.Estado;
import com.Tuki.Tuki_Backend_Provisional.Entidades.Pedido;

import java.util.List;

public interface PedidoRepository extends BaseRepository<Pedido,Long> {
    List<Pedido> findByUsuarioIdOrderByIdAsc(Long usuarioId);
    List<Pedido> findAllByOrderByIdAsc();
    List<Pedido> findByEstadoOrderByIdAsc(Estado estado);
}
