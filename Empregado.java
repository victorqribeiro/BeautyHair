package recurso;

/** Classe Empregado
/*
*/

public class Empregado extends Pessoa {

	private int comissao;

	/** Construtor
	/* @param - int - codigo
	*/
	public Empregado(int codigo){
		super(codigo);
	}

	/** Set Comissão
	/* @param - int comissao
	*/
	public void setComissao(int comissao){
		this.comissao = comissao;
	}

	/** Get Comissao
	/* @return - int
	*/
	public int getComissao(){
		return this.comissao;
	}


	/** Método que sobreescrever superclasse gerando uma String pronta para ser gravada como CSV usando ";" como separador de colunas e "\n" como separador de linhas
	/* @return - String
	*/
	@Override
	public String toString(){
		return super.toString()+"   Comissão: "+this.getComissao()+"% \n";
	}

	@Override
	public String toFile(){
		return super.toFile()+this.getComissao()+"\n";
	}

}
