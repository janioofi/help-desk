import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { API_CONFIG } from '../config/api.config';
import { Observable } from 'rxjs';
import { Cliente } from '../models/cliente';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  url: string = `${ API_CONFIG.baseUrl }/clientes`;

  constructor(private http: HttpClient) { }

  findAll(): Observable<Cliente[]>{
    return this.http.get<Cliente[]>(`${ API_CONFIG.baseUrl }/clientes`);
  }

  create(cliente: Cliente): Observable<Cliente>{
    return this.http.post<Cliente>(`${ API_CONFIG.baseUrl }/clientes`, cliente);
  }

  findById(id: any): Observable<Cliente>{
    return this.http.get<Cliente>(`${ API_CONFIG.baseUrl }/clientes/${id}`);
  }

  update(cliente: Cliente): Observable<Cliente>{
    return this.http.put<Cliente>(`${ API_CONFIG.baseUrl }/clientes/${cliente.id_pessoa}`, cliente);
  }

  delete(id: any): Observable<Cliente>{
    return this.http.delete<Cliente>(`${ API_CONFIG.baseUrl }/clientes/${id}`);
  }
} 
