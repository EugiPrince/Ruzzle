package it.polito.tdp.ruzzle.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe che implementa l'algoritmo ricorsivo per il gioco Ruzzle
 * @author eugenioprincipi
 *
 */
public class Ricerca {

	public List<Pos> trovaParola(String parola, Board board) {
		for(Pos p: board.getPositions()) {
			//Per ogni posizione mi chiedo se il carattere in tale posizione sia uguale al primo carattere della parola
			if(board.getCellValueProperty(p).get().charAt(0) == parola.charAt(0)) {
				//Potrebbe essere un potenziale inizio di parola, faccio la ricorsione
				List<Pos> percorso = new ArrayList<>();
				percorso.add(p); //Aggiungo già la prima lettera (posizione) perchè la ricorsione parte a liv 1
				//Se la procedura ricorsiva va a buon fine posso restituire il percorso direttamente
				if(cerca(parola, 1, percorso, board))
					return percorso;
			}
		}
		return null;
	}

	private boolean cerca(String parola, int livello, List<Pos> percorso, Board board) {
		//Caso terminale
		
		if(livello==parola.length())
			return true;
		
		Pos ultima = percorso.get(percorso.size()-1); //Sarebbe l'ultima cosa che ho inserito nella soluzione parziale
		List<Pos> adiacenti = board.getAdjacencies(ultima);
		
		//Prese le adiacenti, prendo le corrette che non siano gia' state utilizzate
		
		for(Pos p: adiacenti) {
			if(!percorso.contains(p) && parola.charAt(livello) == board.getCellValueProperty(p).get().charAt(0)) {
				percorso.add(p);
				if(cerca(parola, livello+1, percorso, board))
					return true; //Uscita rapida in caso di soluzione
				percorso.remove(percorso.size()-1);
			}
		}
		return false;
	}
}
