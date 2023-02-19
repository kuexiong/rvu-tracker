package com.rvutracker.persistence;

import com.rvutracker.entity.ReportStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ReportStatusAttributeConverter implements AttributeConverter<ReportStatus, Integer> {
    /**
     * @param attribute
     * @return in
     */
    @Override
    public Integer convertToDatabaseColumn(ReportStatus attribute) {
        if (attribute == null)
            return null;

        switch (attribute) {
            case INPROGRESS:
                return 1;
            case FINALREVIEW:
                return 2;
            case SIGNED:
                return 3;
            default:
                throw new IllegalArgumentException(attribute + " not supported.");
        }
    }

    /**
     * @param dbData
     * @return
     */
    @Override
    public ReportStatus convertToEntityAttribute(Integer dbData) {
        if (dbData == null)
            return null;

        switch (dbData) {
            case 1:
                return ReportStatus.INPROGRESS;
            case 2:
                return ReportStatus.FINALREVIEW;
            case 3:
                return ReportStatus.SIGNED;
            default:
                throw new IllegalArgumentException(dbData + " not supported.");
        }
    }
}
