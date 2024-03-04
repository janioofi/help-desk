import { Component, ViewChild } from '@angular/core';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { Tecnico } from '../../../models/tecnico';

@Component({
  selector: 'app-tecnico-list',
  standalone: true,
  imports: [MatTableModule, MatPaginatorModule],
  templateUrl: './tecnico-list.component.html',
  styleUrl: './tecnico-list.component.css'
})
export class TecnicoListComponent {

  ELEMENT_DATA: Tecnico[] = [
    {
      id: 1,
      nome: 'Jânio Filho',
      cpf: '523152678821',
      email: 'janio@gmail.com',
      senha: '1235',
      perfis: ['0'],
      dataCriacao: '04/03'
    }
  ]

  displayedColumns: string[] = ['id', 'nome', 'cpf', 'email', 'acoes'];
  dataSource = new MatTableDataSource<Tecnico>(this.ELEMENT_DATA);

  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }
}