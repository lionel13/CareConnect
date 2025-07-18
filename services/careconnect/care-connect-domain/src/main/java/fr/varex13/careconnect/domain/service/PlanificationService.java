package fr.varex13.careconnect.domain.service;

import fr.varex13.careconnect.domain.model.Creneau;
import fr.varex13.careconnect.domain.model.CreneauDejaPrisException;
import fr.varex13.careconnect.domain.model.Identifiant;
import fr.varex13.careconnect.domain.model.RendezVous;

public interface PlanificationService {

	RendezVous planifier(Identifiant patientId, Identifiant medecinId, Creneau creneau) throws CreneauDejaPrisException;

}
