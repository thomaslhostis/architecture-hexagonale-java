package com.architecturehexagonale.tests.functionaltests;

import com.architecturehexagonale.ArchitectureHexagonaleJava;
import com.architecturehexagonale.tests.functionaltests.configuration.TestContextCleaner;
import com.architecturehexagonale.tests.functionaltests.configuration.TestDatabaseCleaner;
import io.cucumber.java.Before;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        plugin = "pretty"
)
public class CucumberTest {

    @SpringBootTest(
            classes = ArchitectureHexagonaleJava.class,
            webEnvironment = RANDOM_PORT
    )
    @CucumberContextConfiguration
    public static class CucumberSpringContextConfiguration {
        @Autowired
        private TestContextCleaner testContextCleaner;
        @Autowired
        private TestDatabaseCleaner testDatabaseCleaner;

        @Before
        public void cleanUp() {
            testContextCleaner.reset();
            testDatabaseCleaner.dropCollections();
        }
    }
}
