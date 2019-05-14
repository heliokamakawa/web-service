package br.edu.ifpr.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import entidade.Estado;
import repositorio.RepositorioEstado;

@CrossOrigin
@RestController
@RequestMapping("/webserv")
public class ControleEstado {

	@Autowired
	private RepositorioEstado rep;

	 
	@RequestMapping(value = "/estado/", method = RequestMethod.GET)
    public ResponseEntity<List<Estado>> listAll() {
        List<Estado> estados = rep.findAll();
        if(estados.isEmpty()){
            return new ResponseEntity<List<Estado>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Estado>>(estados, HttpStatus.OK);
    }
 
 
    //-------------------Retrieve Single User--------------------------------------------------------
     
    @RequestMapping(value = "/estado/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Estado> get(@PathVariable("id") Integer id) {
        Estado estado = rep.findById(id).get();
        if (estado == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<Estado>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Estado>(estado, HttpStatus.OK);
    }
     
    //-------------------Create a User--------------------------------------------------------
     
    @RequestMapping(value = "/estado/", method = RequestMethod.POST)
    public void create(@RequestBody Estado estado) { 
        rep.save(estado);
    }
     
    //------------------- Update a User --------------------------------------------------------
     
    @RequestMapping(value = "/estado/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Estado> update(@PathVariable("id") Integer id, @RequestBody Estado estado) {
        Estado currentEstado = rep.findById(id).get();
         
        if (currentEstado==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<Estado	>(HttpStatus.NOT_FOUND);
        }
 
        currentEstado.setNome(estado.getNome());
        currentEstado.setSigla(estado.getSigla());
        currentEstado.setStatus(estado.getStatus());
         
        rep.saveAndFlush(currentEstado);
        return new ResponseEntity<Estado>(currentEstado, HttpStatus.OK);
    }
 
    //------------------- Delete a User --------------------------------------------------------
     
    @RequestMapping(value = "/estado/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Estado> delete(@PathVariable("id") Integer id) {
        System.out.println("Fetching & Deleting User with id " + id);
 
        Estado estado = rep.findById(id).get();
        if (estado == null) {
            return new ResponseEntity<Estado>(HttpStatus.NOT_FOUND);
        }
 
        rep.deleteById(id);
        return new ResponseEntity<Estado>(HttpStatus.NO_CONTENT);
    }
}
