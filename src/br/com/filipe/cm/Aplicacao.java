package br.com.filipe.cm;

import br.com.filipe.cm.modelo.Tabuleiro;
import br.com.filipe.cm.visao.TabuleiroConsole;

public class Aplicacao {

	public static void main(String[] args) {
         
		Tabuleiro tabuleiro = new Tabuleiro(6, 6, 6);
		new TabuleiroConsole(tabuleiro);
		
		
	}

}
