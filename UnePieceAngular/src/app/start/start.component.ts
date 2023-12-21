import { Component } from '@angular/core';
import { PirateService } from '../pirate.service';
import { Pirate } from '../model';

@Component({
  selector: 'app-start',
  templateUrl: './start.component.html',
  styleUrls: ['./start.component.css']
})
export class StartComponent {

  capitaines:Pirate[] = [];

constructor(private pirateService:PirateService) {}

listCapitaines():Pirate[] {
this.pirateService.findAll().subscribe(resp => {
  this.capitaines = resp;
  this.capitaines = this.capitaines.filter(pirate => pirate.capitaine == true);
  console.log('this.capitaines :>> ', this.capitaines);
}
);
  return this.capitaines;
}

}