package fr.univtours.examplanner;

import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("fr.univtours.examplanner")
@IncludeClassNamePatterns(".*Test")
class TestMain {

}
