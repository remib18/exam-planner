package fr.univtours.examplanner.entities.dtos;

import fr.univtours.examplanner.entities.Exam;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FinalExamDTO extends Exam {


	/**
	 * Examen
	 *
	 * @param id            Identifiant de l'examen dans la base de donnée, si null l'examen n'est pas encore enregistré
	 * @param name          Nom de l'examen
	 * @param groups        Groupes d'étudiants participant à l'examen
	 * @param managers      Surveillants de l'examen
	 * @param subject       Sujet de l'examen
	 * @param duration      Durée de l'examen en heures
	 * @param previousExams Précédence de l'examen
	 */
	public FinalExamDTO(
			@Nullable String id,
			@NotNull String name,
			@NotNull List<GroupDTO> groups,
			@NotNull List<ManagerDTO> managers,
			@NotNull SubjectDTO subject,
			float duration,
			@NotNull List<Exam> previousExams
	) {
		super(id, name, groups, managers, subject, duration, previousExams);
	}
}