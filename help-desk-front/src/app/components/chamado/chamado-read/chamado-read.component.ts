import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { ChamadoService } from '../../../services/chamado.service';
import { ClienteService } from '../../../services/cliente.service';
import { ToastrService } from 'ngx-toastr';
import { TecnicoService } from '../../../services/tecnico.service';
import { Cliente } from '../../../models/cliente';
import { Tecnico } from '../../../models/tecnico';
import { Chamado } from '../../../models/chamado';

@Component({
  selector: 'app-chamado-read',
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
  templateUrl: './chamado-read.component.html',
  styleUrl: './chamado-read.component.css'
})
export class ChamadoReadComponent {
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
  }

  findById(): void{
    this.chamadoService.findById(this.chamado.id_chamado).subscribe(response => {
      this.chamado = response;
    }, ex => {
      this.toastr.error(ex.error.error)
    })
  }
}
