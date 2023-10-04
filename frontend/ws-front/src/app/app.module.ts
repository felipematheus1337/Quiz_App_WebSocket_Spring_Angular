import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { GreetingsComponent } from './chats/greetings/greetings.component';
import { FailedComponent } from './components/failed/failed.component';
import { DbzComponent } from './chats/dbz/dbz.component';
import { MmaComponent } from './chats/mma/mma.component';
import { CineComponent } from './chats/cine/cine.component';
import { ResultadoComponent } from './components/resultado/resultado.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { HomeComponent } from './components/home/home.component';
import { HeaderComponent } from './components/header/header.component';
import { UserService } from './services/user.service';
import { SelectComponent } from './components/select/select.component';
import { WebsocketService } from './services/websocket.service';
import { HttpClientModule } from '@angular/common/http';
import { DisconnectComponent } from './components/disconnect/disconnect.component';



@NgModule({
  declarations: [
    AppComponent,
    GreetingsComponent,
    FailedComponent,
    DbzComponent,
    MmaComponent,
    CineComponent,
    ResultadoComponent,
    NotFoundComponent,
    HomeComponent,
    HeaderComponent,
    SelectComponent,
    DisconnectComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [UserService, WebsocketService],
  bootstrap: [AppComponent]
})
export class AppModule { }
