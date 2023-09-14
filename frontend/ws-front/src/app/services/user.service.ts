import { Injectable, OnInit } from '@angular/core';
import { ChatType } from '../models/Chat-Type-model.enum';
import { User } from '../models/User-model';
import { v4 } from 'uuid';
import { StatusUser } from '../models/Status-User.enum';

@Injectable({
  providedIn: 'root'
})
export class UserService implements OnInit {

  nome: string | null = null;
  chat: ChatType | null = null
  
  private user: User | null = null;

constructor() { }


ngOnInit(): void {
  console.log(this.nome, this.chat);
}
  

criarUsuario(nome: string | null, chat: ChatType | null) {
  this.nome = nome;
  this.chat = chat;
  this.user = new User();

  this.user.nome = this.nome!;
  this.user.chatType = this.chat!;
  this.user.statusUser = StatusUser.ANSWERING;
  this.user.identificador = v4();
  
  localStorage.setItem('user', JSON.stringify(this.user));
}

obterUsuario(): User | null {
  if (this.user) {
    return this.user;
  } else {
    const userData = localStorage.getItem('user');
    if (userData) {
      const parsedUser = JSON.parse(userData);
      if (parsedUser && parsedUser.nome) {
        this.user = parsedUser;
        return this.user;
      }
    }
    return null;
  }
}


removerUsuario(): void {
  if (this.user) this.user = null;
  
  localStorage.removeItem('user');
}



}
