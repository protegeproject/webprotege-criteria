package edu.stanford.protege.webprotege.criteria;

import com.fasterxml.jackson.annotation.*;
import com.google.auto.value.AutoValue;

import javax.annotation.Nonnull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 17 Jun 2018
 */
@AutoValue
@JsonTypeName("ContextSensitiveSubClassOf")
public abstract class ContextSensitiveSubClassOfCriteria implements EntityMatchCriteria {

    private static final String FILTER_TYPE = "filterType";

    @JsonProperty(FILTER_TYPE)
    public abstract HierarchyFilterType getFilterType();

    @JsonCreator
    @Nonnull
    public static ContextSensitiveSubClassOfCriteria get(@Nonnull @JsonProperty(FILTER_TYPE) HierarchyFilterType filterType) {
        return new AutoValue_ContextSensitiveSubClassOfCriteria(filterType);
    }

    @Override
    public <R> R accept(RootCriteriaVisitor<R> visitor) {
        return visitor.visit(this);
    }

}
