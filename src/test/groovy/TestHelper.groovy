

import groovy.util.logging.Log4j
import org.apache.log4j.PropertyConfigurator	
import org.testng.annotations.BeforeTest

//import content.prosper.XMLReader;
import geb.testng.GebReportingTest

class TestHelper  extends GebReportingTest{
	
	XMLReader xml
	@BeforeTest
	void before() {
		def config = new ConfigSlurper().parse(new File('log4j.groovy').toURL())
        PropertyConfigurator.configure(config.toProperties())
		xml = new XMLReader("E:\\Workspace\\GebTestingDemo\\src\\test\\resources\\modalData.xml");
	}

}
