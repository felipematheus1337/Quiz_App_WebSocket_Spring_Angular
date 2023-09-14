import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { WebsocketService } from 'src/app/services/websocket.service';

export enum TypeStatus {
  DISPONIVEL = ("DISPONÍVEL"),
  INDISPONIVEL = ("INDISPONÍVEL")
}

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private route: Router,
    private userService: UserService) { }
    
  private statusWebSocket: WebSocket | undefined;  

  NOT_CONNECTED_IMG = '../../../assets/imgs/websocket-ds.gif';

  nome: string | null = '';

  status: boolean | null = false;

  ngOnInit() {

    this.estabelecerConexaoWebSocket();
    
    this.iniciarVerificacaoPeriodica();
   
  }

  estabelecerConexaoWebSocket() {
    this.statusWebSocket = new WebSocket(WebsocketService.STATUS_URL);

    this.statusWebSocket.onopen = (e) => {
      console.log('Conexão WebSocket estabelecida com sucesso');
      this.status = true;
    }

    this.statusWebSocket.onclose = (e) => {
      console.log('Conexão WebSocket finalizada.')
      this.status = false;
    }

    this.statusWebSocket.onerror = (e) => {
      console.log('Falha ao conectar com servidor WebSocket');
      this.status = false;
    }
  }

  escolherChat() {
    if (this.nome !== '' || this.nome !== null || this.nome !== undefined) {

      if (this.nome?.trim() !== '' && typeof this.nome === 'string' && this.nome?.length > 3) {

        const hasUser = this.userService.obterUsuario();
        if(hasUser) this.userService.removerUsuario();

        console.log("[HomeComponent] Indo para o Select.")

        this.userService.nome = this.nome;
        this.route.navigate(['select']);

      } else {
        alert("É necessário ter mais de 3 caracteres.");
      }
      
    } else {
      alert("Nome inválido!");
    }
    
  }

  verificarStatus() {
    this.estabelecerConexaoWebSocket();
  }

  private iniciarVerificacaoPeriodica() {
    const intervalId = setInterval(() => {
      if (!this.status) {
        this.verificarStatus();
      } else {
        clearInterval(intervalId);
      }
    }, 10000);
  }
  
  

}
