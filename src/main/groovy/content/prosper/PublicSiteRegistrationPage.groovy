package content.prosper

import geb.Page

class PublicSiteRegistrationPage extends Page {
	static at = {
		  $(".small-12.columns>h1").text() == "Get a Custom Rate in 1 Click"
		 }
	
	static content = {
		loaderBox ( required: false ){$('.spinnerContent')}
        firstName_in    (wait:'slow'){$('#first-name')}
        lastName_in (wait:'slow'){$('#last-name')}
        homeAddress_in  (wait:'slow'){$('#home-address')}
        city_in     (wait:'slow'){$('#city')}
        zip_in  (wait:'slow') {$('#zip')}
        state_dropDown (wait:'slow'){$('#state')}
        employmentStatus_dropDown (wait:'slow') {$('#employment_status_id')}
        yearlIncome_in  (wait:'slow') {$('#individual-yearly-income')}
        dateOfBirth_in (wait:'slow')    {$('#date-of-birth-text')}
        creditReportAuthorization_checkBox (wait:'slow') {$('#credit-report-authorization')}
        getYourRate_btn (to:PublicSiteLoanOfferPage,wait:true) {$("button[data-q='get-rate']")}
        emailAddressField (wait:true){$("#emailTop")}
        password_in (wait:true){$("#ab-test-password>input[name='passwordTop']")}
		creditReportAuth_link (wait:true){$("a[data-q='credit-report-authorization']")}
		creditReportAuth_window(wait:'slow'){$("#M_MainContent_lblAgreementTitle")}
    }

    /**
     * @param firstName
     * @param lastName
     * @param streetName
     * @param cityName
     * @param zipCode
     * @param stateName
     * @param employmentStatus
     * @param yearlyIncome
     * @param dob
     * @param emailAddress
     * @return
     */
    def enterDetailAndSubmitRegistration(firstName,lastName,streetName,cityName,zipCode,stateName,employmentStatus,yearlyIncome,dob,emailAddress){

        firstName_in <<firstName
        lastName_in <<lastName

        homeAddress_in <<streetName

        city_in << cityName
        zip_in <<zipCode
        state_dropDown = stateName
        employmentStatus_dropDown = employmentStatus
        yearlIncome_in <<yearlyIncome
        dateOfBirth_in << dob

        emailAddressField << emailAddress
        password_in << 'Password23'
		creditReportAuthorization_checkBox.click()
        getYourRate_btn.click()
		
		waitFor('slow'){!loaderBox.present}
		return emailAddress
    }

}
