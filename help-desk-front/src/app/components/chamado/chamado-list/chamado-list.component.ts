import { Component, OnInit } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { RouterLink } from '@angular/router';
import { Chamado } from '../../../models/chamado';
import { MatCardModule } from '@angular/material/card';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { FormsModule } from '@angular/forms';
import { MatRadioModule } from '@angular/material/radio';

@Component({
  selector: 'app-chamado-list',
  standalone: true,
  imports: [
    MatTableModule, 
    MatPaginatorModule, 
    MatFormFieldModule, 
    MatInputModule,
    MatIconModule,
    MatDividerModule,
    MatButtonModule,
    RouterLink,
    MatCardModule, 
    MatCheckboxModule, 
    FormsModule, 
    MatRadioModule],
  templateUrl: './chamado-list.component.html',
  styleUrl: './chamado-list.component.css'
})
export class ChamadoListComponent implements OnInit {

  constructor(){
  }

  ngOnInit(): void {   
  }

  ELEMENT_DATA: Chamado[] = [{
    id_chamado: 1,
    dataAbertura: '20/03/2024',
    dataFechamento: '20/03/2024',
    prioridade: 'ALTA',
    status: 'ANDAMENTO',
    titulo: 'Chamado Teste',
    observacoes: 'Teste',
    id_tecnico: 1,
    id_cliente: 2,
    cliente: 'Juan Silva',
    tecnico: 'Jonas'
  }]

  displayedColumns: string[] = ['id_chamado', 'titulo', 'cliente', 'tecnico', 'dataAbertura', 'prioridade', 'status', 'acoes'];
  dataSource = new MatTableDataSource<Chamado>(this.ELEMENT_DATA);

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

}
