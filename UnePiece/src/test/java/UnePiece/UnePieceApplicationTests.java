package UnePiece;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import UnePiece.dao.IDAOAction;
import UnePiece.dao.IDAOBateau;
import UnePiece.dao.IDAOCompte;
import UnePiece.dao.IDAOEvenement;
import UnePiece.dao.IDAOIle;
import UnePiece.dao.IDAOMembre;
import UnePiece.dao.IDAONavire;
import UnePiece.dao.IDAOPartie;
import UnePiece.dao.IDAOPirate;
import UnePiece.model.Action;
import UnePiece.model.Bateau;
import UnePiece.model.Evenement;
import UnePiece.model.Ile;
import UnePiece.model.Joueur;
import UnePiece.model.Membre;
import UnePiece.model.Mer;
import UnePiece.model.Navire;
import UnePiece.model.Odyssee;
import UnePiece.model.Partie;
import UnePiece.model.Pirate;

@SpringBootTest
class UnePieceApplicationTests {

	@Autowired
	private IDAOPirate daoPirate;
	@Autowired
	private IDAOMembre daoMembre;
	@Autowired
	private IDAOBateau daoBateau;
	@Autowired
	private IDAONavire daoNavire;
	@Autowired
	private IDAOIle daoIle;
	@Autowired
	private IDAOEvenement daoEvenement;
	@Autowired
	private IDAOCompte daoCompte;
	@Autowired
	private IDAOAction daoAction;
	@Autowired
	private IDAOPartie daoPartie;
	
