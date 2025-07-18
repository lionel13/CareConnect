package fr.varex13.careconnect.domain.service.cucumber;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("useCases")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "fr.varex13.careconnect.domain.service.cucumber")
class RunCucumberUTTest {
}
