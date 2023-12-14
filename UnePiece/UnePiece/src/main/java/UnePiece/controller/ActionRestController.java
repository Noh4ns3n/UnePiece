package UnePiece.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import UnePiece.dao.IDAOAction;
import UnePiece.model.Action;
import UnePiece.view.Views;

@RestController
@RequestMapping("/api/action")
public class ActionRestController {
	
	@Autowired
	private IDAOAction daoAction;

	@GetMapping("/{id}")
	@JsonView(Views.Action.class)
	public Action findById(@PathVariable Integer id) 
	{
		Optional<Action> opt = daoAction.findById(id);
		if(opt.isEmpty()) 
		{
			return null;
		}
		return opt.get();
	}
	
	@GetMapping
	@JsonView(Views.Common.class)
	public List<Action> findAll() 
	{
		return daoAction.findAll();
	}
	
	@PostMapping
	@JsonView(Views.Action.class)
	public Action insert(@RequestBody Action action, BindingResult result) 
	{
		/*if(result.hasErrors()) 
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La action n'est pas valide...");
		}*/
		return daoAction.save(action);
	}
	
	@PutMapping("/{id}")
	@JsonView(Views.Action.class)
	public Action update(@RequestBody Action action, BindingResult result) 
	{
		/*if(result.hasErrors()) 
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La action n'est pas valide...");
		}*/
		return daoAction.save(action);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) 
	{
		daoAction.deleteById(id);
	}
	
}
