import { Component, OnInit } from '@angular/core';
import { FormControl, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { NgxMaskDirective, provideNgxMask } from 'ngx-mask';
import { Tecnico } from '../../../models/tecnico';
import { TecnicoService } from '../../../services/tecnico.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-tecnico-update',
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
  templateUrl: './tecnico-update.component.html',
  styleUrl: './tecnico-update.component.css'
})
export class TecnicoUpdateComponent implements OnInit {
  tecnico: Tecnico = {
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
    private service: TecnicoService,
    private toastr: ToastrService,
    private router: Router,
    private route: ActivatedRoute
) {}

ngOnInit(): void {
  this.tecnico.id_pessoa = this.route.snapshot.paramMap.get('id')
  this.findById();
}

addPerfil(perfil: any): void{
  
  if(this.tecnico.perfis.includes(perfil)){
    this.tecnico.perfis.splice(this.tecnico.perfis.indexOf(perfil), 1)
  }else{
    this.tecnico.perfis.push(perfil)
  }

  console.log(this.tecnico.perfis)
}

findById(){
  this.service.findById(this.tecnico.id_pessoa).subscribe(response => {
    response.perfis = [];
    this.tecnico = response;
  })
}

update(): void {
  this.service.update(this.tecnico).subscribe(
    () => {
      this.toastr.success("Técnico atualizado com sucesso", "Atualização");
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

validaCampos(): boolean {
      return (
          this.nome.valid &&
          this.email.valid &&
          this.senha.valid
      );
}
}
