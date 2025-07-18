package fr.varex13.careconnect.domain.service.cucumber;


import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.LocalDateTime;
import java.util.UUID;

import fr.varex13.careconnect.domain.model.Creneau;
import fr.varex13.careconnect.domain.model.CreneauDejaPrisException;
import fr.varex13.careconnect.domain.model.Identifiant;
import fr.varex13.careconnect.domain.service.PlanificationService;
import fr.varex13.careconnect.domain.service.impl.PlanificationServiceImpl;
import fr.varex13.careconnect.domain.service.cucumber.stubs.InMemoryRepo;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Etantdonné;
import io.cucumber.java.fr.Quand;

public class PlanificationSteps {

	private final InMemoryRepo repo = new InMemoryRepo();
	private final PlanificationService service = new PlanificationServiceImpl(repo);
	private Creneau creneau;
	private Identifiant medecinId;
	private Exception exception;

	@Etantdonné("un médecin avec un créneau libre demain à 10h")
	public void medecinDisponible() {
		medecinId = new Identifiant(UUID.randomUUID());
		creneau = new Creneau(LocalDateTime.now().plusDays(1).withHour(10).withMinute(0).withSecond(0));
	}

	@Etantdonné("un médecin avec un rendez-vous existant demain à 10h")
	public void medecinOccupe() throws CreneauDejaPrisException {
		medecinDisponible();
		service.planifier(new Identifiant(UUID.randomUUID()), medecinId, creneau);
	}

	@Quand("un patient demande un rendez-vous à ce créneau")
	public void patientDemandeRendezVous() {
		try {
			service.planifier(new Identifiant(UUID.randomUUID()), medecinId, creneau);
		} catch (Exception e) {
			exception = e;
		}
	}

	@Quand("un autre patient tente de prendre ce créneau")
	public void unAutrePatientDemande() {
		patientDemandeRendezVous();
	}

	@Alors("le rendez-vous est planifié avec succès")
	public void rvOk() {
		assertThat(exception, nullValue());
	}

	@Alors("une erreur est levée")
	public void rvErreur() {
		assertThat(exception, notNullValue());
	}
}

