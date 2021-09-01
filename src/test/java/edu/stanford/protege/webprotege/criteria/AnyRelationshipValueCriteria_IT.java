package edu.stanford.protege.webprotege.criteria;

import com.fasterxml.jackson.annotation.JsonTypeName;
import edu.stanford.protege.webprotege.match.JsonSerializationTestUtil;
import org.junit.Test;

import java.io.IOException;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2019-12-04
 */
@JsonTypeName("AnyValue")
public class AnyRelationshipValueCriteria_IT {

    @Test
    public void shouldSerialize_AnyRelationshipPropertyCriteria() throws IOException {
        testSerialization(AnyRelationshipValueCriteria.get());
    }

    private static <V extends RelationshipValueCriteria> void testSerialization(V value) throws IOException {
        JsonSerializationTestUtil.testSerialization(value, RelationshipValueCriteria.class);
    }
}
