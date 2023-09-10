import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GreetingsComponent } from './chats/greetings/greetings.component';
import { FailedComponent } from './components/failed/failed.component';
import { DbzComponent } from './chats/dbz/dbz.component';
import { MmaComponent } from './chats/mma/mma.component';
import { CineComponent } from './chats/cine/cine.component';
import { ResultadoComponent } from './components/resultado/resultado.component';

const routes: Routes = [
  {path: 'greetings', component: GreetingsComponent},

  {path: '', redirectTo: '/greetings', pathMatch: 'full'},

  { path: 'falha', component: FailedComponent},

  {path: 'dbz', component: DbzComponent},

  {path: 'mma', component: MmaComponent},

  {path: 'cine', component: CineComponent},

  {path: 'resultado', component: ResultadoComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
