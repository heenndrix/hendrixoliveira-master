package br.edu.ifrs.demo;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifrs.model.Produto;


@RestController
@CrossOrigin(origins = "*")// Resolve problemas de CORS
public class ProdutoController {


    @GetMapping("/Produto")
	public List<Produto> getAll() {
		return Produto.getAll();
	}


    @GetMapping("/Produto/{id}")
	public Produto getOne(
                        @PathVariable("id") long id
        ) {
		Produto a =  new Produto();
        a.setId(id);
        if(a.load()){
            return a;
        }else{
            return null;
        }        
	}  

    @PostMapping(value = "/Produto")
    public boolean save(Produto a){
        System.out.println(a);
        return a.insert();
    }

    @PostMapping(value = "/Produto/{id}")
    public boolean update( @PathVariable("id") long id, Produto a){
        a.setId(id);
        return a.update();
    }

    @GetMapping(value = "/Produto/remove/{id}")
    public boolean delete( @PathVariable("id") long id){
        Produto a = new Produto();
        a.setId(id);
        return a.delete();
    }

}



