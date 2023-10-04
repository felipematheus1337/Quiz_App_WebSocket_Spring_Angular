import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-disconnect',
  templateUrl: './disconnect.component.html',
  styleUrls: ['./disconnect.component.css']
})
export class DisconnectComponent implements OnInit {

  NOT_CONNECTED_IMG = '../../../assets/imgs/websocket-ds.gif';
  
  constructor() { }

  ngOnInit() {
  }

  @Input()
  servidor: boolean = false;

}
