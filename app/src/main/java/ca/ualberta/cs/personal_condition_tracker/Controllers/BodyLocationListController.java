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

package ca.ualberta.cs.personal_condition_tracker.Controllers;

import android.util.Log;

import java.util.ArrayList;
import ca.ualberta.cs.personal_condition_tracker.Model.BodyLocation;
import ca.ualberta.cs.personal_condition_tracker.Model.BodyLocationList;
import ca.ualberta.cs.personal_condition_tracker.Model.Record;
import ca.ualberta.cs.personal_condition_tracker.Managers.BodyLocationListManager;

/**
 * BodyLocationListController performs operations on a BodyLocationList object.
 * @author    R. Voon; rcvoon@ualberta.ca
 * @author    D. Buksa; draydon@ualberta.ca
 * @author    W. Nichols; wnichols@ualberta.ca
 * @author    D. Douziech; douziech@ualberta.ca
 * @author    C. Neureuter; neureute@ualberta.ca
 * @version   1.1, 11-18-18
 * @since     1.0
 */
public class BodyLocationListController {
    private static BodyLocationList bodyLocationList = null;
    /**
     * Gets the BodyLocation list.
     * @return bodyLocationList
     */
    static public BodyLocationList getBodyLocationList() {
        if ((bodyLocationList) == null) {
            bodyLocationList = new BodyLocationList();
        }
        return bodyLocationList;
    }

    /**
     * Load body locations from the elasticsearch server.
     * @param record
     * @return
     */
    public ArrayList<BodyLocation> loadBodyLocations(Record record) {
        ArrayList<BodyLocation>  bodyLocations = new ArrayList<>();
        BodyLocationListManager.GetBodyLocationsTask getBodyLocationsTask =
                new BodyLocationListManager.GetBodyLocationsTask();
        String query = "{ \"query\": {\"match\": { \"associatedRecordID\" : \""+ record.getId() +"\" } } }";
        Log.e("Error", record.getId());
        try {
            bodyLocations = getBodyLocationsTask.execute(query).get();
        } catch (Exception e) {
            Log.e("Error", "Failed to get the records out of the async object.");
        }
        Log.e("Error", Integer.toString(bodyLocations.size()));
        return bodyLocations;
    }

    /**
     *  Add a body location to the server
     */
    public void createBodyLocation(BodyLocation newBodyLocation) {
        // Check if the body location already exists
        BodyLocationListManager.GetBodyLocationsTask getBodyLocationsTask =
                new BodyLocationListManager.GetBodyLocationsTask();
        String query = "{ \"query\": {\"match\": { \"id\" : \"" + newBodyLocation.getId() + "\" } } }";
        getBodyLocationsTask.execute(query);
        ArrayList<BodyLocation> bodyLocations = new ArrayList<>();
        try {
            bodyLocations = getBodyLocationsTask.get();
        } catch (Exception e) {
            Log.e("Error", "Failed to get the tweets out of the async object.");
        }

        // Add the body location to the database.
        if (bodyLocations.size() == 0) {
            BodyLocationListManager.AddBodyLocationsTask addBodyLocationsTask
                    = new BodyLocationListManager.AddBodyLocationsTask();
            addBodyLocationsTask.execute(newBodyLocation);
        }
    }

    /**
     * Remove a selected body location from the server.
     * @param selectedBodyLocation
     */
    public void deleteBodyLocation(BodyLocation selectedBodyLocation) {
        BodyLocationListManager.DeleteBodyLocationsTask deleteBodyLocationsTask =
                new BodyLocationListManager.DeleteBodyLocationsTask();
        deleteBodyLocationsTask.execute(selectedBodyLocation);
    }

}
