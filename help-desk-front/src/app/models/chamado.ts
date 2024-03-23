export interface Chamado {
	id_chamado?: any;
	dataAbertura?: string;
	dataFechamento?: string;
	prioridade: string;
	status: string;
	titulo: string;
	descricao: string;
	tecnico: any;
	cliente: any;
	nomeCliente: string;
	nomeTecnico: string;
}