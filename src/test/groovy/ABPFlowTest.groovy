import java.util.ArrayList;

import org.apache.log4j.Logger
import org.testng.annotations.Test
import content.support.SupportLoginPage
import content.support.*
import content.prosper.*
class ABPFlowTest extends TestHelper{

	Logger log = Logger.getLogger(ABPFlowTest)
	@Test
	public void testABPPathAListingCreation() {

		to SupportLoginPage
		login.loginToSupportSite()
		log.info("User is login into Support site")

		at SupportHomePage
		log.info("User is navigated to Support site home page")
		String emailAddress = submitABPviaDMPartner()
		log.info("User emailaddress is:"+emailAddress)
		log.info("User submit the ABP form via DM partner")

		withNewWindow({abpStartApp_btn.click()}){
			 at ABPRegistrationPage
			enterABPRegistrationAndSubmit()
			
			at ABPLoanOfferPage
			chooseRate_btn.click()
			waitFor('slow'){!loaderBox.present}
			
			at ABPPersonalDetailPage
			enterPersonalDetailAndSubmit()
			
			at ABPBankInfoPage
			submitABPBankInfoPage()
			
			assert at(ABPThankYouPage)
			pause()
			
			SupportFromJava supportFromJava = new SupportFromJava();
			ArrayList<String> fromToSubjectBody= supportFromJava.verifyMailBackEnd(emailAddress,"Following up on the Prosper request for")
			
			String url = supportFromJava.getHrefFromEmailBody(fromToSubjectBody.get(3))
			browser.go "$url"
			
			at ABPCreatePasswordPage
			
			waitFor(wait:'slow'){createPasswordForm_div.present}
		}
	}
}
