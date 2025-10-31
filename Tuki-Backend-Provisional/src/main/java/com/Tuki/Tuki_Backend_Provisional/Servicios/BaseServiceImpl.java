package com.Tuki.Tuki_Backend_Provisional.Servicios;

import com.Tuki.Tuki_Backend_Provisional.Entidades.UsuarioDTOs.Mappers.BaseMapper;
import com.Tuki.Tuki_Backend_Provisional.Repositorys.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class BaseServiceImpl<E, Long, DTOcreate,DTOrespueta> implements BaseService<E, Long, DTOcreate,DTOrespueta> {

    @Autowired
    BaseRepository<E, Long> baseRepository;

    @Autowired
    BaseMapper<E, DTOcreate, DTOrespueta>  baseMapper;

    @Override
    public List<DTOrespueta> listar() {
        List<DTOrespueta> dtOrespuetas = new ArrayList<>();
        for(E e: baseRepository.findAll()){
            dtOrespuetas.add(baseMapper.entityToDTO(e));
        }
        return dtOrespuetas;
    }

    @Override
    public DTOrespueta crear(DTOcreate dtOcreate) {
        E e = baseMapper.dtoToEntity(dtOcreate);
        e = baseRepository.save(e);
        return baseMapper.entityToDTO(e);
    }


    @Override
    public E actualizar(Long id, E entidad) {
        return null;
    }

    @Override
    public Optional<E> bucasPorId(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<E> eliminar(Long id){
        return null;
    }

}
