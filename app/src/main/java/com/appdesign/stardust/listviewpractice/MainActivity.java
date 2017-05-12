package com.appdesign.stardust.listviewpractice;

import android.app.ListActivity;
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.*;
import android.widget.*;
import java.util.*;
import android.view.*;
import android.graphics.Color;

public class MainActivity extends ListActivity {
    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    String[] customItems ={"apple", "orange", "banana", "orangutaun", "Great Ape", "Great Apple", "monkeys" };
    String[] customSubItems = {"Golden", "Floridian", "Monkey", "Smart Ape", "Human", "New York City","Bonobo"};


    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LIST VIEW
   ArrayList<TextObject> objectView;

    //RECORDING HOW MANY TIMES THE BUTTON HAS BEEN CLICKED
    int clickCounter=0;
    Random rand = new Random(100);

    @Override
    public void onCreate(Bundle practice) {
        super.onCreate(practice);

        setContentView(R.layout.main);
        /** Old Code for Creating new items on the ListView
       /* adapter=new ArrayAdapter<String>(this,
               android.R.layout.simple_list_item_1,
               listItems)
           //Allows changing of text for each menu item
      {
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view =super.getView(position, convertView, parent);

           TextView textView=(TextView) view.findViewById(android.R.id.text1);

         YOUR CHOICE OF COLOR
              textView.setTextColor(rand.nextInt());

                return view;
           }
        };*/
        objectView = new ArrayList<TextObject>();

        //Create the adapter and assign an ArrayAdapter to handle adding and removing objects from the
        //Adapter
        TextAdapter txtAdapter = new TextAdapter(this, objectView);

        //Identifying the ListView to be populated with data
        ListView lV = (ListView) findViewById(android.R.id.list);

        //Assigning the adapter created to the listView
        lV.setAdapter(txtAdapter);

        //Populating the listView
        for (int i = 0; i < customItems.length; i++) {
            txtAdapter.add(new TextObject(customItems[i].toUpperCase(), customSubItems[i]));
        }
    }

    //METHOD WHICH WILL HANDLE DYNAMIC INSERTION
  /* public void addItems(View v) {
        if (clickCounter < 9) { adapter.add("Clicks: " + ((clickCounter++) + 1));}
        else{ adapter.clear();clickCounter = 0; }
        //adapter class automatically calls this method
       // adapter.notifyDataSetChanged();
    }*/
    //Data Object to Represent Multiple Data sets
    public class TextObject {

        public String mainText;

        public String subText;

        public TextObject(String mainText, String subText) {

            this.mainText = mainText;

            this.subText = subText;

        }

    }

    //Creating a Custom Adapter to handle multiple data sets for a view
    public class TextAdapter extends ArrayAdapter<TextObject> {

        public TextAdapter(Context context, ArrayList<TextObject> users) {

            super(context, 0, users);

        }



        @Override

        public View getView(int position, View convertView, ViewGroup parent) {

            // Get the data item for this position
            TextObject txtObject = getItem(position);

            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {

                convertView = LayoutInflater.from(getContext()).inflate(R.layout.mylist, parent, false);

            }

            // Lookup view for data population
            TextView mainTextView = (TextView) convertView.findViewById(R.id.text_view_custom);

            TextView subTextView = (TextView) convertView.findViewById(R.id.sub_text_view);

            // Populate the data into the template view using the data object
            mainTextView.setText(txtObject.mainText);

            subTextView.setText(txtObject.subText);

            // Return the completed view to render on screen

            return convertView;

        }

    }
}