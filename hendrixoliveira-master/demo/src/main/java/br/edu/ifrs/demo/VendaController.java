
package br.edu.ifrs.demo;



import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifrs.model.Venda;

@RestController
@CrossOrigin(origins = "*")// Resolve problemas de CORS
public class VendaController {

	@GetMapping("/venda")
	public String index() {
		Venda v = new Venda();
        v.lazyLoad();

        long idUsuario = v.getUsuario().getId();

        return null;
	}
  
   

}
