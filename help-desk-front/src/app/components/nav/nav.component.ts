import { Component, OnInit } from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {MatSidenavModule} from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { HeaderComponent } from '../header/header.component';

@Component({
  selector: 'app-nav',
  standalone: true,
  imports: [
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    RouterOutlet,
    RouterLink,
    HeaderComponent
  ],
  templateUrl: './nav.component.html',
  styleUrl: './nav.component.css'
})
export class NavComponent implements OnInit {
  constructor(private router: Router){}

  ngOnInit(): void{
    this.router.navigate(['home'])
  }
  showFiller = false;
}
