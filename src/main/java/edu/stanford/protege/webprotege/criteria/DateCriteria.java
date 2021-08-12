package edu.stanford.protege.webprotege.criteria;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 18 Jun 2018
 */
public interface DateCriteria extends LexicalValueCriteria {

    String YEAR = "year";

    String MONTH = "month";

    String DAY = "day";

    @JsonProperty(YEAR)
    int getYear();

    @JsonProperty(MONTH)
    int getMonth();

    @JsonProperty(DAY)
    int getDay();

    static void checkArgs(int year, int month, int day) {
        if(!(month >= 1 && month <= 12)) {
            throw new IllegalArgumentException("Month must be 1-12");
        }
        if(!(day >= 1 && day <= 31)) {
            throw new IllegalArgumentException("Day must be 1-31");
        }
    }
}
