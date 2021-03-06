package edu.stanford.protege.webprotege.criteria;

import com.google.auto.value.AutoValue;
import java.util.List;

import javax.annotation.Nonnull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2020-06-18
 */
@AutoValue

public abstract class CompositeLiteralCriteria implements LiteralCriteria {

    public static CompositeLiteralCriteria get(@Nonnull List<? extends LiteralCriteria> criteria,
                                               @Nonnull MultiMatchType matchType) {
        return new AutoValue_CompositeLiteralCriteria(List.copyOf(criteria), matchType);
    }

    @Nonnull
    public abstract List<LiteralCriteria> getCriteria();

    @Nonnull
    public abstract MultiMatchType getMultiMatchType();

    @Override
    public <R> R accept(@Nonnull LiteralCriteriaVisitor<R> visitor) {
        return visitor.visit(this);
    }

    @Override
    public <R> R accept(@Nonnull AnnotationValueCriteriaVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
