package com.Tuki.Tuki_Backend_Provisional.Servicios.IntefacesServicios;


import com.Tuki.Tuki_Backend_Provisional.Entidades.Base;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface BaseService<E, Long, DTOPost, DTOUpdate, DTOrespueta>  {
    List<DTOrespueta> listarActivos();
    List<DTOrespueta> listarTodos();
    List<DTOrespueta> listarEliminados();
    DTOrespueta crear(DTOPost dtOcreate);
    DTOrespueta actualizar(Long id, DTOUpdate dtOmodicador);
    Optional<E> eliminar(Long id);
    ResponseEntity<?> reactivar(java.lang.Long id);

}