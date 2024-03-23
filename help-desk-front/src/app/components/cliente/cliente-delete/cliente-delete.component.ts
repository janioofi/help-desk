import { Component } from '@angular/core';
import { FormControl, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { NgxMaskDirective, provideNgxMask } from 'ngx-mask';
import { ClienteService } from '../../../services/cliente.service';
import { ToastrService } from 'ngx-toastr';
import { Cliente } from '../../../models/cliente';

@Component({
  selector: 'app-cliente-delete',
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
  templateUrl: './cliente-delete.component.html',
  styleUrl: './cliente-delete.component.css'
})
export class ClienteDeleteComponent {
  cliente: Cliente = {
    id_pessoa: "",
    nome: "",
    cpf: "",
    email: "",
    senha: "",
    perfis: [],
    dataCriacao: "",
  };

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

  findById(){
    this.service.findById(this.cliente.id_pessoa).subscribe(response => {
      response.perfis = [];
      this.cliente = response;
    })
  }

  delete(): void {
    this.service.delete(this.cliente.id_pessoa).subscribe(
      () => {
        this.toastr.success("Cliente deletado com sucesso", "Deletado");
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
}

