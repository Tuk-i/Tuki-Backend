package com.Tuki.Tuki_Backend_Provisional.Servicios;

import com.Tuki.Tuki_Backend_Provisional.Entidades.Categoria;
import com.Tuki.Tuki_Backend_Provisional.Entidades.UsuarioDTOs.CategoriaInputDTO;
import com.Tuki.Tuki_Backend_Provisional.Entidades.UsuarioDTOs.CategoriaRespuestaDTO;
import com.Tuki.Tuki_Backend_Provisional.Entidades.UsuarioDTOs.Mappers.CategoriaMapper;
import com.Tuki.Tuki_Backend_Provisional.Repositorys.CategoriaRepository;
import com.Tuki.Tuki_Backend_Provisional.Servicios.IntefacesServicios.CategoriaService;


public class CategoriaServiceIMP extends BaseServiceImpl<Categoria, Long, CategoriaInputDTO, CategoriaRespuestaDTO> implements CategoriaService {
    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    public CategoriaServiceIMP(CategoriaRepository categoriaRepository, CategoriaMapper categoriaMapper) {
        super(categoriaRepository, categoriaMapper);
        this.categoriaRepository = categoriaRepository;
        this.categoriaMapper = categoriaMapper;
    }

}
