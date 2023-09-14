import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
import { ChatType } from 'src/app/models/Chat-Type-model.enum';
import { User } from 'src/app/models/User-model';
import { UserService } from 'src/app/services/user.service';

export interface ChatSelectorType {
  name: string;
  url: string;
  chatType: ChatType;
}


@Component({
  selector: 'app-select',
  templateUrl: './select.component.html',
  styleUrls: ['./select.component.css']
})
export class SelectComponent implements OnInit {

  constructor(private userService: UserService,
    private router: Router) { }

  nomeUsuario: string | null = '';
  user: User | null = null;

  listOfChatSelectorTypes : ChatSelectorType[] = [];

  listTeste: ChatSelectorType = {
    chatType: ChatType.GREETINGS,
    name: 'Conhecimentos Gerais',
    url: '../../../assets/imgs/gerais.png'
  };

  ngOnInit() {
    this.criarListaDeChats();
    this.setarUsuario();
    
  }

  setarUsuario() {
    const userSaved = this.userService.obterUsuario();

    if (userSaved)  {
      console.log(userSaved);
      this.user = userSaved;
      this.nomeUsuario = this.user.nome;
    }
    else { 
      this.nomeUsuario = this.userService?.nome;
      this.user = null;
    }

    console.log(userSaved, this.nomeUsuario);
    
  }

  selecionarChat(q: ChatSelectorType): void {
    if (this.user) {
       this.user.chatType = q.chatType;
       localStorage.setItem('user', JSON.stringify(this.user));
    } else {
      this.userService.criarUsuario(this.nomeUsuario, q.chatType);
    }

    this.direcionarQuiz(q.chatType);

  }

  direcionarQuiz(chat: ChatType): void {
    switch (chat) {
      case ChatType.GREETINGS:
        this.router.navigate(['/greetings']);
        break;
      case ChatType.CINEMA:
        this.router.navigate(['/cine']);
        break;
      case ChatType.DBZ:
        this.router.navigate(['/dbz']);
        break;
      case ChatType.MMA:
        this.router.navigate(['/mma']);    
    }
  }

  criarListaDeChats() {

    const listGreetings: ChatSelectorType = {
      chatType: ChatType.GREETINGS,
      name: 'Conhecimentos Gerais',
      url: '../../../assets/imgs/gerais.png'
    };

    const listDBZ: ChatSelectorType = {
      chatType: ChatType.DBZ,
      name: 'Dragon ball Z',
      url: '../../../assets/imgs/dbz-icon.jpg'
    };

    const listMMA: ChatSelectorType = {
      chatType: ChatType.MMA,
      name: 'MMA',
      url: '../../../assets/imgs/mma-icon.png'
    };

    const listCinema: ChatSelectorType = {
      chatType: ChatType.CINEMA,
      name: 'Cinema',
      url: '../../../assets/imgs/cine-icon.png'
    };

    this.listOfChatSelectorTypes.push(listGreetings);
    this.listOfChatSelectorTypes.push(listCinema);
    this.listOfChatSelectorTypes.push(listDBZ);
    this.listOfChatSelectorTypes.push(listMMA);
  }

  voltar(letter: string):void {

    if(letter === 'i') {
      this.router.navigate(['home']);
    } else {
      this.router.navigate(['resultado']);
    }
  }

}
