package br.com.filipe.cm.visao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import br.com.filipe.cm.execao.ExplosaoException;
import br.com.filipe.cm.execao.SairException;
import br.com.filipe.cm.modelo.Tabuleiro;

public class TabuleiroConsole {
     
	private Tabuleiro tabuleiro;
	private Scanner entrada = new Scanner(System.in);

	public TabuleiroConsole(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		
		executarJogo();
	}

	private void executarJogo() {
		try {
			boolean continuar = true;
			
			while(continuar) {
				cicloDoJogo();
				
				System.out.print("Outra Partida ? (S/n)");
				String resposta = entrada.nextLine();
				
				if("n".equalsIgnoreCase(resposta) ) {
					continuar = false;
				} else {
					tabuleiro.reiniciar();
				}
;			}
			
		}catch(SairException e) {
			System.out.println("Tchau !!!");
		} finally {
			entrada.close();
		}
	}

	private void cicloDoJogo() {
		try {
			
			while(!tabuleiro.objetivoAlcansaco()) {
				System.out.print(tabuleiro);
				
				String digitado = capiturarValorDigitado("Digite (x, y): ");
				
				Iterator<Integer> xy = Arrays.stream(digitado.split(","))
						.map(e -> Integer.parseInt(e.trim())).iterator();
				
				digitado = capiturarValorDigitado("! - Abrir ou 2 - Marcar/Desmarcar");
				
				if("1".equals(digitado)) {
					tabuleiro.abrir(xy.next(), xy.next());
				}else if ("2".equals(digitado)) {
					tabuleiro.alternarMarcacao(xy.next(), xy.next());
				}
				
			}
			
			System.out.print(tabuleiro);
			System.out.print("Voce Ganhou !!");

		} catch(ExplosaoException e) {
			System.out.print(tabuleiro);
			System.out.print("Voce Perdeu !!");
		}
	}
	
	private String capiturarValorDigitado(String texto) {
		System.out.print(texto);
		String digitado = entrada.nextLine();
		
		if("sair".equalsIgnoreCase(digitado)) {
			throw new SairException();
		}
		
		return digitado;
	}
}
