package fr.varex13.careconnect.domain.service.impl;

import java.util.List;

import fr.varex13.careconnect.domain.model.Creneau;
import fr.varex13.careconnect.domain.model.CreneauDejaPrisException;
import fr.varex13.careconnect.domain.model.Identifiant;
import fr.varex13.careconnect.domain.model.RendezVous;
import fr.varex13.careconnect.domain.port.RendezVousRepository;
import fr.varex13.careconnect.domain.service.PlanificationService;

public class PlanificationServiceImpl implements PlanificationService {

	private final RendezVousRepository repository;

	public PlanificationServiceImpl(final RendezVousRepository repository) {
		this.repository = repository;
	}

	public RendezVous planifier(final Identifiant patientId, final Identifiant medecinId, final Creneau creneau) throws CreneauDejaPrisException {
		final List<RendezVous> rendezVousByMedecinIdAndCreneau = repository.findAllByMedecinIdAndDate(medecinId, creneau);
		if (creneau.isDejaPris(rendezVousByMedecinIdAndCreneau)) {
			throw new CreneauDejaPrisException();
		}

		final RendezVous rendezVousToCreate = RendezVous.create(patientId, medecinId, creneau);
		repository.save(rendezVousToCreate);
		return rendezVousToCreate;
	}

}
