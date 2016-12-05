package recurso;

import java.text.DecimalFormat;

/** Classe agenda
/*
*/

public class Agenda {

	private int codigo;
	private Cliente cliente;
	private Empregado empregado;
	private Servico servico;
	private String data;
	private String hora;

	/** Construtor
	/* @param - int - codigo
	/* @param - Cliente cliente
	/* @param - Servico - servico
	/* @param - Emregado - empregado
	*/
	public Agenda(int codigo, Cliente cliente, Servico servico, Empregado empregado){
		this.codigo = codigo;
		this.cliente = cliente;
		this.servico = servico;
		this.empregado = empregado;
	}	

	/** Set Cliente
	/* @param - Cliente cliente
	*/
	public void setCliente(Cliente cliente){
		this.cliente = cliente;
	}

	/** Set Empregado
	/* @param - Empregado empregado
	*/
	public void setEmpregado(Empregado empregado){
		this.empregado = empregado;
	}

	/** Set Servico
	/* @param - Servico servico
	*/
	public void setServico(Servico servico){
		this.servico = servico;
	}

	/** Set data
	/* @param - String data
	*/
	public void setData(String data){
		this.data = data;
	}

	/** Set hora
	/* @param - String hora
	*/
	public void setHora(String hora){
		this.hora = hora;
	}

	/** Get Código
	/* @return - int
	*/
	public int getCodigo(){
		return this.codigo;
	}

	/** Get Cliente
	/* @return - Cliente
	*/
	public Cliente getCliente(){
		return this.cliente;
	}

	/** Get Empregado
	/* @return - Empregado
	*/
	public Empregado getEmpregado(){
		return this.empregado;
	}


	/** Get Servico
	/* @return - Servico
	*/
	public Servico getServico(){
		return this.servico;
	}

	/** Get data
	/* @return - String
	*/
	public String getData(){
		return this.data;
	}

	/** Get hora
	/* @return - String
	*/
	public String getHora(){
		return this.hora;
	}

	/** Método que sobreescreve o toString() retorando todos os atributos do objeto com identificador e valor.
	/* @return - String
	*/
	public String toString(){
		DecimalFormat df = new DecimalFormat("#.00"); 
		double valor = this.getServico().getValor();
		int desconto = this.getCliente().getDesconto();
		double valorCliente = (valor-((valor*desconto)/100));
		int comissao = this.getEmpregado().getComissao();
		double valorEmpregado = valorCliente-(valorCliente-(valorCliente*comissao)/100);
		return "   Codigo: "+this.getCodigo()+
				"\n   Cliente: "+this.getCliente().getNome()+
				"\n   Serviço: "+this.getServico().getTipo()+" - "+this.getServico().getDetalhe()+
				"\n   Empregado: "+this.getEmpregado().getNome()+
				"\n   Data: "+this.getData()+
				"\n   Hora: "+this.getHora()+
				"\n   Valor do serviço: R$ "+df.format(valor)+
				"\n   Valor com desconto: R$ "+df.format(valorCliente)+
				"\n   Comissao à pagar: R$ "+df.format(valorEmpregado)+
				"\n";
	}

	/** Método que gera uma String pronta para ser gravada como CSV usando ";" como separador de colunas e "\n" como separador de linhas
	/* @return - String
	*/
	public String toFile(){
		return this.getCodigo()+";"+this.getCliente().getCodigo()+";"+this.getServico().getCodigo()+";"+this.getEmpregado().getCodigo()+";"+this.getData()+";"+this.getHora()+"\n";
	}


}
