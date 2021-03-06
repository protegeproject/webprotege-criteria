package edu.stanford.protege.webprotege.criteria;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.google.auto.value.AutoValue;
import java.util.List;
import org.semanticweb.owlapi.model.OWLEntity;

import javax.annotation.Nonnull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2019-12-09
 */
@AutoValue

@JsonTypeName("EntityIs")
public abstract class EntityIsCriteria implements EntityMatchCriteria {

    @JsonCreator
    public static EntityIsCriteria get(@Nonnull @JsonProperty("term") OWLEntity entity) {
        return new AutoValue_EntityIsCriteria(entity);
    }

    @Nonnull
    public abstract OWLEntity getEntity();

    @Override
    public <R> R accept(RootCriteriaVisitor<R> visitor) {
        return visitor.visit(this);
    }

    @Override
    public CompositeRootCriteria asCompositeRootCriteria() {
        return CompositeRootCriteria.get(List.of(this), MultiMatchType.ALL);
    }
}
