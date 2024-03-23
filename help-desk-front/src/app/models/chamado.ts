export interface Chamado {
	id_chamado?: any;
	dataAbertura?: string;
	dataFechamento?: string;
	prioridade: string;
	status: string;
	titulo: string;
	observacoes: string;
	id_tecnico: any;
	id_cliente: any;
	cliente: string;
	tecnico: string;
}