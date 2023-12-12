package com.api.rest.encuestas.reposotories;

import com.api.rest.encuestas.model.Voto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends CrudRepository<Voto, Long> {
}
