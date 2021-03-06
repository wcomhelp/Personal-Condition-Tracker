/* Personal Condition Tracker : A simple and attractive Android application that allows an individual to
 document, track and review the progression of a personal health issue (a 'condition'), thus serving to facilitate
 enhanced clarity of communicating between patient and care provider, early detection and accurate prognosis with the
 aim of obtaining medical treatment as soon as possible.

 Document the facts - get the treatment you deserve!

 Copyright (C) 2018

 R. Voon; rcvoon@ualberta.ca
 D. Buksa; draydon@ualberta.ca
 W. Nichols; wnichols@ualberta.ca
 D. Douziech; douziech@ualberta.ca
 C. Neureuter; neureute@ualberta.ca


Redistribution and use in source and binary forms, with or without
modification, are permitted (subject to the limitations in the disclaimer
below) provided that the following conditions are met:

     * Redistributions of source code must retain the above copyright notice,
     this list of conditions and the following disclaimer.

     * Redistributions in binary form must reproduce the above copyright
     notice, this list of conditions and the following disclaimer in the
     documentation and/or other materials provided with the distribution.

     * Neither the name of the copyright holder nor the names of its
     contributors may be used to endorse or promote products derived from this
     software without specific prior written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY
THIS LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND
CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER
IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
POSSIBILITY OF SUCH DAMAGE.

*/

package ca.ualberta.cs.personal_condition_tracker.Model;


/**
 * The CareProvider class is used to represent all of the account information pertaining to Care Providers.
 * @author  C. Neureuter; neureute@ualberta.ca
 * @author  R. Voon; rcvoon@ualberta.ca
 * @author  D. Buksa; draydon@ualberta.ca
 * @author  W. Nichols; wnichols@ualberta.ca
 * @author  D. Douziech; douziech@ualberta.ca
 * @version 1.1, 11-18-18
 * @since   1.0
 */

public class CareProvider extends UserAccount{

    private PatientList patientList = new PatientList();
    /**
     * Constructor for instantiating a basic care provider type object.
     */
    CareProvider() {
        super();
    }


    /**
     * Constructor with specified attributes: accountType, userID, and emailAddress.
     * @param accountType String representing the type of user account, Care Provider or Patient.
     * @param userID String representing the name of the account holder; a username.
     * @param emailAddress Email address of the account holder.
     * @see UserAccount
     */
    CareProvider(String accountType, String userID, String emailAddress) {
        super(accountType,userID,emailAddress);
    }

    /**
     * Constructor with specified attributes: accountType, userID, emailAddress, and phoneNumber.
     * @param accountType String representing the type of user account, Care Provider or Patient.
     * @param userID String representing the name of the account holder; a username.
     * @param emailAddress Email address of the account holder.
     * @param phoneNumber Phone number of the account holder.
     * @see UserAccount
     */
    public CareProvider(String accountType, String userID, String emailAddress, String phoneNumber) {
        super(accountType, userID, emailAddress, phoneNumber);
    }

    /**
     * Provides the list of Patient objects representing the patients that are assigned to a particular Care Provider.
     * @return  patientList List of patient that are assigned to a particular Care Provider
     * @see PatientList
     * @see Patient
     */
    public PatientList getPatientList() {
        return patientList;
    }


    /**
     * Sets the list of patients assigned to a particular Care Provider.
     * @param patientList List of patients, that is, a list of Patient objects
     * @return  Nothing
     * @see PatientList
     * @see Patient
     */

    public void setPatientList(PatientList patientList) {
        this.patientList = patientList;
    }
}