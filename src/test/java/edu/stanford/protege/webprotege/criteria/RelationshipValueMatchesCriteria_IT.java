package edu.stanford.protege.webprotege.criteria;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import edu.stanford.protege.webprotege.match.JsonSerializationTestUtil;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2019-12-04
 */
@JsonTest
public class RelationshipValueMatchesCriteria_IT {

    @Autowired
    private ObjectMapper objectMapper;

    private RelationshipValueMatchesCriteria criteria = RelationshipValueMatchesCriteria.get(
            CompositeRootCriteria.get(ImmutableList.of(EntityIsDeprecatedCriteria.get()),
                                      MultiMatchType.ALL)
    );

    @Test
    public void shouldSerialize_AnyRelationshipPropertyCriteria() throws IOException {
        testSerialization(criteria);
    }

    @Test
    public void shouldSerialize_AnyRelationshipPropertyCriteriaWithEntityCriteria() throws IOException {
        var ser = "{\"match\":\"ValueMatches\",\"matchCriteria\": {\"match\":\"EntityIsDeprecated\"}}";
        var read = objectMapper.readValue(ser, RelationshipValueCriteria.class);
        MatcherAssert.assertThat(read, Matchers.is(criteria));
    }

    private static <V extends RelationshipValueCriteria> void testSerialization(V value) throws IOException {
        JsonSerializationTestUtil.testSerialization(value, RelationshipValueCriteria.class);
    }
}
