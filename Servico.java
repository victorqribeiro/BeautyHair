package recurso;

import java.text.DecimalFormat;

/** Classe Serviço
/*
*/

public class Servico {

	private int codigo;
	private String tipo;
	private String detalhe;
	private double valor;

	/** Construtor
	/* @param - int - codigo
	*/
	public Servico(int codigo){
		this.codigo = codigo;
	}

	/** Set Tipo
	/* @param - String tipo
	*/
	public void setTipo(String tipo){
		this.tipo = tipo;
	}

	/** Set Detalhe
	/* @param - String detalhe
	*/
	public void setDetalhe(String detalhe){
		this.detalhe = detalhe;
	}

	/** Set Valor
	/* @param - double valor
	*/
	public void setValor(double valor){
		this.valor = valor;
	}

	/** Get Cógido
	/* @return - int
	*/
	public int getCodigo(){
		return this.codigo;
	}

	/** Get Tipo
	/* @return - String
	*/
	public String getTipo(){
		return this.tipo;
	}

	/** Get Detalhe
	/* @return - String
	*/
	public String getDetalhe(){
		return this.detalhe;
	}

	/** Get Valor
	/* @return - double
	*/
	public double getValor(){
		return this.valor;
	}

	/** Método que sobreescreve o toString() retorando todos os atributos do objeto com identificador e valor.
	/* @return - String
	*/
	public String toString(){
		DecimalFormat df = new DecimalFormat("#.00");
		return "   Codigo: "+this.getCodigo()+"\n   Tipo: "+this.getTipo()+"\n   Detalhe: "+this.getDetalhe()+"\n   Valor: R$ "+df.format(this.getValor())+"\n";
	}

	/** Método que gera uma String pronta para ser gravada como CSV usando ";" como separador de colunas e "\n" como separador de linhas
	/* @return - String
	*/
	public String toFile(){
		return this.getCodigo()+";"+this.getTipo()+";"+this.getDetalhe()+";"+this.getValor()+"\n";
	}

}
