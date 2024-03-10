import { Component, OnInit } from '@angular/core';
import { FormControl, FormsModule, Validators, ReactiveFormsModule} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { Credenciais } from '../../models/credenciais';
import { ToastrService } from 'ngx-toastr';

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
    ReactiveFormsModule,],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
  providers: [
    {provide: ToastrService, useClass: ToastrService}
  ]
})
export class LoginComponent implements OnInit {

  creds: Credenciais = {
    email: '',
    senha: ''
  }

  email = new FormControl(null, Validators.email);
  senha = new FormControl(null, Validators.minLength(3));

  constructor(private toastr: ToastrService) {}

  ngOnInit(): void {
  }

  logar(){
    this.toastr.error("E-mail e/ou senha inválidos", "Login")
    this.creds.senha = ''
  }

  validaCampos(): boolean{
    return this.email.valid && this.senha.valid
  }

}
