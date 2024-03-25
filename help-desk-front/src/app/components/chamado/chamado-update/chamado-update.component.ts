import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { Cliente } from '../../../models/cliente';
import { Tecnico } from '../../../models/tecnico';
import { ChamadoService } from '../../../services/chamado.service';
import { ClienteService } from '../../../services/cliente.service';
import { ToastrService } from 'ngx-toastr';
import { TecnicoService } from '../../../services/tecnico.service';
import { Chamado } from '../../../models/chamado';

@Component({
  selector: 'app-chamado-update',
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
  templateUrl: './chamado-update.component.html',
  styleUrl: './chamado-update.component.css'
})
export class ChamadoUpdateComponent {
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
    private router: Router,
    private route: ActivatedRoute)
  {}

  ngOnInit(): void {
    this.chamado.id_chamado = this.route.snapshot.paramMap.get('id');
    this.findById();
    this.findAllClientes();
    this.findAllTecnicos();
  }

  findById(): void{
    this.chamadoService.findById(this.chamado.id_chamado).subscribe(response => {
      this.chamado = response;
    }, ex => {
      this.toastr.error(ex.error.error)
    })
  }
  
  update(): void{
    this.chamadoService.update(this.chamado).subscribe(reponse => {
      this.toastr.success("Chamado atualizado com sucesso", "Atualização")
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
