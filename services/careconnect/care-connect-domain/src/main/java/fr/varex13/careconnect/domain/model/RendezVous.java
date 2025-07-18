package fr.varex13.careconnect.domain.model;

import java.util.UUID;

public class RendezVous {

	private final Identifiant rendezVousid;
	private final Identifiant patientId;
	private final Identifiant medecinId;
	private final Creneau creneau;

	public static RendezVous create(final Identifiant patientId, final Identifiant medecinId, final Creneau creneau) {
		return builder()
				.rendezVousid(new Identifiant(UUID.randomUUID()))
				.patientId(patientId)
				.medecinId(medecinId)
				.creneau(creneau)
				.build();
	}

	private RendezVous(final RendezVousBuilder rendezVousBuilder) {
		this.rendezVousid = rendezVousBuilder.rendezVousid;
		this.patientId = rendezVousBuilder.patientId;
		this.medecinId = rendezVousBuilder.medecinId;
		this.creneau = rendezVousBuilder.creneau;
	}

	public static RendezVousBuilder builder() {
		return new RendezVousBuilder();
	}

	public static class RendezVousBuilder {
		private Identifiant rendezVousid;
		private Identifiant patientId;
		private Identifiant medecinId;
		private Creneau creneau;


		public RendezVousBuilder rendezVousid(final Identifiant rendezVousid) {
			this.rendezVousid = rendezVousid;
			return this;
		}

		public RendezVousBuilder patientId(final Identifiant patientId) {
			this.patientId = patientId;
			return this;
		}

		public RendezVousBuilder medecinId(final Identifiant medecinId) {
			this.medecinId = medecinId;
			return this;
		}

		public RendezVousBuilder creneau(final Creneau creneau) {
			this.creneau = creneau;
			return this;
		}

		public RendezVous build() {
			return new RendezVous(this);
		}
	}

	public Identifiant getRendezVousid() {
		return rendezVousid;
	}

	public Identifiant getPatientId() {
		return patientId;
	}

	public Identifiant getMedecinId() {
		return medecinId;
	}

	public Creneau getCreneau() {
		return creneau;
	}

}
