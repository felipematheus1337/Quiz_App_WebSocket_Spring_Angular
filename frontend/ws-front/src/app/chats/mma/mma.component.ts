import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MessageType } from 'src/app/models/MessageType-model.enum';
import { Question } from 'src/app/models/Question-model.enum';
import { StatusUser } from 'src/app/models/Status-User.enum';
import { UserRequest } from 'src/app/models/User-Request-model';
import { User } from 'src/app/models/User-model';
import { UserService } from 'src/app/services/user.service';
import { WebsocketService } from 'src/app/services/websocket.service';

@Component({
  selector: 'app-mma',
  templateUrl: './mma.component.html',
  styleUrls: ['./mma.component.css']
})
export class MmaComponent implements OnInit {

  private webSocket: WebSocket | undefined ;

  welcome: string | undefined;
  texto: string = '';

  isCustom: boolean | null = null;

  questionNumber: number = 0;

  opcoes = ['A', 'B', 'C', 'D'];

  questoes: Question  | null = null;

  nomeUser: string | null = '';

  totalPontos: number = 0;

  opcaoResultado: number | undefined = undefined;  

  usuario: User = new User();

  cadastrado: boolean | null = false;

  finalizado: boolean | null = false;

  constructor(private router: Router,
    private userService: UserService) {
    
  }
  ngOnInit(): void {
    this.inicializarWebSocket();
    this.setarUsuario();
  }

  mensagem!: string;
  LAST_QUESTION_NUMBER = 4;

  inicializarWebSocket(): any {
    this.webSocket = new WebSocket(WebsocketService.MMA_QUIZ_ANSWERING_URL);

    this.webSocket.onopen = (e) => {
      console.log('Conexão WebSocket estabelecida com sucesso');
    };

    this.webSocket.onclose = (e) => {
      this.conexaoFechada(e);
       
    };

    this.webSocket.onerror = (error) => {
      this.ErrorConexao(error);
    };

    this.webSocket.onmessage = (msgEvent) => {
      this.recebeuMensagem(msgEvent);
    }
  }

  ErrorConexao(e: Event) {
    this.router.navigate(['/falha']);
  }

  conexaoFechada(e: CloseEvent) {
    this.router.navigate(['/resultado']);
  }
  
  setarUsuario() {
    this.usuario = JSON.parse(localStorage.getItem('user')!);
    this.nomeUser = this.usuario?.nome;
    this.cadastrado = true;
  }

  recebeuMensagem(msg: MessageEvent) {
    const data = JSON.parse(msg.data); 
    console.log(data);

    if (data?.messageType === MessageType.CUSTOM) {
      this.isCustom = true;
      const mensagemCustomizada = data?.customMessage;

      if (mensagemCustomizada) {
        this.mensagem = mensagemCustomizada;
      } else {
        this.mensagem = '';
      }
      this.questoes = null;
    } else {
      this.questoes = data;
      this.opcaoResultado = data?.correctOption;
      this.isCustom = false;
      this.questionNumber++;
      if (this.questoes?.messageType == MessageType.LAST_QUESTION) this.finalizado = true;
    }
  
  }
  

  capturarValor(valor: string) {
    const letra = valor.split(valor.charAt(1))[0];
    const respostaCorreta = this.verificarSeAOpcaoEstaCorreta(letra);

    if (respostaCorreta) this.totalPontos = this.totalPontos + 1;
      
    this.enviarMsg(letra);
  }

  verificarSeAOpcaoEstaCorreta(opcao: string): boolean {
    const opcaoCorretaIndex = this.opcaoResultado;
    return opcao === this.opcoes[opcaoCorretaIndex!];
  }

  enviarMsg(letra: any) {

    const userRequest = new UserRequest();
    userRequest.identificador = this.usuario.identificador;
    userRequest.letra = letra;
    userRequest.totalPontos = this.totalPontos;
    userRequest.questionIndex = this.questionNumber - 1;
    userRequest.status = this.questionNumber === this.LAST_QUESTION_NUMBER ? StatusUser.FINISHED : StatusUser.ANSWERING;

    this.webSocket?.send(JSON.stringify(userRequest!));
     
    if (this.finalizado) {
      this.questoes = new Question();
    }


  }

  enviarUsuario() {

    this.usuario.totalPontos = this.totalPontos;
    this.webSocket?.send(JSON.stringify(this.usuario!));
    this.cadastrado = true;
  }

  retornarSelect() {
    this.router.navigate(['/select']);  
  }

}
