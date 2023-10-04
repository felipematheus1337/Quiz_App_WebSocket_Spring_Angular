import { Component, OnInit } from '@angular/core';
import { ChatType } from 'src/app/models/Chat-Type-model.enum';
import { User } from 'src/app/models/User-model';
import { UserResult } from 'src/app/models/User-result-model';
import { HttpServiceService } from 'src/app/services/httpService.service';
import { WebsocketService } from 'src/app/services/websocket.service';

@Component({
  selector: 'app-resultado',
  templateUrl: './resultado.component.html',
  styleUrls: ['./resultado.component.css']
})
export class ResultadoComponent implements OnInit {

  greetingsWebSocket: WebSocket | undefined;
  UPDATE_URL: string = "../../../assets/imgs/update.png"

  private statusWebSocket: WebSocket | undefined;
  status: boolean | null = false;


  public isUpdating = false;

  dbzResult: UserResult[] = [];
  greetingsResult: UserResult[] = [];
  mmaResult: UserResult[] = [];
  cinemaResult: UserResult[] = [];

  constructor(private httpService: HttpServiceService, private webSocketService: WebsocketService) { }

  ngOnInit() {
    this.inicializarResultados();

    this.estabelecerConexaoWebSocket();
    
    this.iniciarVerificacaoPeriodica();
  }


  inicializarResultados() {
    this.httpService.get(ChatType.GREETINGS).subscribe((resultado: UserResult[]) => {
     this.greetingsResult = [...resultado];
     console.log(this.greetingsResult);
    })

    this.httpService.get(ChatType.CINEMA).subscribe((resultado: UserResult[]) => {
      this.cinemaResult = [...resultado];
      console.log(this.cinemaResult);
     })

     this.httpService.get(ChatType.DBZ).subscribe((resultado: UserResult[]) => {
      this.dbzResult = [...resultado];
      console.log(this.cinemaResult);
     })

     this.httpService.get(ChatType.MMA).subscribe((resultado: UserResult[]) => {
      this.mmaResult = [...resultado];
      console.log(this.cinemaResult);
     })
  }

  public isRotating = false;

  public atualizar() {
    
    this.isRotating = !this.isRotating;

    this.isUpdating = true;

    setTimeout(() => {
      this.isUpdating = false;
    }, 1000);
  
    this.inicializarResultados();
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

  ngOnDestroy(): void {
    this.statusWebSocket?.close();
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

}
