package org.andsav.family_budget_manager.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

@ContextConfiguration({"classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@ActiveProfiles("postgres")
public abstract class AbstractServiceTest {

    private static final Log LOG = LogFactory.getLog(AbstractServiceTest.class);
    private static StringBuilder results = new StringBuilder();

    @Rule
    public Stopwatch stopwatch = new Stopwatch() {

        @Override
        protected void finished(long nanos, Description description) {
            String result = String.format("%-25s %7d", description.getMethodName(),
                    TimeUnit.NANOSECONDS.toMillis(nanos));
            results.append(result).append('\n');
            LOG.info(result + " ms\n");
        }
    };

    @AfterClass
    public static void printResults() {
        results = new StringBuilder("\n---------------------------------")
                .append("\nTest                 Duration, ms")
                .append("\n---------------------------------\n").append(results)
                .append("---------------------------------\n");
        LOG.info(results.toString());
        results.setLength(0);
    }
}
