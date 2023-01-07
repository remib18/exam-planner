package fr.univtours.examplanner.enums;

/**
 * Type d'examen (continu ou partiel)
 */
public enum ExamType {
    Continuous ("Continuous"),
    Final("Final");

    private final String type;

    private ExamType(String type){
        this.type = type;
    }

    public String getTypeString() {
        return type;
    }

    public static ExamType setTypeByString( String type ){
        switch ( type ){
            case "Continuous" :
                return ExamType.Continuous;
            case "Final" :
                return ExamType.Final;
        }
        throw new UnsupportedOperationException("Should be unreachable");
    }
}

