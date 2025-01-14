import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { BateauService } from '../bateau.service';
import { IleService } from '../ile.service';
import { Joueur, Partie } from '../model';
import { NavireService } from '../navire.service';
import { PartieService } from '../partie.service';

@Component({
  selector: 'ending',
  templateUrl: './ending.component.html',
  styleUrls: ['./ending.component.css'],
})
export class EndingComponent {
  joueur?: Joueur;
  partie?: Partie;

  constructor(
    private partieService: PartieService,
    private router: Router,
    private authService: AuthService
  ) {
    this.joueur = this.authService.getUtilisateur() as Joueur;
    this.partie = this.partieService.getPartie(this.joueur);
    if (this.partie) {
      this.load();
    } else {
      this.partieService.getPartieFromDb(this.joueur).subscribe((resp) => {
        this.partie = resp;
        this.partieService.setPartie(this.partie);
        this.load();
      });
    }
  }

  load() {
    console.log('this.partie ending :>> ', this.partie);
  }

  retourAccueil() {
    localStorage.removeItem('partie');
    this.partie = new Partie();
    this.partieService.setPartie(this.partie);
    this.router.navigate(['/accueil']);
  }
}
