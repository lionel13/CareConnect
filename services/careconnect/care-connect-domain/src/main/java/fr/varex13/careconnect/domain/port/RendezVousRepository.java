package fr.varex13.careconnect.domain.port;

import java.util.List;
import java.util.Optional;

import fr.varex13.careconnect.domain.model.Creneau;
import fr.varex13.careconnect.domain.model.Identifiant;
import fr.varex13.careconnect.domain.model.RendezVous;

public interface RendezVousRepository {

	Optional<RendezVous> findById(Identifiant rendezVousid);

	List<RendezVous> findAllByMedecinIdAndDate(Identifiant medecinId, Creneau date);

	void save(RendezVous rendezVous);
}
