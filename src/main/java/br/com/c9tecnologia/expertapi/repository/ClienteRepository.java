package br.com.c9tecnologia.expertapi.repository;

import br.com.c9tecnologia.expertapi.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
