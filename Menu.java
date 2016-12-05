package salao;

import recurso.*;
import java.util.List;

/**
/*
*/
public class Menu {

	static String mensagem = null;
	static String nenhumRegistro = "   Nenhum registro encontrado!\n\n";
	static String cadastroSucesso = " cadastrado com sucesso!\n\n";
	static String editadoSucesso = "   Editado com sucesso!\n\n";
	static String excluidoSucesso = " excluido com sucesso!\n\n";
	static String opcaoInvalida = "   Opção inválida!\n\n";
	static String menuOpcoes = "   1 - Agenda\n   2 - Clientes\n"+
															"   3 - Serviços\n   4 - Empregados\n   0 - Sair\n\n";
	static String subMenuOpcoes = "   1 - Cadastrar\n   2 - Consultar\n   0 - Voltar\n\n";
	static String consultaSubMenuOpcoes = "   1 - Visualizar\n   2 - Editar\n"+
																				"   3 - Excluir\n   0 - Voltar\n\n";
	static InterfaceCrud agendas = new InterfaceCrud();
	static InterfaceCrud clientes = new InterfaceCrud();
	static InterfaceCrud servicos = new InterfaceCrud();
	static InterfaceCrud empregados = new InterfaceCrud();

	/** cabecalho - Exibe o título com a data do programa
	/*
	*/
	public static void cabecalho(){
		Auxiliar.clear();
		Arte.titulo();
		Auxiliar.escreve("   "+Auxiliar.data()+"\n\n\n");
	}

	/** principal - Exibe o menu principal
	/*
	*/
	public static void principal(){
		clientes.lerArquivo("Clientes");
		servicos.lerArquivo("Servicos");
		empregados.lerArquivo("Empregados");
		agendas.lerArquivo("Agendas");
		int opcao;
		do{
			Menu.cabecalho();
			Auxiliar.bold("   Menu\n");
			if(mensagem != null){
				Auxiliar.escreve(mensagem);
				mensagem = null;
			}
			Auxiliar.escreve(menuOpcoes);
			opcao = Auxiliar.lerInt("opção");
			switch(opcao){
				case 1 :
						Menu.subMenu("Agenda");
					break;
				case 2 :
						Menu.subMenu("Clientes");
					break;
				case 3 :
						Menu.subMenu("Servicos");
					break;
				case 4 :
						Menu.subMenu("Empregados");
					break;
				case 0 :
						Auxiliar.clear();
						Arte.bye();
					break;
				default :
					mensagem = opcaoInvalida;
			}
		}while(opcao != 0);
	}

	/** subMenu - exibe o subMenu
	/*
	*/
	public static void subMenu(String titulo){
		int opcao;
		String resultado = null;
		do{
			Menu.cabecalho();
			if(titulo.equals("Servicos")){
				Auxiliar.bold("   Serviços\n");
			}else{
				Auxiliar.bold("   "+titulo+"\n");
			}
			if(mensagem != null){
				Auxiliar.escreve(mensagem);
				mensagem = null;
			}
			Auxiliar.escreve(subMenuOpcoes);
			opcao = Auxiliar.lerInt("opção");
			switch(opcao){
				case 1 :
						resultado = Menu.cadastro(titulo);
						if(resultado != null)
							mensagem = resultado;
					break;
				case 2 :
						resultado = Menu.consulta(titulo,0);
						if(resultado != null)
							mensagem = resultado;
					break;
				case 0 :
						Auxiliar.clear();
					break;
				default :
					mensagem = opcaoInvalida;
			}
		}while(opcao != 0);
	}

