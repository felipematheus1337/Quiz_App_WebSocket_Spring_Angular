import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GreetingsComponent } from './chats/greetings/greetings.component';
import { FailedComponent } from './components/failed/failed.component';
import { DbzComponent } from './chats/dbz/dbz.component';
import { MmaComponent } from './chats/mma/mma.component';
import { CineComponent } from './chats/cine/cine.component';
import { ResultadoComponent } from './components/resultado/resultado.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { HomeComponent } from './components/home/home.component';
import { SelectComponent } from './components/select/select.component';

const routes: Routes = [
  {path: 'home', component: HomeComponent},
  
  {path: 'greetings', component: GreetingsComponent},

  {path: 'select', component: SelectComponent},

  { path: 'falha', component: FailedComponent},

  {path: 'dbz', component: DbzComponent},

  {path: 'mma', component: MmaComponent},

  {path: 'cine', component: CineComponent},

  {path: 'resultado', component: ResultadoComponent},
  
  {path: '**', component: HomeComponent}, 

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
