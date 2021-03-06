package edu.stanford.protege.webprotege.criteria;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.google.auto.value.AutoValue;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2019-12-02
 */
@AutoValue

@JsonTypeName("ValueMatches")
public abstract class RelationshipValueMatchesCriteria implements RelationshipValueCriteria {

    @Nonnull
    public static RelationshipValueMatchesCriteria get(@Nonnull @JsonProperty("criteria") CompositeRootCriteria criteria) {
        return new AutoValue_RelationshipValueMatchesCriteria(criteria);
    }

    /**
     * Support earlier serializations that just used to use an {@link EntityMatchCriteria}.
     */
    @Nonnull
    @JsonCreator
    protected static RelationshipValueMatchesCriteria get(
            @Nullable @JsonProperty("criteria") CompositeRootCriteria criteria,
            @Nullable @JsonProperty("matchCriteria") EntityMatchCriteria entityMatchCriteria) {
        if(entityMatchCriteria != null) {
            return new AutoValue_RelationshipValueMatchesCriteria(CompositeRootCriteria.get(List.of(entityMatchCriteria), MultiMatchType.ALL));
        }
        else {
            return new AutoValue_RelationshipValueMatchesCriteria(criteria);
        }
    }

    @JsonProperty("criteria")
    @Nonnull
    public abstract CompositeRootCriteria getMatchCriteria();

    @Override
    public <R> R accept(@Nonnull RelationshipValueCriteriaVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
