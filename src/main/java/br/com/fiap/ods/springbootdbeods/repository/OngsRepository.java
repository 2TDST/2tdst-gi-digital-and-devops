package br.com.fiap.ods.springbootdbeods.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.fiap.ods.springbootdbeods.model.Ongs;

@Repository
public interface OngsRepository extends JpaRepository<Ongs, Long>{

}