	/** cadastro - Interage com o usuário na criação de um objeto
	/*@return String
	*/
	public static String cadastro(String titulo){
		switch(titulo){
			case "Agenda" :
					mensagem = Menu.cadastraAgenda();
				break;
			case "Clientes" :
					Cliente c = Cria.criaCliente(clientes.geraCodigo(-1));
					if(clientes.cadastrar( c ) && clientes.escreverArquivo("Clientes")){
						mensagem = "   Cliente código: "+
												c.getCodigo()+" - "+
												c.getNome()+
												cadastroSucesso;
					}
				break;
			case "Servicos" :
					Servico s = Cria.criaServico(servicos.geraCodigo(-1));
					if(servicos.cadastrar( s ) && servicos.escreverArquivo("Servicos")){
						mensagem = "   Serviço código: "+
												s.getCodigo()+" - "+
												s.getTipo()+" - "+
												s.getDetalhe()+
												cadastroSucesso;
					}
				break;
			case "Empregados" :
					Empregado e = Cria.criaEmpregado(empregados.geraCodigo(-1));
					if(empregados.cadastrar( e ) && empregados.escreverArquivo("Empregados")){
						mensagem = "   Empregado código: "+
												e.getCodigo()+" - "+
												e.getNome()+
												cadastroSucesso;
					}
				break;
		}
	return mensagem;
	}

	/** cadastaAgenda - interage com o usuário na criação de uma Agenda
	/*@return String
	*/
	public static String cadastraAgenda(){
		try{
			String cliente = Menu.consulta("Clientes",1);
			Cliente ca = (Cliente)clientes.consultar( Integer.parseInt(cliente) );
			String servico = Menu.consulta("Servicos",1);
			Servico sa = (Servico)servicos.consultar( Integer.parseInt(servico) );
			String empregado = Menu.consulta("Empregados",1);
			Empregado ea = (Empregado)empregados.consultar( Integer.parseInt(empregado) );
			Agenda a = Cria.criaAgenda(agendas.geraCodigo(-1),ca,sa,ea);
			if(agendas.cadastrar(a) && agendas.escreverArquivo("Agendas")){
				mensagem = "   Compromisso código: "+a.getCodigo()+"\n";
			}
		}catch(Exception e){
			mensagem = "   Não encontrado!\n   Cadastre cliente, serviço e empregado primeiro.\n\n";
		}
		return mensagem;
	}

