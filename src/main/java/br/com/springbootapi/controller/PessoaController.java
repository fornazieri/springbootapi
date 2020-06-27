package br.com.springbootapi.controller;

import br.com.springbootapi.entity.Pessoa;
import br.com.springbootapi.repository.PessoaRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController //A anotação @RestController permite definir um controller com características REST;
public class PessoaController {

    @Autowired //A anotação @Autowired delega ao Spring Boot a inicialização do objeto;
    private PessoaRepository _pessoaRepository;

    //A anotação @RequestMapping permite definir uma rota.
    //Caso não seja informado o método HTTP da rota, ela será definida para todos os métodos.
    @ApiOperation(value = "Retorna uma lista de pessoas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de pessoa"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @RequestMapping(value = "/pessoa", method = RequestMethod.GET, produces="application/json")
    public List<Pessoa> Get() {
        return _pessoaRepository.findAll();
    }

    @RequestMapping(value = "/pessoa/{id}", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<Pessoa> GetById(@PathVariable(value = "id") long id)
    { //A anotação @PathVariable indica que o valor da variável virá de uma informação da rota;
        Optional<Pessoa> pessoa = _pessoaRepository.findById(id);
        if(pessoa.isPresent())
            return new ResponseEntity<Pessoa>(pessoa.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/pessoa", method =  RequestMethod.POST, produces="application/json", consumes="application/json")
    public Pessoa Post(@Valid @RequestBody Pessoa pessoa)
    { //A anotação @RequestBody indica que o valor do objeto virá do corpo da requisição;
      //E a anotação @Valid indica que os dados recebidos devem ser validados.
        return _pessoaRepository.save(pessoa);
    }

    @RequestMapping(value = "/pessoa/{id}", method =  RequestMethod.PUT, produces="application/json", consumes="application/json")
    public ResponseEntity<Pessoa> Put(@PathVariable(value = "id") long id, @Valid @RequestBody Pessoa newPessoa)
    {
        Optional<Pessoa> oldPessoa = _pessoaRepository.findById(id);
        if(oldPessoa.isPresent()){
            Pessoa pessoa = oldPessoa.get();
            pessoa.setNome(newPessoa.getNome());
            pessoa.setSobrenome(newPessoa.getSobrenome());
            _pessoaRepository.save(pessoa);
            return new ResponseEntity<Pessoa>(pessoa, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/pessoa/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    {
        Optional<Pessoa> pessoa = _pessoaRepository.findById(id);
        if(pessoa.isPresent()){
            _pessoaRepository.delete(pessoa.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}

