import { Component, OnInit } from '@angular/core';
import { FormControl, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { Router, RouterLink } from '@angular/router';
import { Cliente } from '../../../models/cliente';
import { Tecnico } from '../../../models/tecnico';
import { ClienteService } from '../../../services/cliente.service';
import { TecnicoService } from '../../../services/tecnico.service';
import { ChamadoService } from '../../../services/chamado.service';
import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import { Chamado } from '../../../models/chamado';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-chamado-create',
  standalone: true,
  imports: [
    FormsModule, 
    MatFormFieldModule, 
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    MatDividerModule,
    MatIconModule,
    RouterLink,
    ReactiveFormsModule,
    CommonModule],
  templateUrl: './chamado-create.component.html',
  styleUrl: './chamado-create.component.css'
})
export class ChamadoCreateComponent implements OnInit{

  chamado: Chamado = {
    id_chamado: '',
    prioridade: '',
    status: '',
    titulo: '',
    observacoes: '',
    id_tecnico: '',
    id_cliente: '',
    cliente: '',
    tecnico: ''
  }

  clientes: Cliente[] = [];
  tecnicos: Tecnico[] = [];

  prioridade: FormControl = new FormControl(null, Validators.required)
  status: FormControl = new FormControl(null, Validators.required)
  titulo: FormControl = new FormControl(null, Validators.required)
  observacoes: FormControl = new FormControl(null, Validators.required)
  tecnico: FormControl = new FormControl(null, Validators.required)
  cliente: FormControl = new FormControl(null, Validators.required)

  constructor(
    private chamadoService: ChamadoService,
    private clienteService: ClienteService, 
    private toastr: ToastrService,
    private tecnicoService: TecnicoService,
    private router: Router)
  {}

  ngOnInit(): void {
    this.findAllClientes();
    this.findAllTecnicos();
  }
  
  create(): void{
    this.chamadoService.crete(this.chamado).subscribe(reponse => {
      this.toastr.success("Chamado criado com sucesso", "Criação")
      this.router.navigate(['chamados'])
    }, ex => {
      this.toastr.error(ex.error.error)
    })
  }

  findAllClientes(): void{
    this.clienteService.findAll().subscribe(response => {
      this.clientes = response;
    })
  }
  
  findAllTecnicos(): void{
    this.tecnicoService.findAll().subscribe(response => {
      this.tecnicos = response;
    })
  }

  validaCampos(): boolean{
    return this.prioridade.valid && this.status.valid && this.titulo.valid && this.observacoes.valid && this.tecnico.valid && this.cliente.valid;
  }
  
}
