import java.util.Scanner;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.time.LocalDate;
import java.time.ZoneId;

public class relogio {

	/****
	Autor: Henrique Denke Mahl
	
	Funcionamento:
		1 - O programa inicializa as variaveis
		2 - O programa inicial o do{...}while
		3 - Usuario informa o horario no formato HH:MM
		4 - O programa através do substring pega o valor de hora e minuto informado para o usuario
		5 - O programa tenta converter os valores de hora e minuto obtidos para inteiros
		6 - Caso falhe o programa ira recomençar o loop
		7 - Caso o horario seja valido (horas < 24 e >= 0 e minutos < 60 e >=0) sai do loop
		8 - É obtido o horario local do sistema
		9 - É inicializado o GregorianCalendar
		10 - É criado uma instacia para executar a função retornaAnguloRelogio
		11 - Na função é calculado e retorna o angulo entre os dois ponteiros
		12 - printa na tela o resultado
		
	****/

    public static void main(String[] args) {
		
		int horaI = 0;
		int minutoI = 0;
		String horario = "";
		
		do{
			if(horaI < 0 || horaI >= 24 || minutoI < 0 || minutoI >= 60){
				System.out.println("Por favor informe um horario validos");
			}
			
			//Pergunta ao usuario qual o horario que ele deseja
			System.out.println("Informe o horario no formato (hh:mm)");
			
			//Captura o input em formato de string hh:mm
			Scanner leitor = new Scanner(System.in);
			horario = leitor.nextLine();
			
			//Separa o horario em hora e minuto
			String hora = horario.substring(0,2);
			String minuto = horario.substring(3);
			
			//Tenta converter de string para int, caso falhe indica que o usuario informou um valor errado para a conversão (Ex: numero negativo)
			try{
			 horaI = Integer.parseInt(hora);
			 minutoI = Integer.parseInt(minuto);
			}catch(NumberFormatException e){
				horaI = -1;
			}
			
		}while(horaI < 0 || horaI >= 24 || minutoI < 0 || minutoI >= 60); //Garante que seja inserido apenas valores validos
		
		//Pega o dia, mes e ano
		Date date = new Date();
		
		//Os valores são locais do sistema
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int ano = localDate.getYear();
		int mes = localDate.getMonthValue();
		int dia = localDate.getYear();
		
		//Cria o GregorianCalendar
		GregorianCalendar data = new GregorianCalendar(ano,mes,dia,horaI,minutoI);
		
		//Inicializado nova instacia para rodar a função retornaAnguloRelogio
		relogio r = new relogio();
		long angulo = r.retornaAnguloRelogio(data);
		
		//Printa o resultado
		System.out.println(horario+" possui um angulo de "+ angulo);
    }
	
	public long retornaAnguloRelogio(GregorianCalendar horario){
		
		//O valor de graus que o ponteiro de minuto se movimenta
		int grauM = 6;
		//O valor de graus que o ponteiro de horas se movimenta
		int grauH = 30;
		
		//Pega os valores de Hora e minuto
		int hora = horario.get(Calendar.HOUR_OF_DAY);
		int minuto = horario.get(Calendar.MINUTE);
		
		//Caso o horario informado passe de 12 horas
		if(hora >= 12){
			hora -= 12;
		}
		
		//Quantidade de graus que o ponteiro de Minutos se mexeu em relação a 00 horas
		int qtdM = minuto*grauM;
		
		//Quantidade de graus que o ponteiro de Horas se mexeu em relação a 00 minutos
		int qtdH = hora*grauH;
		
		long resp = 0;
		
		//Verifica quem tem o maior angulo
		if(qtdM > qtdH){
			resp = qtdM - qtdH;
		}
		if(qtdH > qtdM){
			resp = qtdH - qtdM;
		}
		//Caso os dois tenham o mesmo angulo o resultado sempre ira ser 0
		
		return resp;
	}
}
