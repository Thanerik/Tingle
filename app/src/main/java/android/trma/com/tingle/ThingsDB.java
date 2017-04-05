package android.trma.com.tingle;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * The Thingy singleton "fake" database
 * @author Troels Madsen (trma)
 */
public class ThingsDB extends Observable {
    private static volatile ThingsDB sThingsDB;

    //fake database
    private List<Thing> mThingsDB;

    public static ThingsDB get(Context context) {
        if (sThingsDB == null) {
            synchronized (ThingsDB.class) {
                if (sThingsDB == null) {
                    sThingsDB = new ThingsDB(context);
                }
            }
        }
        return sThingsDB;
    }

    public List<Thing> getThingsDB() { return mThingsDB; }

    public void addThing(Thing thing) {
        mThingsDB.add(thing);
        notifying();
    }

    public void deleteThing(Thing thing) {
        mThingsDB.remove(thing);
        notifying();
    }

    public int size() { return mThingsDB.size(); }

    public Thing get(int i) { return mThingsDB.get(i); }

    private void notifying() {
        setChanged();
        notifyObservers();
        clearChanged();
    }

    // Fill database for testing purposes
    private ThingsDB(Context context) {
        mThingsDB = new ArrayList<>();
        mThingsDB.add(new Thing("Android Phone", "Desk"));
        mThingsDB.add(new Thing("Big Nerd book", "Desk"));
        mThingsDB.add(new Thing("Thermo mug", "Desk"));
        mThingsDB.add(new Thing("Coffee", "Café Analog"));
        mThingsDB.add(new Thing("Mobile App Development", "Aud3"));
        mThingsDB.add(new Thing("Interpreters", "Besides me"));
        mThingsDB.add(new Thing("Laptop", "At home..."));
        mThingsDB.add(new Thing("t", "between r and y"));
        mThingsDB.add(new Thing("My brain", "Freezer"));
        mThingsDB.add(new Thing("Unicorn", "Atlantis"));
    }
}
