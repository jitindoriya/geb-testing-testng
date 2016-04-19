import java.util.ArrayList;

import org.apache.log4j.Logger
import org.testng.annotations.Test
import content.support.SupportLoginPage
import content.support.*
import content.prosper.*
class ABPFlowTest extends TestHelper{

	Logger log = Logger.getLogger(ABPFlowTest)
	SupportFromJava supportFromJava = new SupportFromJava();
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


			ArrayList<String> fromToSubjectBody= supportFromJava.verifyMailBackEnd(emailAddress,"Following up on the Prosper request for")

			String url = supportFromJava.getHrefFromEmailBody(fromToSubjectBody.get(3))
			browser.go "$url"

			at ABPCreatePasswordPage
			waitFor('slow'){!loadingBox_div.present}
			waitFor('slow'){createPasswordForm_div.displayed }
			createPassword_input ="Password23"
			creditReportAuth_checkbox.click()
			finishYourLoan_btn.click()
			assert at(PublicSiteLoanTermPage)
			log.info('User has navigate to til page')
			acceptAndSubmitTil()
			log.info('User has accepted the til document')
			assert at(PublicSiteBankInfoPage)
			submitExistingUserManualBankOptions()
			assert at(PublicSiteThankYouPage)
			log.info('User has navigate to Thank You page')
			goToMyAccount_btn.click()
			log.info('User has clicked on Go To My Account button to move on Account Overview page')
		}
	}


	@Test
	void testABPPathBListingCreation(){
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
			completeLater_btn.click()
			waitFor('slow'){abandonModal_div.present}

			miscellaneousReasonID_radio[1].click()
			submitCompleteLater_btn.click()
			waitFor('slow'){successCompleteLaterModal.present}
			waitFor('slow'){successCompleteLaterModal.text().contains("Abandon reason submitted.")}


			ArrayList<String> fromToSubjectBody= supportFromJava.verifyMailBackEnd(emailAddress,"Following up on the Prosper request for")

			String url = supportFromJava.getHrefFromEmailBody(fromToSubjectBody.get(3))
			browser.go "$url"

			at ABPCreatePasswordPage
			waitFor('slow'){!loadingBox_div.present}
			waitFor('slow'){createPasswordForm_div.displayed }
			createPassword_input ="Password23"
			creditReportAuth_checkbox.click()
			finishYourLoan_btn.click()
			
			assert at(PublicSiteRegistrationPage)
			creditReportAuthorization_checkBox.click()
			getYourRate_btn.click()
			waitFor('slow'){!loaderBox.present}
			
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
}
