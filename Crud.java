package salao;

import java.util.List;

/** Interface Crud
/*
*/
interface Crud {

	/** geraCodigo - gera um código baseado na opção passada por parâmetro
	/*@param int opcao - (-1) gera um código novo; (n) gera um código n - (-n) gera um código inválido
	*/
	public int geraCodigo(int opcao);

	/** cadastrar - Recebe um objeto e o insere na lista
	/*@param Object obj
	*/
	public boolean cadastrar(Object obj);

	/** consutar - recebe um código e retorna o objeto com esse código
	/*@param int codigo
	*/
	public Object consultar(int codigo);

	/** consultarNome - recebe um nomer e retorna uma lista com objetos com esses nomes
	/*@param String nome
	*/
	public List<Object> consultarNome(String nome);

	/** editar - Recebe um codigo e um objeto, subistitui esse objeto pelo objeto com o código
	/*@param int codigo
	/*@param Object obj
	*/
	public boolean editar(int codigo, Object obj);

	/** excluir - recebe um código e exculi o objeto com esse código
	/*@param int codigo
	*/
	public boolean excluir(int codigo);

	/** lerArquivo - receber um nome de arquivo e grava um arquivo csv com esse nome
	/*@param String nome
	*/
	public boolean lerArquivo(String arquivo);

	/** escreverArquivo - recebe um nome e lê um arquivo csv com esse nome
	/*@param String arquivo
	*/
	public boolean escreverArquivo(String arquivo);

	/** tamanho - retorna quantos objetos existem na lista
	/*
	*/
	public int tamanho();

	/** listar - lista todos os objetos de uma lista por páginas, dez elementos por página
	/*
	*/
	//public void listar();

}
