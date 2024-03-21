import { routes } from './../../../app.routes';
import { Component, OnInit } from "@angular/core";
import { MatCheckboxModule } from "@angular/material/checkbox";
import { MatIconModule } from "@angular/material/icon";
import { MatInputModule } from "@angular/material/input";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatButtonModule } from "@angular/material/button";
import { Router, RouterLink } from "@angular/router";
import {
    FormControl,
    FormsModule,
    Validators,
    ReactiveFormsModule,
} from "@angular/forms";
import { NgxMaskDirective, provideNgxMask } from "ngx-mask";
import { TecnicoService } from "../../../services/tecnico.service";
import { Tecnico } from "../../../models/tecnico";
import { ToastrService } from "ngx-toastr";

@Component({
    selector: "app-tecnico-create",
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
    templateUrl: "./tecnico-create.component.html",
    styleUrl: "./tecnico-create.component.css",
})
export class TecnicoCreateComponent implements OnInit {
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
        private router: Router
    ) {}

    ngOnInit(): void {}

    addPerfil(perfil: any): void{
      
      if(this.tecnico.perfis.includes(perfil)){
        this.tecnico.perfis.splice(this.tecnico.perfis.indexOf(perfil), 1)
      }else{
        this.tecnico.perfis.push(perfil)
      }

      console.log(this.tecnico.perfis)
    }
    
    create(): void {
      this.service.create(this.tecnico).subscribe(
        () => {
          this.toastr.success("Técnico cadastrado com sucesso", "Cadastro");
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
              this.cpf.valid &&
              this.email.valid &&
              this.senha.valid
          );
    }
}
