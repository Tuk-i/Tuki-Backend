package com.Tuki.Tuki_Backend_Provisional.Controladores;

import com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.Carrito.CarritoDTO;
import com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.PedidoDTOs.PedidoRespuestaDTO;
import com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.PedidoDTOs.PedidoUpdateDTO;
import com.Tuki.Tuki_Backend_Provisional.Entidades.Enum.Estado;
import com.Tuki.Tuki_Backend_Provisional.Servicios.IntefacesServicios.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public List<PedidoRespuestaDTO> listarTodos() {
        return pedidoService.listarTodos();
    }

    @GetMapping("/estado/{estado}")
    public List<PedidoRespuestaDTO> listarPorEstado(@PathVariable Estado estado) {
        return pedidoService.listarPorEstado(estado);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<PedidoRespuestaDTO> listarPorUsuario(@PathVariable Long usuarioId) {
        return pedidoService.listarPedidosPorUsuario(usuarioId);
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody CarritoDTO dto) {
        return pedidoService.crearPedidoDesdeCarrito(dto);
    }

    @PutMapping("/estado")
    public ResponseEntity<?> actualizarEstado(@RequestBody PedidoUpdateDTO dto) {
        return pedidoService.actualizarEstadoPedido(dto);
    }
}