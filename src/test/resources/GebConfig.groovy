//import org.openqa.selenium.ie.InternetExplorerDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxBinary
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxProfile


/*driver = {
	System.setProperty('webdriver.chrome.driver', "E:\\Workspace\\GebTestingDemo\\src\\test\\resources\\chromedriver.exe")
	def driverInstance = new ChromeDriver()
	driverInstance.manage().window().maximize()
	driverInstance

}*/


environments {
	
	// run as “mvn -Dgeb.env=chrome test”
	// See: http://code.google.com/p/selenium/wiki/ChromeDriver
	chrome {
		System.setProperty('webdriver.chrome.driver', "E:\\Workspace\\GebTestingDemo\\src\\test\\resources\\chromedriver.exe")
		driver = { new ChromeDriver() }
	}
	
	firefox{
		driver = { new FirefoxDriver() }
		
	}

}

// application URI to be tested using geb 
baseUrl = "https://www.stg.circleone.com/"


reportsDir = new File("build/geb-reports")
waiting {
	presets {
		slow {
			timeout = 120
			retryInterval = 1
			includeCauseInMessage = true
		}
		quick {
			timeout = 1
			includeCauseInMessage = true
		}
	}
}



unexpectedPages = [PageNotFoundPage, InternalServerErrorPage]


waiting {
	includeCauseInMessage = true
	atCheckWaiting = true
}