	/** consulta - Retorna um objeto, lista de objetos ou códido, baseado na opcao obj
	/* int obj - 0 = pega o código do objeto e chama o subMenu
	/*           1 = retorna o código do objeto para quem chamou
	/*					 2 = retorna null caso o usuário não digite nada
	/*@return String
	*/
	public static String consulta(String titulo, int obj){
		String console = "sua busca";
		int codigo = 0;
		List<Object> achados;
		switch(titulo){
			case "Clientes" :
					console = "nome do cliente";
				break;
			case "Servicos" :
					console = "tipo de serviço";
				break;
			case "Empregados" :
					console = "nome do empregado";
				break;
		}
		String nome = Auxiliar.lerString(console);
		if(obj == 2 && nome.equals("")){
			return null;
		}
		switch(titulo){
			case "Agenda" :
					achados = agendas.consultarNome(nome);
					if(achados.size() == 0){
						return nenhumRegistro;
					}else if(achados.size() == 1){
						Agenda a = (Agenda)achados.get(0);
						if(obj == 1){
							return a.getCodigo()+"";
						}
						Menu.consultaSubMenu(titulo, a);
					}else{
						Auxiliar.clear();
						Menu.cabecalho();
						Auxiliar.escreve("   "+achados.size()+" resultados encontrados:\n");
						for(Object o : achados){
							Agenda a = (Agenda)o;
							Auxiliar.escreve("   "+a.getCodigo()+" - "+a.getCliente().getNome()+"\n");
						}
						Auxiliar.escreve("\n");
						codigo = Auxiliar.lerInt("código");
						Agenda a = (Agenda)agendas.consultar(codigo);
						if(a != null){
							if(obj == 1){
								return a.getCodigo()+"";
							}
							Menu.consultaSubMenu(titulo, a);
						}else{
							return opcaoInvalida;
						}
					}
				break;
			case "Clientes" :
					achados = clientes.consultarNome(nome);
					if(achados.size() == 0){
						return nenhumRegistro;
					}else if(achados.size() == 1){
						Cliente c = (Cliente)achados.get(0);
						if(obj == 1 || obj == 2){
							return c.getCodigo()+"";
						}
						Menu.consultaSubMenu(titulo, c);
					}else{
						Auxiliar.clear();
						Menu.cabecalho();
						Auxiliar.escreve("   "+achados.size()+" resultados encontrados:\n");
						for(Object o : achados){
							Cliente c = (Cliente)o;
							Auxiliar.escreve("   "+c.getCodigo()+" - "+c.getNome()+"\n");
						}
						Auxiliar.escreve("\n");
						codigo = Auxiliar.lerInt("código");
						Cliente c = (Cliente)clientes.consultar(codigo);
						if(c != null){
							if(obj == 1 || obj == 2){
								return c.getCodigo()+"";
							}
							Menu.consultaSubMenu(titulo, c);
						}else{
							return opcaoInvalida;
						}
					}
				break;
			case "Servicos" :
					achados = servicos.consultarNome(nome);
					if(achados.size() == 0){
						return nenhumRegistro;
					}else if(achados.size() == 1){
						Servico s = (Servico)achados.get(0);
						if(obj == 1 || obj == 2){
							return s.getCodigo()+"";
						}						
						Menu.consultaSubMenu(titulo, s);
					}else{
						Auxiliar.clear();
						Menu.cabecalho();
						Auxiliar.escreve("   "+achados.size()+" resultados encontrados:\n");
						for(Object o : achados){
							Servico s = (Servico)o;
							Auxiliar.escreve("   "+s.getCodigo()+" - "+s.getTipo()+" - "+s.getDetalhe()+"\n");
						}
						Auxiliar.escreve("\n");
						codigo = Auxiliar.lerInt("código");
						Servico s = (Servico)servicos.consultar(codigo);
						if(s != null){
							if(obj == 1 || obj == 2){
								return s.getCodigo()+"";
							}
							Menu.consultaSubMenu(titulo, s);
						}else{
							return opcaoInvalida;
						}
					}
				break;
			case "Empregados" :
					achados = empregados.consultarNome(nome);
					if(achados.size() == 0){
						return nenhumRegistro;
					}else if(achados.size() == 1){
						Empregado e = (Empregado)achados.get(0);
						if(obj == 1 || obj == 2){
							return e.getCodigo()+"";
						}
						Menu.consultaSubMenu(titulo, e);
					}else{
						Auxiliar.clear();
						Menu.cabecalho();
						Auxiliar.escreve("   "+achados.size()+" resultados encontrados:\n");
						for(Object o : achados){
							Empregado e = (Empregado)o;
							Auxiliar.escreve("   "+e.getCodigo()+" - "+e.getNome()+"\n");
						}
						Auxiliar.escreve("\n");
						codigo = Auxiliar.lerInt("código");
						Empregado e = (Empregado)empregados.consultar(codigo);
						if(e != null){
							if(obj == 1 || obj == 2){
								return e.getCodigo()+"";
							}
							Menu.consultaSubMenu(titulo, e);
						}else{
							return opcaoInvalida;
						}
					}
				break;
		}
		return null;
	}

