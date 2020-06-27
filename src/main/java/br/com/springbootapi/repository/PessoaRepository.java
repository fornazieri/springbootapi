package br.com.springbootapi.repository;

import br.com.springbootapi.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    //para o repositório foi necessário apenas estender da interface JpaRepository do Spring Data.
    //Esta interface possui métodos para as operações padrão de um CRUD.
}
