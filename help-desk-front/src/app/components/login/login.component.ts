import { Component, OnInit } from '@angular/core';
import { FormControl, FormsModule, Validators, ReactiveFormsModule} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { Credenciais } from '../../models/credenciais';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from '../../services/auth.service';
import { HttpClientModule } from '@angular/common/http';
import { AppModule } from '../../app.module';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    FormsModule, 
    MatFormFieldModule, 
    MatInputModule, 
    MatButtonModule, 
    MatDividerModule, 
    MatIconModule,
    ReactiveFormsModule,
    HttpClientModule ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
  providers: [
    {provide: ToastrService, useClass: ToastrService}
  ]
})
export class LoginComponent implements OnInit {

  creds: Credenciais = {
    email: '',
    password: ''
  }

  email = new FormControl(null, Validators.email);
  senha = new FormControl(null, Validators.minLength(3));

  constructor(
    private toastr: ToastrService,
    private service: AuthService,
    private router: Router) {}

  ngOnInit(): void {
  }

  logar() {
    this.service.authenticate(this.creds).subscribe(resposta => {
      this.service.successFullLogin(resposta.headers.get('Authorization').substring(7));
      this.router.navigate(['']);
    }, () => {
      this.toastr.error('E-mail e/ou senha inválidos.');
    })
  }

  validaCampos(): boolean{
    return this.email.valid && this.senha.valid
  }

}


