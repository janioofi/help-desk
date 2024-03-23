import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild, inject } from '@angular/core';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { Cliente } from '../../../models/cliente';
import { ClienteService } from '../../../services/cliente.service';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatIconModule} from '@angular/material/icon';
import {MatDividerModule} from '@angular/material/divider';
import {MatButtonModule} from '@angular/material/button';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-cliente-list',
  standalone: true,
  imports: [
    MatTableModule, 
    MatPaginatorModule, 
    MatFormFieldModule, 
    MatInputModule,
    MatIconModule,
    MatDividerModule,
    MatButtonModule,
    RouterLink],
  templateUrl: './cliente-list.component.html',
  styleUrl: './cliente-list.component.css'
})
export class ClienteListComponent implements OnInit{
  httpClient = inject(HttpClient)

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private service: ClienteService){}

  ELEMENT_DATA: Cliente[] = []

  displayedColumns: string[] = ['id_pessoa', 'nome', 'cpf', 'email', 'acoes'];
  dataSource = new MatTableDataSource<Cliente>(this.ELEMENT_DATA);


  ngOnInit(): void {
   this.findAll();
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  findAll(){
    this.service.findAll().pipe().subscribe(response => {
      this.ELEMENT_DATA = response;
      this.dataSource = new MatTableDataSource<Cliente>(response)
      this.dataSource.paginator = this.paginator;
    });
  }
}