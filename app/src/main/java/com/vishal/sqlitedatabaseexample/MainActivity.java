package com.vishal.sqlitedatabaseexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DBHelper db = new DBHelper(this);

        // Inserting person/Rows
        Log.d("Insert: ", "Inserting ..");
        db.insertPerson(new Person("Vishal Halani", 24, "9510732508", "Male"));
        db.insertPerson(new Person("Akshay Shah", 25, "9898989898", "Male"));
        db.insertPerson(new Person("Jay Patel", 26, "8989898989", "Male"));


        // Reading all shops
        Log.d("Reading: ", "Reading all person..");
        List<Person> personList = db.getAllPerson();

        for (Person person : personList) {
            String log = "Id: " + person.getId() + " ,Name: " + person.getName() + " ,Age: " + person.getAge() + " ,Mobile Number: " + person.getMobileNumber() + " ,Gender: " + person.getGender();
            // Writing person  to log
            Log.d("Person: : ", log);
        }
    }
}
