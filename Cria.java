package salao;

import recurso.*;

/** Classe Cria
/*
*/

class Cria {

	/** Classe criaPessoa - classe genérica que recebe uma pessoa e seta seus atributos
	/*@param Pessoa pessoa
	*/
	public static void criaPessoa(Pessoa pessoa){
		pessoa.setNome(Auxiliar.lerString("nome"));
		pessoa.setTelefone(Auxiliar.lerTelefone(false));
		pessoa.setEmail(Auxiliar.lerEmail(false));
		pessoa.setEndereco(Auxiliar.lerString("endereço"));	
	}

	/** Classe criaCliente - classe genérica que cria um cliente e seta seus atributos
	/*@param int codigo
	*/
	public static Cliente criaCliente(int codigo){
		Cliente c = new Cliente(codigo);
		criaPessoa(c);
		c.setDesconto(Auxiliar.lerInt("desconto"));
		return c;
	}

	/** Classe criaEmpregado - classe genérica que cria um empregado e seta seus atributos
	/*@param int codigo
	*/
	public static Empregado criaEmpregado(int codigo){
		Empregado e = new Empregado(codigo);
		criaPessoa(e);
		e.setComissao(Auxiliar.lerInt("comissão"));
		return e;
	}

	/** Classe criaServico - classe genérica que cria um serviço e seta seus atributos
	/*@param int codigo
	*/
	public static Servico criaServico(int codigo){
		Servico s = new Servico(codigo);
		s.setTipo(Auxiliar.lerString("tipo"));
		s.setDetalhe(Auxiliar.lerString("detalhe"));
		s.setValor(Auxiliar.lerDouble("valor"));
		return s;
	}

	/** Classe criaAgenda - classe genérica que cria uma agenda e seta seus atributos
	/*@param int codigo
	/*@param Cliente c
	/*@param Servico s
	/*@param Empregado e
	*/
	public static Agenda criaAgenda(int codigo, Cliente c, Servico s, Empregado e){
		Agenda a = new Agenda(codigo, c, s, e);
		a.setData(Auxiliar.lerData(false));
		a.setHora(Auxiliar.lerHora(false));
		return a;
	}

	/** Classe criaClienteEditado - classe genérica que cria um cliente e seta seus atributos
	/*@param Cliente velho
	*/
	public static Cliente criaClienteEditado(Cliente velho){
		Cliente novo = new Cliente(velho.getCodigo());
		String nome = Auxiliar.lerString("nome");
		if(nome.equals(""))
			nome = velho.getNome();
		novo.setNome(nome);
		String email = Auxiliar.lerEmail(true);
		if(email.equals(""))
			email = velho.getEmail();
		novo.setEmail(email);
		String telefone = Auxiliar.lerTelefone(true);
		if(telefone.equals(""))
			telefone = velho.getTelefone();
		novo.setTelefone(telefone);
		String endereco = Auxiliar.lerString("endereço");
		if(endereco.equals("")){
			endereco = velho.getEndereco();
		}
		novo.setEndereco(endereco);
		int desconto = 0;
		do{
			String d = Auxiliar.lerString("desconto");
			if(d.equals("")){
				desconto = velho.getDesconto();
				break;
			}else{
				try{
					desconto = Integer.parseInt(d);
					break;
				}catch(Exception e){
					Auxiliar.escreve("Apenas números!\n");
				}
			}
		}while(true);
		novo.setDesconto(desconto);
		return novo;
	}

	/** Classe criaEmpregadoEditado - classe genérica que cria um empregado e seta seus atributos
	/*@param Empregado velho
	*/
	public static Empregado criaEmpregadoEditado(Empregado velho){
		Empregado novo = new Empregado(velho.getCodigo());
		String nome = Auxiliar.lerString("nome");
		if(nome.equals(""))
			nome = velho.getNome();
		novo.setNome(nome);
		String email = Auxiliar.lerEmail(true);
		if(email.equals(""))
			email = velho.getEmail();
		novo.setEmail(email);
		String telefone = Auxiliar.lerTelefone(true);
		if(telefone.equals(""))
			telefone = velho.getTelefone();
		novo.setTelefone(telefone);
		String endereco = Auxiliar.lerString("endereço");
		if(endereco.equals("")){
			endereco = velho.getEndereco();
		}
		novo.setEndereco(endereco);
		int comissao = 0;
		do{
			String c = Auxiliar.lerString("comissão");
			if(c.equals("")){
				comissao = velho.getComissao();
				break;
			}else{
				try{
					comissao = Integer.parseInt(c);
					break;
				}catch(Exception e){
					Auxiliar.escreve("Apenas números!\n");
				}
			}
		}while(true);
		novo.setComissao(comissao);
		return novo;
	}

	/** Classe criaServicoEditado - classe genérica que cria um serviço e seta seus atributos
	/*@param Servico velho
	*/
	public static Servico criaServicoEditado(Servico velho){
		Servico novo = new Servico(velho.getCodigo());
		String tipo = Auxiliar.lerString("tipo");
		if(tipo.equals(""))
			tipo = velho.getTipo();
		novo.setTipo(tipo);
		String detalhe = Auxiliar.lerString("detalhe");
		if(detalhe.equals(""))
			detalhe = velho.getDetalhe();
		novo.setDetalhe(detalhe);
		double valor = 0;
		do{
			String v = Auxiliar.lerString("valor");
			if(v.equals("")){
				valor = velho.getValor();
				break;
			}else{
				try{
					valor = Double.parseDouble(v);
					break;
				}catch(Exception e){
					Auxiliar.escreve("Apenas números!\n");
				}
			}
		}while(true);
		novo.setValor(valor);
		novo.setValor(valor);
		return novo;
	}

	/** Classe criaAgenda - classe genérica que cria uma agenda e seta seus atributos
	/*@param int codigo
	/*@param Cliente c
	/*@param Servico s
	/*@param Empregado e
	*/
	public static Agenda criaAgendaEditado(Agenda velho, Cliente c, Servico s, Empregado e){
		Agenda novo = new Agenda(velho.getCodigo(), c, s, e);
		String data = Auxiliar.lerData(true);
		if(data.equals(""))
			data = velho.getData();
		novo.setData(data);
		String hora = Auxiliar.lerHora(true);
		if(hora.equals(""))
			hora = velho.getHora();
		novo.setHora(hora);

		return novo;
	}

}
