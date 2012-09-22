package uk.co.iankent.Asserted.Web.Concordion;

import org.concordion.Concordion;
import org.concordion.api.ResultSummary;
import org.concordion.integration.junit4.ConcordionRunner;
import org.concordion.internal.ConcordionBuilder;
import uk.co.iankent.Asserted.AssertedTest;

import java.io.IOError;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Ian
 * Date: 20/09/12
 * Time: 19:10
 * To change this template use File | Settings | File Templates.
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
