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

package ca.ualberta.cs.personal_condition_tracker.Managers;

import android.os.AsyncTask;
import android.util.Log;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.util.ArrayList;
import java.util.List;

import ca.ualberta.cs.personal_condition_tracker.Model.Condition;
import io.searchbox.core.Delete;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

/**
 * ConditionListManager uses elasticsearch to store, retrieve, and remove conditions from the server.
 * @author    R. Voon; rcvoon@ualberta.ca
 * @author    D. Buksa; draydon@ualberta.ca
 * @version   1.1, 11-18-18
 * @since     1.0
 */

public class ConditionListManager {
    private static JestDroidClient client;

    /**
     * Add conditions to the server.
     */
    public static class AddConditionsTask extends AsyncTask<Condition, Void, Void> {
        @Override
        protected Void doInBackground(Condition... conditions) {
            verifySettings();

            for (Condition condition : conditions) {
                Index index = new Index.Builder(condition).index("cmput301f18t28").type("condition").build();

                try {
                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded()) {
                        if (condition.getId() == null) {
                            condition.setId(result.getId());
                        }
                        Log.e("Error", "Success.");
                    }
                    else {
                        Log.e("Error", "Elastic search was not able to add the condition.");
                    }
                }
                catch (Exception e) {
                    Log.i("Error", "The application failed to build and send the conditions.");
                }

            }
            return null;
        }
    }

    /**
     * Get conditions from the server.
     */
    public static class GetConditionsTask extends AsyncTask<String, Void, ArrayList<Condition>> {
        @Override
        protected ArrayList<Condition> doInBackground(String... search_parameters) {
            verifySettings();

            ArrayList<Condition> conditions = new ArrayList<>();

            Search search = new Search.Builder( search_parameters[0])
                    //Search search = new Search.Builder( search_parameters[0] )
                    .addIndex("cmput301f18t28")
                    .addType("condition")
                    .build();

            try {
                SearchResult result = client.execute(search);
                if (result.isSucceeded()) {
                    List<Condition> foundConditions = result.getSourceAsObjectList(Condition.class);
                    conditions.addAll(foundConditions);
                    Log.e("Error", "Success.");
                }
                else {
                    Log.e("Error", "The search query failed to find any conditions that matched.");
                }
            }
            catch (Exception e) {
                Log.e("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }
            return conditions;
        }
    }
    /**
     * Remove conditions from the server.
     */
    public static class DeleteConditionsTask extends AsyncTask<Condition, Void, Void> {
        @Override
        protected Void doInBackground(Condition... conditions) {
            verifySettings();

            Condition condition = conditions[0];
            String jestID = condition.getId();
            Delete delete = new Delete.Builder(jestID).index("cmput301f18t28").type("condition").build();

            try {
                DocumentResult result = client.execute(delete);
                if (result.isSucceeded()) {
                    Log.e("Error", "Success.");
                }
                else {
                    Log.e("Error", "The search query failed to find any conditions that matched.");
                }
            }
            catch (Exception e) {
                Log.e("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }
            return null;
        }
    }


    /**
     * Set up the server.
     */
    public static void verifySettings() {
        if (client == null) {
            DroidClientConfig.Builder builder = new DroidClientConfig.Builder("http://cmput301.softwareprocess.es:8080/");
            DroidClientConfig config = builder.build();

            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = (JestDroidClient) factory.getObject();
        }
    }
}
