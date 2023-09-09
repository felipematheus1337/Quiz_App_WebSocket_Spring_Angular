import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { GreetingsComponent } from './chats/greetings/greetings.component';
import { FailedComponent } from './components/failed/failed.component';

@NgModule({
  declarations: [
    AppComponent,
    GreetingsComponent,
    FailedComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
