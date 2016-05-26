package ee.ttu.olivereivak.webbasedapps.repair.entity.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import ee.ttu.olivereivak.webbasedapps.repair.entity.SubjectType;

@Converter
public class SubjectTypeConverter implements AttributeConverter<SubjectType, Long> {

    public Long convertToDatabaseColumn(SubjectType value) {
        if (value == null) {
            return null;
        }

        return value.getValue();
    }

    public SubjectType convertToEntityAttribute(Long value) {
        if (value == null) {
            return null;
        }

        return SubjectType.fromValue(value);
    }
}
