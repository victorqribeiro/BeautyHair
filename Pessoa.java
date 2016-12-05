package recurso;

/** Classe Cliente
/*
*/

public class Pessoa {

	private int codigo;
	private String nome;
	private String email;
	private String telefone;
	private String endereco;

	/** Construtor
	/* @param - int - codigo
	*/
	public Pessoa(int codigo){
		this.codigo = codigo;
	}

	/** Set Nome
	/* @param - String nome
	*/
	public void setNome(String nome){
		this.nome = nome;
	}

	/** Set e-mail
	/* @param - String email
	*/
	public void setEmail(String email){
		this.email = email;
	}

	/** Set Telefone
	/* @param - String telefone
	*/
	public void setTelefone(String telefone){
		this.telefone = telefone;
	}

	/** Set Endereço
	/* @param - String endereco
	*/
	public void setEndereco(String endereco){
		this.endereco = endereco;
	}

	/** Get Cógido
	/* @return - int
	*/
	public int getCodigo(){
		return this.codigo;
	}

	/** Get Nome
	/* @return - String
	*/
	public String getNome(){
		return this.nome;
	}

	/** Get e-mail
	/* @return - String
	*/
	public String getEmail(){
		return this.email;
	}

	public String getTelefone(){
		return this.telefone;
	}

	/** Get Endereço
	/* @return - String
	*/
	public String getEndereco(){
		return this.endereco;
	}

	/** Método que sobreescreve o toString() retorando todos os atributos do objeto com identificador e valor.
	/* @return - String
	*/
	public String toString(){
		return "   Codigo: "+this.getCodigo()+"\n   Nome: "+this.getNome()+"\n   E-mail: "+this.getEmail()+"\n   Telefone: "+this.getTelefone()+"\n   Endereço: "+this.getEndereco()+"\n";
	}

	/** Método que gera uma String pronta para ser gravada como CSV usando ";" como separador de colunas e "\n" como separador de linhas
	/* @return - String
	*/
	public String toFile(){
		return this.getCodigo()+";"+this.getNome()+";"+this.getEmail()+";"+this.getTelefone()+";"+this.getEndereco()+";";
	}

}
