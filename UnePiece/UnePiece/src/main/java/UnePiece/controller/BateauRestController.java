package UnePiece.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import UnePiece.dao.IDAOBateau;

@RestController
@RequestMapping("/api/bateau")
public class BateauRestController {
	
	@Autowired
	private IDAOBateau daoBateau;

}
