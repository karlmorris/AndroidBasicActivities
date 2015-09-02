package edu.temple.basicactivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityB extends Activity {

    TextView messageTextView;
    Button openActivityButton;

    // Arbitrary unique integer
    int activityCRequestCode = 1234321;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        messageTextView = (TextView) findViewById(R.id.messageTextView);
        openActivityButton = (Button) findViewById(R.id.openActivityButton);

        //  Retrieve the intent used to launch this instance of the activity
        Intent receivedIntent = getIntent();

        //  Grad the data that was passed.
        String intentDataString = receivedIntent.getStringExtra(KeyHelper.DATA_FROM_A_TO_B_KEY);

        //  Display the received message in the text view
        messageTextView.setText(intentDataString);

        openActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchActivityIntent = new Intent(ActivityB.this, ActivityC.class);

                startActivityForResult(launchActivityIntent, activityCRequestCode);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        //  If the callback was invoked because ActivityC exited
        if (requestCode == activityCRequestCode){

            //  If the operation was successful
            if (resultCode == Activity.RESULT_OK){
                messageTextView.setText("ActivityC reported that it did whatever it was supposed to do");

                //There might be some data in the returned intent if ActivityC placed it there.
                Bundle dataExtras = null;

                if (data != null) {
                    dataExtras = data.getExtras();
                }

                if (dataExtras != null){
                    Log.d("Stuff from ActivityC", dataExtras.toString());
                }

            } else if (resultCode == Activity.RESULT_CANCELED){
                messageTextView.setText("The user calceled the operation");
            } else {
                messageTextView.setText("ActivityC returned some other status code that we haven't gotten around to implementing yet.");
            }

        }

    }




}
