import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ChatType } from '../models/Chat-Type-model.enum';

@Injectable({
  providedIn: 'root'
})
export class HttpServiceService {

  RESULT_URL = `http://localhost:8080/user`;

constructor(private http: HttpClient) { }

get (chatType: ChatType): Observable <any> {
  return this.http.get(`${this.RESULT_URL}`, { params: {chatType: chatType} });
}

}
