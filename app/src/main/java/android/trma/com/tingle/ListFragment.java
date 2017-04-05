package android.trma.com.tingle;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

/**
 * List Fragment to show the list
 * @author Troels Madsen (trma)
 */
public class ListFragment extends Fragment implements Observer {
    private ListView listView;
    private ArrayAdapter<Thing> listAdapter;

    //fake database
    private ThingsDB thingsDB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thingsDB = thingsDB.get(getContext());
        thingsDB.addObserver(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);

        if(thingsDB != null) {
            listAdapter = new ArrayAdapter<>(getActivity(), R.layout.list_item, thingsDB.getThingsDB());

            listView = (ListView) v.findViewById(R.id.list_view);
            listView.setAdapter(listAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    final Thing theThing = listAdapter.getItem(position);

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage(R.string.delete_alert)
                            .setPositiveButton(R.string.delete_sure, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    thingsDB.deleteThing(theThing);
                                    Toast.makeText(getActivity(), "Thing \""+theThing.getWhat()+"\" deleted", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton(R.string.delete_no, new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // CANCEL!! NOTHING HAPPENS
                        }
                    }).setCancelable(true);

                    builder.create().show(); // Start the alertDialog!
                }
            });
        }

        return v;
    }

    @Override
    public void update(Observable o, Object arg) {
        listAdapter.notifyDataSetChanged();
    }
}