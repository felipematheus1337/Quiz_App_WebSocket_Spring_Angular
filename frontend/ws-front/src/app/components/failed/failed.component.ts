import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-failed',
  templateUrl: './failed.component.html',
  styleUrls: ['./failed.component.css']
})
export class FailedComponent implements OnInit {

  GIF_URL = "https://media1.giphy.com/media/9J7tdYltWyXIY/giphy.gif?cid=ecf05e475s67jl6ilpkylcq2qgrlovnuyuw0uhf2093etwyk&ep=v1_gifs_search&rid=giphy.gif&ct=g";
  GIF_URL_2 = "https://i.pinimg.com/originals/8a/dc/18/8adc18bb9db584ad6c4cefab715f2ebc.gif"

  constructor(private route: Router) { }

  ngOnInit() {
  }

  voltarMenu() {
    this.route.navigate(['/home'])
  }

}
