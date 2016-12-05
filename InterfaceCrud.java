package salao;

import recurso.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

/** Classe que implementa a interface Crud
/*
*/
class InterfaceCrud implements Crud {

	private List<Object> lista = new ArrayList<>();

	/** geraCodigo - gera um código baseado na opção passada por parâmetro
	/*@param int opcao - (-1) gera um código novo; (n) gera um código n - (-n) gera um código inválido
	*/
	@Override
	public int geraCodigo(int opcao){
		if(opcao == -1){
			if(this.lista.size() > 0){
				if(lista.get(0) instanceof Cliente){
					Cliente c = (Cliente)lista.get(lista.size()-1);
					return c.getCodigo()+1;
				}
				if(lista.get(0) instanceof Empregado){
					Empregado e = (Empregado)lista.get(lista.size()-1);
					return e.getCodigo()+1;
				}
				if(lista.get(0) instanceof Agenda){
					Agenda a = (Agenda)lista.get(lista.size()-1);
					return a.getCodigo()+1;
				}
				if(lista.get(0) instanceof Servico){
					Servico s = (Servico)lista.get(lista.size()-1);
					return s.getCodigo()+1;
				}
			}else{
				return 0;
			}
		}
		return opcao;
	}

	/** cadastrar - Recebe um objeto e o insere na lista
	/*@param Object obj
	*/
	@Override
	public boolean cadastrar(Object obj){
		try{
			lista.add(obj);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	/** consutar - recebe um código e retorna o objeto com esse código
	/*@param int codigo
	*/
	@Override
	public Object consultar(int codigo){
		for(Object o : lista){
			if(o instanceof Cliente){
				Cliente c = (Cliente)o;
				if(c.getCodigo() == codigo){
					return c;
				}
			}
			if(o instanceof Empregado){
				Empregado e = (Empregado)o;
				if(e.getCodigo() == codigo){
					return e;
				}
			}
			if(o instanceof Agenda){
				Agenda a = (Agenda)o;
				if(a.getCodigo() == codigo){
					return a;
				}
			}
			if(o instanceof Servico){
				Servico s = (Servico)o;
				if(s.getCodigo() == codigo){
					return s;
				}
			}
		}
		return null;
	}

	/** consultarNome - recebe um nomer e retorna uma lista com objetos com esses nomes
	/*@param String nome
	*/
	@Override
	public List<Object> consultarNome(String nome){
		List<Object> achados = new ArrayList<>();
			for(Object o : lista){
			if(o instanceof Pessoa){
				Pessoa p = (Pessoa)o;
				if(p.getNome().toLowerCase().contains( nome.toLowerCase() )){
					achados.add( p );
				}
			}
			if(o instanceof Agenda){
				Agenda a = (Agenda)o;
				if(a.getCliente().getNome().toLowerCase().contains( nome.toLowerCase() ) ||
					a.getEmpregado().getNome().toLowerCase().contains( nome.toLowerCase() ) || 
					a.getServico().getTipo().toLowerCase().contains( nome.toLowerCase() ) || 
					a.getServico().getDetalhe().toLowerCase().contains( nome.toLowerCase() ) || 
					a.getData().contains( nome ) || 
					a.getHora().contains( nome ) ){
					achados.add( a );
				}
			}
			if(o instanceof Servico){
				Servico s = (Servico)o;
				if(s.getTipo().toLowerCase().contains( nome.toLowerCase() ) || s.getDetalhe().toLowerCase().contains( nome.toLowerCase() )){
					achados.add( s );
				}
			}
			}
		return achados;
	}

	/** editar - Recebe um codigo e um objeto, subistitui esse objeto pelo objeto com o código
	/*@param int codigo
	/*@param Object obj
	*/
	@Override
	public boolean editar(int codigo, Object obj){
		for(int i = 0; i < lista.size(); i++){
			if(lista.get(i) instanceof Pessoa){
				Pessoa p = (Pessoa)lista.get(i);
				if(p.getCodigo() == codigo){
					lista.set(i, obj);
					return true;
				}
			}
			if(lista.get(i) instanceof Agenda){
				Agenda a = (Agenda)lista.get(i);
				if(a.getCodigo() == codigo){
					lista.set(i, obj);
					return true;
				}
			}
			if(lista.get(i) instanceof Servico){
				Servico s = (Servico)lista.get(i);
				if(s.getCodigo() == codigo){
					lista.set(i, obj);
					return true;
				}
			}
		}
		return false;
	}

	/** excluir - recebe um código e exculi o objeto com esse código
	/*@param int codigo
	*/
	@Override
	public boolean excluir(int codigo){
		for(Object o : lista){
			if(o instanceof Pessoa){
				Pessoa p = (Pessoa)o;
				if(p.getCodigo() == codigo){
					lista.remove(p);
					return true;
				}
			}
			if(o instanceof Agenda){
				Agenda a = (Agenda)o;
				if(a.getCodigo() == codigo){
					lista.remove(a);
					return true;
				}
			}
			if(o instanceof Servico){
				Servico s = (Servico)o;
				if(s.getCodigo() == codigo){
					lista.remove(s);
					return true;
				}
			}
		}
		return false;
	}

	/** lerArquivo - receber um nome de arquivo e grava um arquivo csv com esse nome
	/*@param String nome
	*/
	@Override
	public boolean lerArquivo(String arquivo) {
		BufferedReader buff;
    String linha;

		try {

		  buff = new BufferedReader(new InputStreamReader(new FileInputStream("dados/"+arquivo+".csv"), "UTF-8"));

		  linha = buff.readLine();
		  while (linha != null) {
		    String[] atributos = linha.split(";");
		    int codigofile = Integer.parseInt(atributos[0]);
				if(arquivo.equals("Agendas")){
					Cliente c = (Cliente)Menu.clientes.consultar( Integer.parseInt(atributos[1]) );
					Servico s = (Servico)Menu.servicos.consultar( Integer.parseInt(atributos[2]) );
					Empregado e = (Empregado)Menu.empregados.consultar ( Integer.parseInt(atributos[3]) );
					Agenda a = new Agenda(codigofile, c, s, e);
					a.setData( atributos[4] );
					a.setHora( atributos[5] );
					this.cadastrar( a );
				}
				if(arquivo.equals("Clientes")){
					Cliente c = new Cliente(codigofile);
					c.setNome( atributos[1] );
					c.setEmail( atributos[2] );
					c.setTelefone( atributos[3] );
					c.setEndereco( atributos[4] );
					c.setDesconto( Integer.parseInt(atributos[5]) );
					this.cadastrar( c );
				}
				if(arquivo.equals("Empregados")){
					Empregado e = new Empregado(codigofile);
					e.setNome( atributos[1] );
					e.setEmail( atributos[2] );
					e.setTelefone( atributos[3] );
					e.setEndereco( atributos[4] );
					e.setComissao( Integer.parseInt(atributos[5]) );
					this.cadastrar( e );
				}
				if(arquivo.equals("Servicos")){
					Servico s = new Servico(codigofile);
					s.setTipo( atributos[1] );
					s.setDetalhe( atributos[2] );
					s.setValor( Double.parseDouble(atributos[3]) );
					this.cadastrar( s );
				}

		    linha = buff.readLine();

		  }

		  buff.close();

		  return true;
		} catch (Exception e) {
		  return false;
		}
	}

	/** escreverArquivo - recebe um nome e lê um arquivo csv com esse nome
	/*@param String arquivo
	*/
	@Override
	public boolean escreverArquivo(String arquivo){
		FileOutputStream outFile;
		BufferedWriter buff;

		try {
			File f = new File("dados");
			if(!f.exists()){
				try{
		      f.mkdir();
    		} 
				catch(SecurityException se){
				}   
			}
			outFile = new FileOutputStream(new File("dados/"+arquivo+".csv"));
			buff = new BufferedWriter(new OutputStreamWriter(outFile, "UTF-8"));
		
			for (Object o : lista) {
				if(arquivo.equals("Clientes")){
					Cliente c = (Cliente)o;
					buff.write(c.toFile());
				}
				if(arquivo.equals("Empregados")){
					Empregado e = (Empregado)o;
					buff.write(e.toFile());
				}
				if(arquivo.equals("Servicos")){
					Servico s = (Servico)o;
					buff.write(s.toFile());
				}
				if(arquivo.equals("Agendas")){
					Agenda a = (Agenda)o;
					buff.write(a.toFile());
				}
			}

			buff.close();
			outFile.close();

			return true;

		} catch (Exception e) {
				return false;
		}

	}

	/** tamanho - retorna quantos objetos existem na lista
	/*
	*/
	@Override
	public int tamanho(){
		return lista.size();
	}

	/** listar - lista todos os objetos de uma lista por páginas, dez elementos por página
	/*
	@Override
	public void listar(){
		int ini = 0;
		int fim = 10;
		do{
			if(fim > lista.size()){
				fim = lista.size();
			}
			for(int i = ini; i < fim; i++){
				System.out.print(); //escrever resumo do objeto
			}
			System.out.println("");
			ini+=10;
			fim+=10;
		}while(ini < lista.size());
	}
	*/
}
