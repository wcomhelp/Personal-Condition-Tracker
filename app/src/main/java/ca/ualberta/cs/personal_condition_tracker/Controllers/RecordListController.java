package ca.ualberta.cs.personal_condition_tracker.Controllers;

import android.util.Log;

import java.util.ArrayList;

import ca.ualberta.cs.personal_condition_tracker.Model.Condition;
import ca.ualberta.cs.personal_condition_tracker.Model.Record;
import ca.ualberta.cs.personal_condition_tracker.Model.RecordList;
import ca.ualberta.cs.personal_condition_tracker.Managers.RecordListManager;

public class RecordListController {

    private static RecordList recordList = null;
    /**
     * Gets the user account list.
     * @return UserAccountList userAccountList
     */
    static public RecordList getRecordList() {
        if ((recordList) == null) {
            recordList = new RecordList();
        }
        return recordList;
    }
    /**
     * Add an account to the user account list.
     */
    public void addRecord(Record record){ getRecordList().addRecord(record);}

    public ArrayList<Record> loadRecords(Condition condition) {
        ArrayList<Record>  records = new ArrayList<>();
        RecordListManager.GetRecordsTask getRecordsTask =
                new RecordListManager.GetRecordsTask();
        String query = "{ \"query\": {\"match\": { \"associatedConditionID\" : \""+ condition.getId() +"\" } } }";
        Log.e("Error", condition.getId());
        try {
            records = getRecordsTask.execute(query).get();
        } catch (Exception e) {
            Log.e("Error", "Failed to get the records out of the async object.");
        }
        Log.e("Error", Integer.toString(records.size()));
        return records;
    }

    public void deleteRecord(Record selectedRecord) {
        RecordListManager.DeleteRecordsTask deleteRecordsTask =
                new RecordListManager.DeleteRecordsTask();
        deleteRecordsTask.execute(selectedRecord);
    }

    public ArrayList<Record> searchByKeyword(String keywords, String condition_id) {
        ArrayList<Record>  records = new ArrayList<>();
        RecordListManager.GetRecordsTask getRecordsTask =
                new RecordListManager.GetRecordsTask();
        String query = "{ \"size\": 100," +
                "  \"query\": {" +
                "    \"bool\": {" +
                "      \"must\": " +
                "      [{\"multi_match\" : {\"query\":    \""+ keywords +"\", \"fields\": [ \"title\", \"description\" ]}}," +
                "       {\"match\": {\"associatedConditionID\": \""+ condition_id + "\"}}]" +
                "    }" +
                "  }" +
                "}";
        try {
            records = getRecordsTask.execute(query).get();
        } catch (Exception e) {
            Log.e("Error", "Failed to get the tweets out of the async object.");
        }
        return records;
    }

    public ArrayList<Record> searchByGeoLocation(String latitude, String longitude, String distance, String condition_id) {
        ArrayList<Record>  records = new ArrayList<>();
        RecordListManager.GetRecordsTask getRecordsTask =
                new RecordListManager.GetRecordsTask();
        String query = "{" +
                "  \"query\": {" +
                "    \"bool\" : {" +
                "      \"must\" : {" +
                "        \"match\" : {\"associatedConditionID\" : \""+condition_id +"\"}" +
                "      }" +
                "    }" +
                "  }," +
                "  \"filter\" : {" +
                "    \"geo_distance\" : {" +
                "      \"distance\" : \"" + distance + " km" + "\"," +
                "      \"location\" : {" +
                "        \"lat\" : \""+ latitude + "\"," +
                "        \"lon\" : \"" + longitude + "\"" +
                "      }" +
                "    }" +
                "  }" +
                "}";

        try {
            records = getRecordsTask.execute(query).get();
        } catch (Exception e) {
            Log.e("Error", "Failed to get the tweets out of the async object.");
        }
        return records;
    }


}