import { Component } from '@angular/core';
import { DashboardComponent } from "./dashboard/dashboard.component";
import { NavbarComponent } from "./navbar/navbar.component";
import { RouterOutlet } from '@angular/router';
@Component({
    selector: 'app-root',
    standalone: true,
    imports: [DashboardComponent, NavbarComponent, RouterOutlet],
    templateUrl: './app.component.html',
    styleUrl: './app.component.css'
})
export class AppComponent {
  
  title= "funciona"

}
