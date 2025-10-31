package com.Tuki.Tuki_Backend_Provisional.Servicios;


import java.util.List;
import java.util.Optional;

public interface BaseService<E, Long, DTOcreate, DTOrespueta>  {
    List<DTOrespueta> listar();
    DTOrespueta crear(DTOcreate dtOcreate);
    E actualizar(Long id, E entidad);
    Optional<E> bucasPorId(Long id);
    Optional<E> eliminar(Long id);

}
