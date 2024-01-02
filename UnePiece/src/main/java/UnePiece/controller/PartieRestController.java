package UnePiece.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import UnePiece.dao.IDAOPartie;
import UnePiece.dto.PartieResponse;
import UnePiece.model.Partie;

@RestController
@RequestMapping("/api/partie")
@CrossOrigin("*")
public class PartieRestController {

	@Autowired
	private IDAOPartie daoPartie;

	@GetMapping("/{id}")
	public PartieResponse findById(@PathVariable Integer id) {
		System.out.println("findById Partie");
		Optional<Partie> opt = daoPartie.findById(id);
		System.out.println(opt.get().toString());
		if (opt.isEmpty()) {
			return null;
		}
		PartieResponse partieDTO = new PartieResponse();
		BeanUtils.copyProperties(opt.get(), partieDTO);
		return partieDTO;
	}

	@GetMapping("/joueur/{idJoueur}")
	public PartieResponse findByIdJoueurDTO(@PathVariable Integer idJoueur) {
		System.out.println("findByIdJoueurDTO");
		Partie partie = daoPartie.findByIdJoueur(idJoueur).get(); // pas faire get direct



		PartieResponse partieDTO = new PartieResponse();
		BeanUtils.copyProperties(partie, partieDTO);

		return partieDTO;
	}

	@GetMapping
	public List<PartieResponse> findAll() {
		List<Partie> parties = daoPartie.findAll();
		List<PartieResponse> partiesDTO = new ArrayList<PartieResponse>();
		for (Partie p : parties) {
			PartieResponse partieDTO = new PartieResponse();
			BeanUtils.copyProperties(p, partieDTO);
			partiesDTO.add(partieDTO);
		}
		return partiesDTO;
	}

	@PostMapping
	public PartieResponse insert(@RequestBody Partie partie, BindingResult result) {
		/*
		 * if(result.hasErrors())
		 * {
		 * throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
		 * "La partie n'est pas valide...");
		 * }
		 */
		daoPartie.save(partie);
		PartieResponse partieDTO = new PartieResponse();
		BeanUtils.copyProperties(partie, partieDTO);
		return partieDTO;
	}

	@PutMapping("/{id}")
	public PartieResponse update(@PathVariable Integer id, @RequestBody Partie partie, BindingResult result) {
		daoPartie.save(partie);
		PartieResponse partieDTO = new PartieResponse();
		BeanUtils.copyProperties(partie, partieDTO);
		return partieDTO;
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		daoPartie.deleteById(id);
	}

}
