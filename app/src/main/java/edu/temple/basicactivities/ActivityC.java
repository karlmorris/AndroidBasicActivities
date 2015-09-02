package edu.temple.basicactivities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ActivityC extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);
        /*
            A predefined code to send back to the parent activity when
            this activity closes to indicate a successful operation.
        */
        setResult(Activity.RESULT_OK);
    }

}
