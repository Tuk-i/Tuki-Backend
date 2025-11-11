package com.Tuki.Tuki_Backend_Provisional.Repositorys;

import com.Tuki.Tuki_Backend_Provisional.Entidades.DetallePedido;

import java.util.List;

public interface DetallePedidoRepository extends BaseRepository<DetallePedido, Long>{
    List<DetallePedido> findByPedidoId(Long pedidoId);
}
