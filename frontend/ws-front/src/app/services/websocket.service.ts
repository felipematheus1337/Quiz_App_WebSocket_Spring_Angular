import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {

  static GREETINGS_QUIZ_ANSWERING_URL = "ws://localhost:8080/greetings";
  static  GREETINGS_UPDATE_RESULT = "ws://localhost:8080/greetings/result";

  static  MMA_QUIZ_ANSWERING_URL = "ws://localhost:8080/mma";
  static  MMA_UPDATE_RESULT = "ws://localhost:8080/mma/result";

  static CINEMA_QUIZ_ANSWERING_URL = "ws://localhost:8080/cinema";
  static  CINEMA_UPDATE_RESULT = "ws://localhost:8080/cinema/result";

  static DBZ_QUIZ_ANSWERING_URL = "ws://localhost:8080/dbz";
  static  DBZ_UPDATE_RESULT = "ws://localhost:8080/dbz/result";

  static STATUS_URL = "ws://localhost:8080/status";
  
  private webSocket: WebSocket | undefined;



constructor() { }

}




