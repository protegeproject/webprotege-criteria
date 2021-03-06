package edu.stanford.protege.webprotege.criteria;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.google.auto.value.AutoValue;
import java.util.List;

import javax.annotation.Nonnull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 14 Jun 2018
 */
@AutoValue

@JsonTypeName("CompositeCriteria")
public abstract class CompositeRootCriteria implements RootCriteria, EntityMatchCriteria {

    private static final String MATCH_TYPE = "matchType";

    private static final String CRITERIA = "criteria";

    @JsonProperty(CRITERIA)
    @Nonnull
    public abstract List<? extends RootCriteria> getRootCriteria();

    @JsonIgnore
    protected abstract int getMultiMatchTypeOrdinal();

    @JsonProperty(MATCH_TYPE)
    @Nonnull
    public MultiMatchType getMatchType() {
        return MultiMatchType.values()[getMultiMatchTypeOrdinal()];
    }


    @JsonCreator
    public static CompositeRootCriteria get(@Nonnull @JsonProperty(CRITERIA) List<? extends RootCriteria> rootCriteria,
                                            @Nonnull @JsonProperty(MATCH_TYPE) MultiMatchType matchType) {
        return new AutoValue_CompositeRootCriteria(rootCriteria, matchType.ordinal());
    }

    @Override
    public <R> R accept(RootCriteriaVisitor<R> visitor) {
        return visitor.visit(this);
    }

    @Override
    public CompositeRootCriteria asCompositeRootCriteria() {
        return this;
    }
}
