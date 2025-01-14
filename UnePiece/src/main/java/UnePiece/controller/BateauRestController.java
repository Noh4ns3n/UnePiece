package UnePiece.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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

import UnePiece.dao.IDAOBateau;
import UnePiece.dao.IDAOIle;
import UnePiece.model.Bateau;
import UnePiece.model.Ile;

@RestController
@RequestMapping("/api/bateau")
@CrossOrigin("*")
public class BateauRestController {

	@Autowired
	private IDAOBateau daoBateau;
	@Autowired
	private IDAOIle daoIle;

	@GetMapping("/{id}")
	public Bateau findById(@PathVariable Integer id) {
		Optional<Bateau> opt = daoBateau.findById(id);
		if (opt.isEmpty()) {
			return null;
		}
		return opt.get();
	}

	@GetMapping("/random/{idIle}")
	public List<Bateau> getRandomBateaux(@PathVariable Integer idIle) {
		List<Bateau> bateaux = this.findAll();

		// retire les bateaux pas de début quand on est sur la première île (idIle = 1)
		if (idIle == 1) {
			int totalBateaux = bateaux.size();
			for (int i = 0; i < totalBateaux; i++) {
				if (i < bateaux.size()) {
					Bateau b = bateaux.get(i);
					if (!b.isDebut()) {
						bateaux.remove(i);
						i--;
					}
				}
			}
		}
		// trie selon la mer et le tier du bateau
		Optional<Ile> opt = daoIle.findById(idIle);
		if (opt.isEmpty()) {
			System.out.println("Aucune île trouvée avec cet Id (getRandomBateaux BateauRestController)");
			return null;
		}
		Ile destination = opt.get();
		String seaName = destination.getMer().name();
		for (int i = 0; i < bateaux.size(); i++) {
			if (seaName == "EastBlue") {
				bateaux = daoBateau.findAllByTier(1);
			} else if (seaName == "WestBlue" || seaName == "NorthBlue") {

				bateaux = daoBateau.findAllByTierOrTier(1, 2);
			} else if (seaName == "SouthBlue") {
				bateaux = daoBateau.findAllByTierOrTier(2, 3);
			} else if (seaName == "GrandLine" || seaName == "NewWorld") {
				bateaux = daoBateau.findAllByTierOrTier(3, 4);
			}
		}

		// renvoie des bateaux random et uniques
		List<Bateau> shuffledBateaux = new ArrayList<Bateau>();
		int max = 3;
		int min = 2;
		Random random = new Random();
		int nombreBateaux = random.nextInt(max - min + 1) + min;
		int i = 1;
		while (i <= nombreBateaux) {
			Random random1 = new Random();
			Bateau randomBateau = bateaux.get(random1.nextInt(bateaux.size()));
			if (!shuffledBateaux.contains(randomBateau)) {
				shuffledBateaux.add(randomBateau);
				i++;
			}
		}
		return shuffledBateaux;
	}

	@GetMapping
	public List<Bateau> findAll() {
		return daoBateau.findAll();
	}

	@PostMapping
	public Bateau insert(@RequestBody Bateau bateau, BindingResult result) {
		return daoBateau.save(bateau);
	}

	@PutMapping("/{id}")
	public Bateau update(@PathVariable Integer id, @RequestBody Bateau bateau, BindingResult result) {
		return daoBateau.save(bateau);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		daoBateau.deleteById(id);
	}

}
