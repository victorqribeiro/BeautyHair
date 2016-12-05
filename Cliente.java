package recurso;

/** Classe Cliente
/*
*/

public class Cliente extends Pessoa {

	private int desconto;

	/** Construtor
	/* @param - int - codigo
	*/
	public Cliente(int codigo){
		super(codigo);
	}

	/** Set Desconto
	/* @param - int desconto
	*/
	public void setDesconto(int desconto){
		this.desconto = desconto;
	}

	/** Get Desconto
	/* @return - int
	*/
	public int getDesconto(){
		return this.desconto;
	}

	/** Método que sobreescreve o toString() da superclasse, retorando todos os atributos do objeto com identificador e valor.
	/* @return - String
	*/
	@Override
	public String toString(){
		return super.toString()+"   Desconto: "+this.getDesconto()+"% \n";
	}

	/** Método que sobreescrever superclasse gerando uma String pronta para ser gravada como CSV usando ";" como separador de colunas e "\n" como separador de linhas
	/* @return - String
	*/
	@Override
	public String toFile(){
		return super.toFile()+this.getDesconto()+"\n";
	}

}
