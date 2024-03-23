import { Component } from '@angular/core';
import { FormControl, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { NgxMaskDirective, provideNgxMask } from 'ngx-mask';
import { TecnicoService } from '../../../services/tecnico.service';
import { ToastrService } from 'ngx-toastr';
import { Tecnico } from '../../../models/tecnico';

@Component({
  selector: 'app-tecnico-delete',
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
  templateUrl: './tecnico-delete.component.html',
  styleUrl: './tecnico-delete.component.css'
})
export class TecnicoDeleteComponent {
  tecnico: Tecnico = {
    id_pessoa: "",
    nome: "",
    cpf: "",
    email: "",
    senha: "",
    perfis: [],
    dataCriacao: "",
  };

  constructor(
      private service: TecnicoService,
      private toastr: ToastrService,
      private router: Router,
      private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.tecnico.id_pessoa = this.route.snapshot.paramMap.get('id')
    this.findById();
  }

  findById(){
    this.service.findById(this.tecnico.id_pessoa).subscribe(response => {
      response.perfis = [];
      this.tecnico = response;
    })
  }

  delete(): void {
    this.service.delete(this.tecnico.id_pessoa).subscribe(
      () => {
        this.toastr.success("Técnico deletado com sucesso", "Deletado");
        this.router.navigate(['tecnicos'])
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

