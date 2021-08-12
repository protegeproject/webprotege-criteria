package edu.stanford.protege.webprotege.criteria;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.google.auto.value.AutoValue;
import java.util.List;

import javax.annotation.Nonnull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2020-04-08
 */
@AutoValue

@JsonTypeName("CompositeCriteria")
public abstract class CompositeHierarchyPositionCriteria implements HierarchyPositionCriteria {

    public static final String MATCH_TYPE = "matchType";

    public static final String CRITERIA = "criteria";

    @JsonCreator
    public static CompositeHierarchyPositionCriteria get(@JsonProperty(CRITERIA) @Nonnull List<? extends HierarchyPositionCriteria> criteria,
                                                         @JsonProperty(MATCH_TYPE) @Nonnull MultiMatchType multiMatchType) {
        return new AutoValue_CompositeHierarchyPositionCriteria(multiMatchType, List.copyOf(criteria));
    }

    public static CompositeHierarchyPositionCriteria get() {
        return get(List.of(),
                   MultiMatchType.ALL);
    }

    @JsonProperty(MATCH_TYPE)
    @Nonnull
    public abstract MultiMatchType getMatchType();

    @JsonProperty(CRITERIA)
    public abstract List<HierarchyPositionCriteria> getCriteria();

    @Override
    public <R> R accept(@Nonnull HierarchyPositionCriteriaVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
