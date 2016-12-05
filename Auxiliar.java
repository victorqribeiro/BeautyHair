package salao;

import java.util.Scanner;
import java.util.GregorianCalendar;
import java.util.Calendar;

/**
/* Classe Auxiliar - Métodos para auxiliar o funcionamento do programa.
*/

public class Auxiliar {
    
	/** Classe escreve - recebe a frase e a escreve na tela
	/*@param String frase
	*/
	public static void escreve(String frase) {
	  System.out.print(frase);
	}

	/** Classe clear - lipa a tela para que as informações sejam escritas sempre no mesmo lugar
	*/
	public static void clear() {  
		System.out.print("\033[H\033[2J");  
		System.out.flush();  
	}

	/** Classe bpçd - recebe a frase e a escreve na tela em negrito
	/*@param String frase
	*/
	public static void bold(String frase) {  
		String strNormalSize = "\033[0;0m";  
		String strBoldSize = "\033[0;1m";  
		System.out.println ( strBoldSize + frase + strNormalSize );  
	}

	/** Classe data - gera data no formato humano (dia da semana, dia do mês, mês, ano)
	*/
	public static String data(){
		GregorianCalendar d = new GregorianCalendar();
		String[] meses = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
		String[] diasSemana = {"Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado"};
		String diaSemana = diasSemana[d.get(Calendar.DAY_OF_WEEK)-1];
		int diaMes = d.get(Calendar.DAY_OF_MONTH);
		String mes = meses[d.get(Calendar.MONTH)];
		int ano = d.get(Calendar.YEAR);
		
		return diaSemana+", "+diaMes+" de "+mes+" de "+ano;
	}

	/**
	 * Recebe o nome do campo desejado, e pede para o usuário digitar uma string
	 *
	 * @param parametro - o nome do campo desejado
	 * @return var - a String
	 */
	public static String lerString(String parametro) {
	  Scanner entrada = new Scanner(System.in);
	  escreve("Digite " + parametro + ": ");
	  String resultado = entrada.nextLine().trim();
	  return resultado;
	}

	/**
	 * Função que testa se uma String pode ser convertida para um número inteiro
	 * válido
	 *
	 * @param str
	 * @return boolean
	 */
	public static boolean isInt(String str) {
	  try {
      Integer.parseInt(str);
      return true;
	  } catch (NumberFormatException nfe) {
      return false;
	  }
	}

	/**
	 * Recebe o nome do campo desejado, e pede para o usuario digitar um numero
	 * inteiro ate ele digitar um valor valido.
	 *
	 * @param parametro - o nome do campo
	 * @return integer - o numero inteiro valido
	 */
	public static int lerInt(String parametro) {
	  String resultado;
	  Scanner entrada = new Scanner(System.in);
	  do {
      escreve("Digite " + parametro + ": ");
      resultado = entrada.nextLine().trim();
      if (!isInt(resultado)) {
          escreve("DIGITE APENAS NÚMEROS!\n\n");
      }
	  } while (!isInt(resultado));
	  int i = Integer.parseInt(resultado);
	  return i;
	}

	/**
	 * Função que testa se uma String pode ser convertida para um double válido
	 *
	 * @param str
	 * @return boolean
	 */
	public static boolean isDouble(String str) {
	  try {
      Double.parseDouble(str);
      return true;
	  } catch (NumberFormatException nfe) {
      return false;
	  }
	}

	/**
	 * Recebe o nome do campo desejado, e pede para o usuario digitar um double
	 * ate ele digitar um valor valido.
	 *
	 * @param parametro - o nome do campo
	 * @return double - o double valido
	 */
	public static double lerDouble(String parametro) {
	  String resultado;
	  Scanner entrada = new Scanner(System.in);
	  do {
      escreve("Digite " + parametro + ": ");
      resultado = entrada.nextLine().trim();
      if (!isDouble(resultado)) {
          escreve("DIGITE APENAS NÚMEROS!\n\n");
      }
	  } while (!isDouble(resultado));
	  double d = Double.parseDouble(resultado);
	  return d;
	}

	/** Classe ler data - Pede ao usuário que entre uma data no formato dd/mm/aaaa
	/*@param boolean - true se puder ter uma entrada vazia, false se não
	*/
	public static String lerData(boolean vazio){
		Scanner entrada = new Scanner(System.in);
		String resultado = "";
		do{	  
		escreve("Digite a data - (dd/mm/aaaa): ");
	  String data = entrada.nextLine().trim();
		if(data.equals("") && vazio){
			return resultado;
		}else{
			if(data.matches("\\d\\d/\\d\\d/\\d\\d\\d\\d")){
				resultado = data;
				break;
			}else{
				escreve("Formato de data inválido!\n");
			}
		}
		}while(true);
	  return resultado;
	}

	/** Classe ler hora - Pede ao usuário que entre uma hora no formato hh:mm
	/*@param boolean - true se puder ter uma entrada vazia, false se não
	*/
	public static String lerHora(boolean vazio){
		Scanner entrada = new Scanner(System.in);
		String resultado = "";
		do{	  
		escreve("Digite a hora - (hh:mm): ");
	  String hora = entrada.nextLine().trim();
		if(hora.equals("") && vazio){
			return resultado;
		}else{
			if(hora.matches("\\d\\d:\\d\\d")){
				resultado = hora;
				break;
			}else{
				escreve("Formato de hora inválido!\n");
			}
		}
		}while(true);
	  return resultado;
	}

	/** Classe ler email - Pede ao usuário que entre um e-mail no formato nome@servidor.dominio
	/*@param boolean - true se puder ter uma entrada vazia, false se não
	*/
	public static String lerEmail(boolean vazio){
		Scanner entrada = new Scanner(System.in);
		String resultado = "";
		do{	  
		escreve("Digite o e-mail - nome@servidor.com : ");
	  String email = entrada.nextLine().trim();
		if(email.equals("") && vazio){
			return resultado;
		}else{
			if(email.matches("(.*)@(.*).(.*)")){
				resultado = email;
				break;
			}else{
				escreve("Formato de e-mail inválido!\n");
			}
		}
		}while(true);
	  return resultado;
	}

	/** Classe ler telefone - Pede ao usuário que entre um telefone no formato (00)?0000-0000
	/* o ponto de interrogação representa o 5º dígito, caso haja.
	/*@param boolean - true se puder ter uma entrada vazia, false se não
	*/
	public static String lerTelefone(boolean vazio){
		Scanner entrada = new Scanner(System.in);
		String resultado = "";
		do{	  
		escreve("Digite o telefone - (00)?0000-0000: ");
	  String telefone = entrada.nextLine().trim();
		if(telefone.equals("") && vazio){
			return resultado;
		}else{
			if(telefone.matches("\\(\\d\\d\\)\\d\\d\\d\\d-\\d\\d\\d\\d") || telefone.matches("\\(\\d\\d\\)\\d\\d\\d\\d\\d-\\d\\d\\d\\d") ){
				resultado = telefone;
				break;
			}else{
				escreve("Formato de telefone inválido!\n");
			}
		}
		}while(true);
	  return resultado;
	}
}
