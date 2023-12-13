package com.api.rest.encuestas.reposotories;

import com.api.rest.encuestas.model.Voto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends CrudRepository<Voto, Long> {

    @Query(value = "select v.* from Opcion o, Voto v where o.encuesta_id = ?1 and v.opcion_id = o.opcion_id", nativeQuery = true)
    public Iterable<Voto> findByEncuesta(Long encuestaId);
}
