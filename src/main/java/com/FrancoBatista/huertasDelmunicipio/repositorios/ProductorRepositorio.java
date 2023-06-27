
package com.FrancoBatista.huertasDelmunicipio.repositorios;

import com.FrancoBatista.huertasDelmunicipio.entidades.Productor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductorRepositorio extends JpaRepository<Productor,String> {
      //genero la query para poder buscar los productores a traves de su atributo "nombre"
    @Query("SELECT l FROM Productor l Where l.nombre = :nombre")
    public Productor buscarPorNombre(@Param("nombre") String nombre); 
}
