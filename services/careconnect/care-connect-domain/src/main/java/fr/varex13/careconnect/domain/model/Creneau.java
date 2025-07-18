package fr.varex13.careconnect.domain.model;

import java.time.LocalDateTime;
import java.util.List;

public record Creneau(LocalDateTime creneau) {

	public boolean isDejaPris(final List<RendezVous> rendezVousList) {
		return rendezVousList.stream()
				.anyMatch(rendezVous -> rendezVous.getCreneau().equals(this));
	}
}