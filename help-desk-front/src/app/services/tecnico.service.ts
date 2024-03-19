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
} 
