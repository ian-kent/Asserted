package uk.co.iankent.Asserted.Web.Concordion;

import org.concordion.Concordion;
import org.concordion.api.ResultSummary;
import org.concordion.integration.junit4.ConcordionRunner;
import org.concordion.internal.ConcordionBuilder;
import uk.co.iankent.Asserted.AssertedTest;

import java.io.IOError;
import java.io.IOException;

/**
 * Asserted - A JUnit/Concordion/Selenium integration framework for automated web testing
 * Copyright (c) Ian Kent, 2012
 */
public class ConcordionAssertedTest extends AssertedTest {

    @Override
    public void test() throws IOException {
        Concordion concordion = new ConcordionBuilder().build();
        ResultSummary resultSummary = concordion.process(this);

        logger.info(
                String.format(
                        "Successes[%s], Failures[%s], Exceptions[%s], Ignored[%s]",
                        resultSummary.getSuccessCount(),
                        resultSummary.getFailureCount(),
                        resultSummary.getExceptionCount(),
                        resultSummary.getIgnoredCount()
                )
        );

        resultSummary.assertIsSatisfied(this);
    }

}
