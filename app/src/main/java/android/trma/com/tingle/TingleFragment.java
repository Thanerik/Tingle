package android.trma.com.tingle;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Tingle Fragment to rule them all
 * @author Troels Madsen (trma)
 */

public class TingleFragment extends Fragment {
    // GUI variables
    private Button addThing, seeList;
    private TextView lastAdded;
    private TextView newWhat, newWhere;

    //fake database
    private static ThingsDB thingsDB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thingsDB = thingsDB.get(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tingle, container, false);

        //Accessing GUI element
        lastAdded = (TextView) v.findViewById(R.id.last_thing);
        updateUI();

        // Textfields for describing a thing
        newWhat = (TextView) v.findViewById(R.id.what_text);
        newWhere = (TextView) v.findViewById(R.id.where_text);

        // Button
        addThing = (Button) v.findViewById(R.id.add_button);
        // view products click event
        addThing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((newWhat.getText().length()>0) && (newWhere.getText().length()>0 )){
                    thingsDB.addThing(
                            new Thing( newWhat.getText().toString(),
                                    newWhere.getText().toString()));
                    newWhat.setText(""); newWhere.setText("");
                    updateUI();
                }
            }
        });

        // Button
        seeList = (Button) v.findViewById(R.id.thing_list_view);
        // If landscape, then hide this button (Don't need that)
        int orientation = getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_LANDSCAPE) {
            seeList.setVisibility(View.INVISIBLE);
        }
        seeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ListActivity.class);
                startActivity(i);
            }
        });

        return v;
    }

    private void updateUI(){
        int s = thingsDB.size();
        if (s > 0) lastAdded.setText(thingsDB.get(s-1).toString());
    }
}