package fr.varex13.careconnect.domain.service.cucumber.stubs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import fr.varex13.careconnect.domain.model.Creneau;
import fr.varex13.careconnect.domain.model.Identifiant;
import fr.varex13.careconnect.domain.model.RendezVous;
import fr.varex13.careconnect.domain.port.RendezVousRepository;

public class InMemoryRepo implements RendezVousRepository {

	private final Map<Identifiant, RendezVous> data = new HashMap<>();

	public Optional<RendezVous> findById(Identifiant rendezVousid) {
		return Optional.ofNullable(data.get(rendezVousid));
	}

	public List<RendezVous> findAllByMedecinIdAndDate(Identifiant medecinId, Creneau date) {
		return data.values().stream()
				.filter(rendezVous -> rendezVous.getMedecinId().equals(medecinId)
						&& rendezVous.getCreneau().equals(date))
				.toList();
	}

	public void save(RendezVous rendezVous) {
		data.put(rendezVous.getRendezVousid(), rendezVous);
	}
}