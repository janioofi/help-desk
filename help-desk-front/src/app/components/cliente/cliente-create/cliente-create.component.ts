import { routes } from '../../../app.routes';
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
import { ClienteService } from "../../../services/cliente.service";
import { Cliente } from "../../../models/cliente";
import { ToastrService } from "ngx-toastr";

@Component({
    selector: "app-cliente-create",
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
    templateUrl: "./cliente-create.component.html",
    styleUrl: "./cliente-create.component.css",
})
export class ClienteCreateComponent implements OnInit {
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
        private router: Router
    ) {}

    ngOnInit(): void {}

    addPerfil(perfil: any): void{
      
      if(this.cliente.perfis.includes(perfil)){
        this.cliente.perfis.splice(this.cliente.perfis.indexOf(perfil), 1)
      }else{
        this.cliente.perfis.push(perfil)
      }

      console.log(this.cliente.perfis)
    }
    
    create(): void {
      this.service.create(this.cliente).subscribe(
        () => {
          this.toastr.success("Cliente cadastrado com sucesso", "Cadastro");
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
              this.cpf.valid &&
              this.email.valid &&
              this.senha.valid
          );
    }
}
