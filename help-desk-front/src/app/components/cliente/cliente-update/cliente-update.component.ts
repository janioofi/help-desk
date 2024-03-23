import { Component, OnInit } from '@angular/core';
import { FormControl, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { NgxMaskDirective, provideNgxMask } from 'ngx-mask';
import { Cliente } from '../../../models/cliente';
import { ClienteService } from '../../../services/cliente.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-cliente-update',
  standalone: true,
  imports: [
    MatCheckboxModule,
    MatIconModule,
    MatInputModule,
    MatFormFieldModule,
    MatButtonModule,
    RouterLink,
    FormsModule,
    ReactiveFormsModule,
    NgxMaskDirective,
],
providers: [provideNgxMask()],
  templateUrl: './cliente-update.component.html',
  styleUrl: './cliente-update.component.css'
})
export class ClienteUpdateComponent implements OnInit {
  cliente: Cliente = {
    id_pessoa: "",
    nome: "",
    cpf: "",
    email: "",
    senha: "",
    perfis: [],
    dataCriacao: "",
  };

nome: FormControl = new FormControl(null, Validators.minLength(3));
cpf: FormControl = new FormControl(null, [Validators.required]);
email: FormControl = new FormControl(null, Validators.email);
senha: FormControl = new FormControl(null, Validators.minLength(3));

constructor(
    private service: ClienteService,
    private toastr: ToastrService,
    private router: Router,
    private route: ActivatedRoute
) {}

ngOnInit(): void {
  this.cliente.id_pessoa = this.route.snapshot.paramMap.get('id')
  this.findById();
}

addPerfil(perfil: any): void{
  
  if(this.cliente.perfis.includes(perfil)){
    this.cliente.perfis.splice(this.cliente.perfis.indexOf(perfil), 1)
  }else{
    this.cliente.perfis.push(perfil)
  }

  console.log(this.cliente.perfis)
}

findById(){
  this.service.findById(this.cliente.id_pessoa).subscribe(response => {
    response.perfis = [];
    this.cliente = response;
  })
}

update(): void {
  this.service.update(this.cliente).subscribe(
    () => {
      this.toastr.success("Cliente atualizado com sucesso", "Atualização");
      this.router.navigate(['clientes'])
    },
    (ex) => {
      console.log(ex);
      if(ex.error.errors){
        ex.error.errors.forEach(element => {
          this.toastr.error(element.message);
        })
      }else{
        this.toastr.error(ex.error);
      }
    }
  );
}

validaCampos(): boolean {
      return (
          this.nome.valid &&
          this.email.valid &&
          this.senha.valid
      );
}
}
