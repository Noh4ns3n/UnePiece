package UnePiece.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import UnePiece.dao.IDAOPirate;

@RestController
@RequestMapping("/api/pirate")
public class PirateRestController {

	@Autowired
	private IDAOPirate daoPirate;

}
