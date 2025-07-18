package fr.varex13.careconnect.domain.model;

public class CreneauDejaPrisException extends Exception {
	public CreneauDejaPrisException() {
		super("Créneau déjà réservé");
	}
}