	/** consultaSubMenu - Exibe as opções de consulta de um objeto
	/*@return String
	*/
	public static String consultaSubMenu(String titulo, Object achado){
		int codigo = 0;
		String objeto = "";
		String atributos = "";
		if(titulo.equals("Agenda")){
			Agenda a = (Agenda)achado;
			codigo = a.getCodigo();
			objeto = a.getCodigo()+" - "+a.getCliente().getNome();
			atributos = a.toString();
		}
		if(titulo.equals("Clientes")){
			Cliente c = (Cliente)achado;
			codigo = c.getCodigo();
			objeto = c.getCodigo()+" - "+c.getNome();
			atributos = c.toString();
		}
		if(titulo.equals("Servicos")){
			Servico s = (Servico)achado;
			codigo = s.getCodigo();
			objeto = s.getCodigo()+" - "+s.getTipo()+" - "+s.getDetalhe();
			atributos = s.toString();
		}
		if(titulo.equals("Empregados")){
			Empregado e = (Empregado)achado;
			codigo = e.getCodigo();
			objeto = e.getCodigo()+" - "+e.getNome();
			atributos = e.toString();
		}


		int opcao;
		String resultado = null;
		do{
			Menu.cabecalho();
			Auxiliar.bold("   "+objeto+"\n");
			if(mensagem != null){
				Auxiliar.escreve(mensagem);
				mensagem = null;
			}
			Auxiliar.escreve(consultaSubMenuOpcoes);
			opcao = Auxiliar.lerInt("opção");
			switch(opcao){
				case 1 :
						mensagem = atributos+"\n";
					break;
				case 2 :
						if(titulo.equals("Agenda")){
							Agenda velho = (Agenda)achado;
							mensagem = Menu.editaAgenda(velho);
							opcao = 0;
						}
						if(titulo.equals("Clientes")){
							Cliente velho = (Cliente)achado;
							Cliente novo = Cria.criaClienteEditado(velho);
							if(clientes.editar(velho.getCodigo(), novo) && 
								clientes.escreverArquivo("Clientes")){
								mensagem = "   Codigo: "+velho.getCodigo()+editadoSucesso;
								opcao = 0;
							}
						}
						if(titulo.equals("Servicos")){
							Servico velho = (Servico)achado;
							Servico novo = Cria.criaServicoEditado(velho);
							if(servicos.editar(velho.getCodigo(), novo) && 
								servicos.escreverArquivo("Servicos")){
								mensagem = "   Codigo: "+velho.getCodigo()+editadoSucesso;
								opcao = 0;
							}
						}
						if(titulo.equals("Empregados")){
							Empregado velho = (Empregado)achado;
							Empregado novo = Cria.criaEmpregadoEditado(velho);
							if(empregados.editar(velho.getCodigo(), novo) && 
								empregados.escreverArquivo("Empregados")){
								mensagem = "   Codigo: "+velho.getCodigo()+editadoSucesso;
								opcao = 0;
							}
						}
					break;
				case 3 :
						if(titulo.equals("Agenda")){
							if(agendas.excluir(codigo) && agendas.escreverArquivo("Agendas")){
								mensagem = "   "+objeto+" "+excluidoSucesso;
								opcao = 0;
							}
						}
						if(titulo.equals("Clientes")){
							if(clientes.excluir(codigo) && clientes.escreverArquivo("Clientes")){
								mensagem = "   "+objeto+" "+excluidoSucesso;
								opcao = 0;
							}
						}
						if(titulo.equals("Servicos")){
							if(servicos.excluir(codigo) && servicos.escreverArquivo("Servicos")){
								mensagem = "   "+objeto+" "+excluidoSucesso;
								opcao = 0;
							}
						}
						if(titulo.equals("Empregados")){
							if(empregados.excluir(codigo) && empregados.escreverArquivo("Clientes")){
								mensagem = "   "+objeto+" "+excluidoSucesso;
								opcao = 0;
							}
						}
					break;
				case 0 :
						Auxiliar.clear();
					break;
				default :
					mensagem = opcaoInvalida;
			}
		}while(opcao != 0);
		return mensagem;
	}

	/** editaAgebda - Interage com usuário na edição de uma agenda
	/*@return String
	*/
	public static String editaAgenda(Agenda velho){
		Cliente ca = velho.getCliente();
		Servico sa = velho.getServico();
		Empregado ea = velho.getEmpregado();
		String cliente = Menu.consulta("Clientes",2);
		if(cliente != null){
			try{
				ca = (Cliente)clientes.consultar( Integer.parseInt(cliente) );
			}catch(Exception e){
				return mensagem = "   Cliente não cadastrado\n\n";
			}
		}
		String servico = Menu.consulta("Servicos",2);
		if(servico != null){
			try{
				sa = (Servico)servicos.consultar( Integer.parseInt(servico) );
			}catch(Exception e){
				return mensagem = "   Serviço não cadastrado\n\n";
			}
		}
		String empregado = Menu.consulta("Empregados",2);
		if(empregado != null){
			try{
				ea = (Empregado)empregados.consultar( Integer.parseInt(empregado) );
			}catch(Exception e){
				return mensagem = "   Empregado não cadastrado\n\n";
			}
		}
		Agenda a = Cria.criaAgendaEditado(velho,ca,sa,ea);
		if(agendas.editar(velho.getCodigo(), a) && agendas.escreverArquivo("Agendas")){
			mensagem = editadoSucesso;
		}
		return mensagem;
	}

}
