package com.example.nick.test;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Information extends AppCompatActivity {

    public static final MediaType FORM_DATA_TYPE
            = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
    //URL derived from form URL
    public static final String URL="https://docs.google.com/forms/d/e/1FAIpQLSe1i9PRjy43QcIMNMoctshJ6unxWXI5QJw1Z0fcBj78sTPGcA/formResponse";
    //input element ids found from the live form page
    public static final String EMAIL_KEY="entry.22263508";
    public static final String FIRSTNAME_KEY ="entry.1202782164";
    public static final String LASTNAME_KEY ="entry.799015540";
    public static final String MAJOR_KEY ="entry.1910484816";
    public static final String YEAR_KEY = "entry.1720201731";
    public static final String PAID_KEY = "entry.416592938";

    private Context context;
    private EditText emailEditText;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText majorEditText;
    private Object spinnerText;
    private String noPay = "No";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        //save the activity in a context variable to be used afterwards
        context = this;

        //Get references to UI elements in the layout
        Button sendButton = (Button)findViewById(R.id.button7);
        emailEditText = (EditText)findViewById(R.id.editText2);
        firstNameEditText = (EditText)findViewById(R.id.editText3);
        lastNameEditText = (EditText)findViewById(R.id.editText4);
        majorEditText = (EditText)findViewById(R.id.editText5);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.year_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        // spinnerText = spinner.getSelectedItem().toString();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                spinnerText = parent.getItemAtPosition(pos);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.paid_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        // spinnerText = spinner.getSelectedItem().toString();
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                spinnerText = parent.getItemAtPosition(pos);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean mChoice = getIntent().getBooleanExtra("memberChoice", false);


                //Make sure all the fields are filled with values
                if(TextUtils.isEmpty(emailEditText.getText().toString()) ||
                        TextUtils.isEmpty(firstNameEditText.getText().toString()))
                {
                    Toast.makeText(context,"All fields are mandatory.",Toast.LENGTH_LONG).show();
                    return;
                }
                //Check if a valid email is entered
                if(!android.util.Patterns.EMAIL_ADDRESS.matcher(emailEditText.getText().toString()).matches())
                {
                    Toast.makeText(context,"Please enter a valid email.",Toast.LENGTH_LONG).show();
                    return;
                }

                //Create an object for PostDataTask AsyncTask
                PostDataTask postDataTask = new PostDataTask();

                //execute asynctask
                postDataTask.execute(URL,emailEditText.getText().toString(),
                        firstNameEditText.getText().toString(), lastNameEditText.getText().toString(),
                        majorEditText.getText().toString(), spinnerText.toString(), noPay);

                    /*
                    Intent intent = new Intent(getApplicationContext(), SignedIn.class);
                    startActivity(intent);


                    Intent intent = new Intent(getApplicationContext(), Payment.class);
                    intent.putExtra("firstName", firstNameEditText.toString());
                    intent.putExtra("lastName", lastNameEditText.toString());
                    intent.putExtra("email", emailEditText.toString());
                    intent.putExtra("major", majorEditText.toString());
                    intent.putExtra("year", spinnerText.toString());
                    startActivity(intent);
                    */
            }
        });
    }

    //AsyncTask to send data as a http POST request
    private class PostDataTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... contactData) {
            Boolean result = true;
            String url = contactData[0];
            String email = contactData[1];
            String firstName = contactData[2];
            String lastName = contactData[3];
            String major = contactData[4];
            String year = contactData[5];
            String paid = contactData[6];
            String postBody="";

            try {
                //all values must be URL encoded to make sure that special characters like & | ",etc.
                //do not cause problems
                postBody = EMAIL_KEY + "=" + URLEncoder.encode(email,"UTF-8") +
                        "&" + FIRSTNAME_KEY + "=" + URLEncoder.encode(firstName,"UTF-8")
                        + "&" + LASTNAME_KEY + "=" + URLEncoder.encode(lastName, "UTF-8")
                        + "&" + MAJOR_KEY + "=" + URLEncoder.encode(major, "UTF-8")
                        + "&" + YEAR_KEY + "=" + URLEncoder.encode(year, "UTF-8")
                        + "&" + PAID_KEY + "=" + URLEncoder.encode(paid, "UTF-8");
            } catch (UnsupportedEncodingException ex) {
                result=false;
            }


            try{
                //Create OkHttpClient for sending request
                OkHttpClient client = new OkHttpClient();
                //Create the request body with the help of Media Type
                RequestBody body = RequestBody.create(FORM_DATA_TYPE, postBody);
                Request request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();
                //Send the request
                Response response = client.newCall(request).execute();
            }catch (IOException exception){
                result=false;
            }
            return result;
        }



        @Override
        protected void onPostExecute(Boolean result){
            //Print Success or failure message accordingly
            Toast.makeText(context,result?"Message successfully sent!":"There was some error in sending message. Please try again after some time.",Toast.LENGTH_LONG).show();
        }

    }
}