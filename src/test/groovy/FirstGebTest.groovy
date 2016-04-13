

import org.testng.annotations.Test

import content.prosper.*
import geb.testng.GebReportingTest
import org.apache.log4j.Logger;

class FirstGebTest extends GebReportingTest {
	Logger log = Logger.getLogger(FirstGebTest)

	@Test
	public void testCreditReportLink() {

		to PublicSiteHomePage
		log.info('User is landed on STG public site')
		loanAmount<<'4000'
		log.info('User has entered loan amount on widget')
		
		loanPurpose='Debt Consolidation'
		log.info('User has entered loan purpose on widget')
		
		creditQuality='Excellent Credit (760+)'
		log.info('User has entered credit quality on widget')
		
		checkYourRateButton.click()
		log.info('User has clicked on check your rate button on widget')

		at PublicSiteRegistrationPage
		log.info('User has navigate to registration page')

		withNewWindow({creditReportAuth_link.click()}){
			assert creditReportAuth_window.text() == 'Authorization to Obtain Credit Report at Registration'
		}
		String emailaddress = enterDetailAndSubmitRegistration('GARY','BAKOWSKI','2416 WASHINGTON AVE','SAINT ALBANS','251773232','WV','Employed','900000','12/12/1970','automation'+Math.abs(new Random().nextInt() % 600) + 1+'@c1.stg')
		log.info('User has entered registration form on registration page')
		log.info(emailaddress)
		assert at(PublicSiteLoanOfferPage)
		
		selectLoanOffers()
		
		assert at(PublicSitePersonalDetailPage)
		
		enterPersonalDetailAndSubmit()
		
		assert at(PublicSiteLoanTermPage)
		acceptAndSubmitTil()
		pause()
		
	}
}
