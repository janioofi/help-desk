import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { API_CONFIG } from '../config/api.config';
import { Observable } from 'rxjs';
import { Tecnico } from '../models/tecnico';

@Injectable({
  providedIn: 'root'
})
export class TecnicoService {

  url: string = `${ API_CONFIG.baseUrl }/tecnicos`;

  constructor(private http: HttpClient) { }

  findAll(): Observable<Tecnico[]>{
    return this.http.get<Tecnico[]>(`${ API_CONFIG.baseUrl }/tecnicos`);
  }

  create(tecnico: Tecnico): Observable<Tecnico>{
    return this.http.post<Tecnico>(`${ API_CONFIG.baseUrl }/tecnicos`, tecnico);
  }

  findById(id: any): Observable<Tecnico>{
    return this.http.get<Tecnico>(`${ API_CONFIG.baseUrl }/tecnicos/${id}`);
  }

  update(tecnico: Tecnico): Observable<Tecnico>{
    return this.http.put<Tecnico>(`${ API_CONFIG.baseUrl }/tecnicos/${tecnico.id_pessoa}`, tecnico);
  }

  delete(id: any): Observable<Tecnico>{
    return this.http.delete<Tecnico>(`${ API_CONFIG.baseUrl }/tecnicos/${id}`);
  }
} 
