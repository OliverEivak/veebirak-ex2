package ee.ttu.olivereivak.webbasedapps.repair.entity.subject;

public enum SubjectType {

    PERSON(1L), ENTERPRISE(2L), EMPLOYEE(3L), CUSTOMER(4L);

    private Long value;

    SubjectType(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    public static SubjectType fromValue(Long value) {
        for (SubjectType subjectType : SubjectType.values()) {
            if (subjectType.getValue().equals(value)) {
                return subjectType;
            }
        }
        throw new IllegalArgumentException("Value " + value + " can not be mapped to a SubjectType");
    }

}
