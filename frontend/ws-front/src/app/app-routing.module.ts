import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GreetingsComponent } from './chats/greetings/greetings.component';
import { FailedComponent } from './components/failed/failed.component';

const routes: Routes = [
  {path: 'greetings', component: GreetingsComponent},

  {path: '', redirectTo: '/greetings', pathMatch: 'full'},

  { path: 'falha', component: FailedComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