	@Test
	void initBdd() {
	Pirate pirate1 = new Pirate(10,10,15,true,true,"Luffy");
	Pirate pirate2 = new Pirate(15,15,10,false,false,"Zorro");
	Pirate pirate3 = new Pirate(2,2,2,false,false,"Ussop");
	Pirate pirate4 = new Pirate(18,18,20,true,false,"Robin");
	Pirate pirate5 = new Pirate(12,12,12,false,true,"Capitaine2");
	Pirate pirate6 = new Pirate(13,13,13,false,true,"Capitaine3");
	
	Membre membre1 = new Membre(pirate1.getPv(), pirate1.getPower(), pirate1);
	Membre membre2 = new Membre(pirate2.getPv(), pirate2.getPower(), pirate2);
	Membre membre3 = new Membre(pirate3.getPv(), pirate3.getPower(), pirate3);
	List<Membre> equipage1 = new ArrayList<Membre>();
	Collections.addAll(equipage1, membre1, membre2, membre3);
	
	Membre membre4 = new Membre(pirate1.getPv(), pirate1.getPower(), pirate4);
	Membre membre5 = new Membre(pirate2.getPv(), pirate2.getPower(), pirate2);
	List<Membre> equipage2 = new ArrayList<Membre>();
	Collections.addAll(equipage2, membre4, membre5);
	
	Bateau bateau1 = new Bateau("Vogue Merry",10,10,10,true);
	Bateau bateau2 = new Bateau("Thousand Sunny",15,15,15,false);
	
	Navire navire1 = new Navire(bateau1.getRobustesse(), bateau1);
	Navire navire2 = new Navire(bateau2.getRobustesse(), bateau1);
	
	Ile ile1 = new Ile("Fuschia Village", true, true, true, 10, 1, Mer.EastBlue);
	Ile ile2 = new Ile("Shells Town", false, false, true, 8, 2, Mer.EastBlue);
	Ile ile4 = new Ile("Ile3_EastBlue", true, true, true, 8, 3, Mer.EastBlue);
	Ile ile5 = new Ile("Ile4_EastBlue", true, true, true, 8, 4, Mer.EastBlue, true);
	Ile ile6 = new Ile("Ile1_WestBlue", false, true, true, 8, 1, Mer.WestBlue);
	Ile ile7 = new Ile("Ile2_WestBlue", true, false, true, 8, 2, Mer.WestBlue);
	Ile ile8 = new Ile("Ile3_WestBlue", false, true, false, 8, 3, Mer.WestBlue);
	Ile ile9 = new Ile("Ile4_WestBlue", false, false, false, 8, 4, Mer.WestBlue, true);
	Ile ile3 = new Ile("Elena", false, false, true, 8, 1, Mer.NorthBlue);
	Ile ile10 = new Ile("Ile2_NorthBlue", false, false, true, 8, 2, Mer.NorthBlue);
	Ile ile11 = new Ile("Ile3_NorthBlue", false, false, false, 8, 3, Mer.NorthBlue);
	Ile ile12 = new Ile("Ile4_NorthBlue", true, true, true, 8, 4, Mer.NorthBlue, true);
	Ile ile13 = new Ile("Ile1_SouthBlue", false, true, false, 8, 1, Mer.SouthBlue);
	Ile ile14 = new Ile("Ile2_SouthBlue", false, true, true, 8, 2, Mer.SouthBlue);
	Ile ile15 = new Ile("Ile3_SouthBlue", true, true, false, 8, 3, Mer.SouthBlue);
	Ile ile16 = new Ile("Ile4_SouthBlue", true, false, true, 8, 4, Mer.SouthBlue, true);
	Ile ile17 = new Ile("Ile1_GrandLine", false, true, true, 8, 1, Mer.GrandLine);
	Ile ile18 = new Ile("Ile2_GrandLine", true, false, false, 8, 2, Mer.GrandLine);
	Ile ile19 = new Ile("Ile3_GrandLine", false, true, true, 8, 3, Mer.GrandLine);
	Ile ile20 = new Ile("Ile4_GrandLine", false, false, false, 8, 4, Mer.GrandLine, true);
	Ile ile21 = new Ile("Ile1_NewWorld", true, true, true, 8, 1, Mer.NewWorld);
	Ile ile22 = new Ile("Ile2_NewWorld", true, false, false, 8, 2, Mer.NewWorld);
	Ile ile23 = new Ile("Ile3_NewWorld", false, true, false, 8, 3, Mer.NewWorld);
	Ile ile24 = new Ile("Ile4_NewWorld", false, false, true, 8, 4, Mer.NewWorld, true);


	Evenement event1 = new Evenement(10,10,0,Odyssee.Tempete);
	Evenement event2 = new Evenement(15,15,10,Odyssee.Bataille);
	Evenement event3 = new Evenement(0,0,50,Odyssee.Tresor);
	
	Joueur joueur1 = new Joueur("player1", "password");
	Joueur joueur2 = new Joueur("player2", "password");
	Joueur joueur3 = new Joueur("player3", "password");
	
	Action action1 = new Action(true, event1.getDegatNavire(), event1.getDegatMembre(), event1.getTresor(), event1);
	Action action2 = new Action(false, event2.getDegatNavire(), event2.getDegatMembre(), event2.getTresor(), event2);
	Action action3 = new Action(false, event3.getDegatNavire(), event3.getDegatMembre(), event3.getTresor(), event3);
	List<Action> actionsPartie1 = new ArrayList<Action>();
	Collections.addAll(actionsPartie1, action1, action2);
	List<Action> actionsPartie2 = new ArrayList<Action>();
	Collections.addAll(actionsPartie2, action3);
	
	daoPirate.save(pirate1);
	daoPirate.save(pirate2);
	daoPirate.save(pirate3);
	daoPirate.save(pirate4);
	daoPirate.save(pirate5);
	daoPirate.save(pirate6);
	
	daoMembre.save(membre1);
	daoMembre.save(membre2);
	daoMembre.save(membre3);
	daoMembre.save(membre4);
	daoMembre.save(membre5);
	
	daoBateau.save(bateau1);
	daoBateau.save(bateau2);
	
	daoNavire.save(navire1);
	daoNavire.save(navire2);
	
	daoIle.save(ile1);
	daoIle.save(ile2);
	daoIle.save(ile3);	
	daoIle.save(ile4);
	daoIle.save(ile5);
	daoIle.save(ile6);
	daoIle.save(ile7);
	daoIle.save(ile8);
	daoIle.save(ile9);
	daoIle.save(ile10);
	daoIle.save(ile11);
	daoIle.save(ile12);
	daoIle.save(ile13);
	daoIle.save(ile14);
	daoIle.save(ile15);
	daoIle.save(ile16);
	daoIle.save(ile17);
	daoIle.save(ile18);
	daoIle.save(ile19);
	daoIle.save(ile20);
	daoIle.save(ile21);
	daoIle.save(ile22);	
	daoIle.save(ile23);
	daoIle.save(ile24);
	
	daoEvenement.save(event1);
	daoEvenement.save(event2);
	daoEvenement.save(event3);
	
	daoCompte.save(joueur1);
	daoCompte.save(joueur2);
	daoCompte.save(joueur3);
	
	daoAction.save(action1);
	daoAction.save(action2);
	daoAction.save(action3);
	}
}
