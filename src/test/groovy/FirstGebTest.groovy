

import org.testng.annotations.Test

import content.prosper.*
import geb.testng.GebReportingTest
import org.apache.log4j.Logger;

class FirstGebTest extends TestHelper {
	Logger log = Logger.getLogger(FirstGebTest)

	@Test
	public void testBorrowerListingCreation() {

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

		String emailaddress = enterDetailAndSubmitRegistration('GARY','BAKOWSKI','2416 WASHINGTON AVE','SAINT ALBANS','251773232','WV','Employed','900000','12/12/1970','automation'+Math.abs(new Random().nextInt() % 600) + 1+'@c1.stg')
		log.info('User has entered registration form on registration page')
		log.info('User emailAddress is :'+emailaddress)
		assert at(PublicSiteLoanOfferPage)
		log.info('User has navigate to Loan offer page')
		selectLoanOffers()
		log.info('User has select the offer from loan offer page')
		assert at(PublicSitePersonalDetailPage)
		log.info('User has navigate to personal detail page')
		enterPersonalDetailAndSubmit()
		log.info('User has entered the personal detail')
		
		assert at(PublicSiteLoanTermPage)
		log.info('User has navigate to til page')
		acceptAndSubmitTil()
		log.info('User has accepted the til document')
		assert at(PublicSiteBankInfoPage)
		log.info('User has navigate to bank info page')
		submitManualBankOptions()
		log.info('User has enter the manual bank option detail on bank info page')
		assert at(PublicSiteThankYouPage)
		log.info('User has navigate to Thank You page')
		goToMyAccount_btn.click()
		log.info('User has clicked on Go To My Account button to move on Account Overview page')
	}
}
