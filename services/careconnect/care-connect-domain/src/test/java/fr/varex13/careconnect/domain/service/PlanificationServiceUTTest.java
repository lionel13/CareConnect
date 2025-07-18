package fr.varex13.careconnect.domain.service;

import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.varex13.careconnect.domain.model.Creneau;
import fr.varex13.careconnect.domain.model.CreneauDejaPrisException;
import fr.varex13.careconnect.domain.model.Identifiant;
import fr.varex13.careconnect.domain.model.RendezVous;
import fr.varex13.careconnect.domain.port.RendezVousRepository;
import fr.varex13.careconnect.domain.service.impl.PlanificationServiceImpl;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
class PlanificationServiceUTTest {

	@Mock
	private RendezVousRepository rendezVousRepository;

	@InjectMocks
	private PlanificationServiceImpl service;

	@Test
	void QUAND_je_planifie_un_rendez_vous_et_que_le_creneau_est_livre_ALORS_on_sauvegarde_ce_creneau() throws CreneauDejaPrisException {

		//GIVEN
		final Identifiant patientId = new Identifiant(UUID.randomUUID());
		final Identifiant medecinId = new Identifiant(UUID.randomUUID());
		final Creneau creneau = new Creneau(LocalDateTime.now().plusDays(1));

		when(rendezVousRepository.findAllByMedecinIdAndDate(medecinId, creneau)).thenReturn(Collections.emptyList());

		//WHEN
		final RendezVous rendezVous = service.planifier(patientId, medecinId, creneau);

		//THEN
		assertThat(rendezVous.getPatientId(), is(patientId));
		verify(rendezVousRepository, times(1)).save(any(RendezVous.class));
	}

	@Test
	void QUAND_je_planifie_un_rendez_vous_et_que_le_creneau_est_deja_pris_ALORS_on_leve_une_exception() {

		//GIVEN
		final Identifiant medecinId = new Identifiant(UUID.randomUUID());
		final Creneau creneau = new Creneau(LocalDateTime.now().plusDays(1));
		final RendezVous dejaPris = RendezVous.create(new Identifiant(UUID.randomUUID()), medecinId, creneau);

		when(rendezVousRepository.findAllByMedecinIdAndDate(medecinId, creneau)).thenReturn(singletonList(dejaPris));

		//WHEN
		final CreneauDejaPrisException creneauDejaPrisException = assertThrows(
				CreneauDejaPrisException.class,
				() -> service.planifier(new Identifiant(UUID.randomUUID()), medecinId, creneau)
		);

		//THEN
		assertThat(creneauDejaPrisException, notNullValue());
		assertThat(creneauDejaPrisException.getMessage(), is("Créneau déjà réservé"));

	}
}