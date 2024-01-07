import { Component } from '@angular/core';
import { Action, Partie } from '../model';
import { IleService } from '../ile.service';
import { PartieService } from '../partie.service';
import { Router } from '@angular/router';
import { ActionService } from '../action.service';

@Component({
  selector: 'action',
  templateUrl: './action.component.html',
  styleUrls: ['./action.component.css'],
})
export class ActionComponent {
  partie: Partie = new Partie();
  action: Action = new Action();
  choixPossible: boolean = true;
  actionPayante: boolean = false;
  text?: string;
  title?: string = 'Faites Face';
  title1?: string;
  title2?: string;

  constructor(
    private ileService: IleService,
    private partieService: PartieService,
    private actionService: ActionService,
    private router: Router
  ) {
    this.partie = this.partieService.getPartie() as Partie;
    this.action = this.partie.actions.shift() as Action;
    this.checkForText(this.action.event?.odyssee as string);

    this.partieService.update(this.partie).subscribe(() => {
      this.partieService.savePartieInStorage(this.partie);
    });
  }

  checkForText(nom: string) {
    switch (nom) {
      case 'Monstre':
        this.text = 'Un monstre marin jaillit devant vous !';
        this.title1 = 'Affronter';
        this.title2 = 'Fuir';
        break;
      case 'Tempete':
        this.choixPossible = false;
        this.text =
          'Accrochez vous matelots, la tempête vient droit sur nous !!';
        break;
      case 'Restaurant':
        this.text = 'Qui a une petite faim ?';
        this.actionPayante = true;
        this.title1 = 'Restaurer';
        this.title2 = 'Continuer';
        break;
      case 'Tresor':
        this.text = "Une île mystérieuse ... un trésor s'y cache peut-être";
        this.title1 = 'Explorer';
        this.title2 = 'Continuer';
        break;
      case 'Bataille':
        this.text =
          'Un féroce équipage vous fait face et semble vouloir en découdre !';
        this.title1 = 'Combattre';
        this.title2 = 'Fuir';
        break;
    }
  }

  suite() {
    if (this.partie.joursRestants! > 1) {
      this.partie.joursRestants!--;
      this.partie.duree!++;
      if (this.partie.actions.length != 0) {
        this.action = this.partie.actions.shift() as Action;
        this.checkForText(this.action.event?.odyssee as string);
        this.partieService.update(this.partie).subscribe(() => {
          this.actionService.delete(this.action.id).subscribe(() => {
            this.partieService.savePartieInStorage(this.partie);
          });
        });
      }
    } else {
      this.partie.joursRestants = this.partie.ile?.attente;
      this.partie.duree!++;
      this.partieService.update(this.partie).subscribe(() => {
        this.partieService.savePartieInStorage(this.partie);
        this.router.navigate(['/ile']);
      });
    }
    this.partieService.checkEndOfGame();
  }

  resolveAction() {
    // bouton 1
    if (this.action.choix == true) {
      if (
        this.actionPayante == true &&
        this.partie.tresor! + this.action.tresor! < 0
      ) {
        alert("Pas assez d'argent pour cette action !");
      } else {
        this.partie.membres.forEach((membre, index) => {
          membre.pv! -= this.action.degatMembre!;
          this.partieService.checkDeathMembre(membre, index);
        });
        this.partie.navire!.robustesse! -= this.action.degatNavire!;
        this.partie.tresor! += this.action.tresor!;
        if (this.partie.tresor! < 0) {
          this.partie.tresor = 0;
        }
        this.suite();
      }
    }
    // bouton 2
    else if (this.action.choix == false) {
      if (this.actionPayante == false) {
        const degat: number = Math.floor(Math.random() * 4);
        const alea: number = Math.floor(Math.random() * 3) + 1;
        
		// tresor
		if (alea == 1) {
          this.partie.tresor! -= degat * 2;
          if (this.partie.tresor! <= 0) {
            this.partie.tresor = 0;
            alert('Vous avez perdu tout votre or !');
          } else {
            alert('Vous avez perdu ' + degat * 2 + '฿');
          }
        } 
		
		// robustesse
		else if (alea == 2) {
          this.partie.navire!.robustesse! -= degat;
          alert('Votre navire prend ' + degat + ' dégâts');
        } 
		
		// degat membres
		else if (alea == 3) {
          const membreRandom: number = Math.floor(
            Math.random() * this.partie.membres.length
          );
          // 2 chance sur 3 de n'affecter qu'un membre
          const alea2: number = Math.floor(Math.random() * 3);
          if (alea2 != 3) {
            this.partie.membres[membreRandom].pv! -= degat;
            alert(
              this.partie.membres[membreRandom].pirate?.nom +
                ' subit ' +
                degat +
                ' dégâts'
            );
          } else {
            this.partie.membres.forEach((membre, index) => {
              membre.pv! -= degat;
              this.partieService.checkDeathMembre(membre, index);
            });
            alert('Vos membres subissent ' + degat + ' dégâts');
          }
        }
      }
      this.suite();
    }
  }

  bouton1() {
    console.log(
      '(this.action.event?.odyssee as string) :>> ',
      this.action.event?.odyssee as string
    );
    this.action.choix = true;
    this.resolveAction();
  }

  bouton2() {
    // choisit aléatoirement degatnavire, degatmembre ou tresor et enlève degat
    const degat: number = Math.floor(Math.random() * 4);
    const alea: number = Math.floor(Math.random() * 3) + 1;
    if (alea == 1) {
      // tresor
      this.partie.tresor! -= degat * 2;
      if (this.partie.tresor! <= 0) {
        this.partie.tresor = 0;
        alert('Vous avez perdu tout votre or !');
      } else {
        alert('Vous avez perdu ' + degat * 2 + '฿');
      }
    } else if (alea == 2) {
      // robustesse
      this.partie.navire!.robustesse! -= degat;
      alert('Votre navire prend ' + degat + ' dégâts');
    } else if (alea == 3) {
      // degat membres
      const membreRandom: number = Math.floor(
        Math.random() * this.partie.membres.length
      );
      // 2 chance sur 3 de n'affecter qu'un membre
      const alea2: number = Math.floor(Math.random() * 3);
      if (alea2 != 3) {
        this.partie.membres[membreRandom].pv! -= degat;
        alert(
          this.partie.membres[membreRandom].pirate?.nom +
            ' subit ' +
            degat +
            ' dégâts'
        );
      } else {
        this.partie.membres.forEach((membre, index) => {
          membre.pv! -= degat;
          this.partieService.checkDeathMembre(membre, index);
        });
        alert('Vos membres subissent ' + degat + ' dégâts');
      }
    }
    this.suite();
  }

  boutonTempete() {
    this.partie.membres.forEach((membre, index) => {
      membre.pv! -= this.action.degatMembre!;
      this.partieService.checkDeathMembre(membre, index);
    });
    this.partie.navire!.robustesse! -= this.action.degatNavire!;
    this.choixPossible = true;
    this.suite();
  }
}
