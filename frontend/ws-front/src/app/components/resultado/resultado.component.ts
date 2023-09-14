import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-resultado',
  templateUrl: './resultado.component.html',
  styleUrls: ['./resultado.component.css']
})
export class ResultadoComponent implements OnInit {
  greetingsWebSocket: WebSocket | undefined;

  constructor() { }

  ngOnInit() {
    this.inicializarGreetingsResult();
  }


  inicializarGreetingsResult() {
    this.greetingsWebSocket = new WebSocket("ws://localhost:8080/quiz-socket");


    this.greetingsWebSocket.onopen = (e) => {
      console.log(e);
    }

    this.greetingsWebSocket.onclose = (e) => {
      console.log(e);
    }

    this.greetingsWebSocket.onmessage = (e) => {
      console.log(e);
    }
  }

}
