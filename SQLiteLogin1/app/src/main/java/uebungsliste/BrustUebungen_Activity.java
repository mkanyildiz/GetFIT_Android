package uebungsliste;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Toast;

import com.etsy.android.grid.StaggeredGridView;
import com.packone.login.R;

import java.util.ArrayList;

public class BrustUebungen_Activity extends Activity implements AbsListView.OnScrollListener, AbsListView.OnItemClickListener {

    private static final String TAG = "StaggeredGridActivity";
    public static final String SAVED_DATA_KEY = "SAVED_DATA";

    private StaggeredGridView mGridView;
    private boolean mHasRequestedMore;
    private BauchUebungenAdapter mAdapter;

    private ArrayList<String> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.gridview);

        setTitle("TechnoTalkative - SGV Demo");
        mGridView = (StaggeredGridView) findViewById(R.id.grid_view);
        mAdapter = new BauchUebungenAdapter(this, android.R.layout.simple_list_item_1, generateData());
        // do we have saved data?
        /*if (savedInstanceState != null) {
            mData = savedInstanceState.getStringArrayList(SAVED_DATA_KEY);
        }

        if (mData == null) {
            mData = generateData();
        }

        for (String data : mData) {
            mAdapter.add(data);
        }*/

        mGridView.setAdapter(mAdapter);
        mGridView.setOnScrollListener(this);
        mGridView.setOnItemClickListener(this);
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList(SAVED_DATA_KEY, mData);
    }

    @Override
    public void onScrollStateChanged(final AbsListView view, final int scrollState) {
        Log.d(TAG, "onScrollStateChanged:" + scrollState);
    }

    @Override
    public void onScroll(final AbsListView view, final int firstVisibleItem, final int visibleItemCount, final int totalItemCount) {
        Log.d(TAG, "onScroll firstVisibleItem:" + firstVisibleItem +
                " visibleItemCount:" + visibleItemCount +
                " totalItemCount:" + totalItemCount);
        // our handling
        /* (!mHasRequestedMore) {
            int lastInScreen = firstVisibleItem + visibleItemCount;
            if (lastInScreen >= totalItemCount) {
                Log.d(TAG, "onScroll lastInScreen - so load more");
                //mHasRequestedMore = true;
                //onLoadMoreItems();
            }
        }*/
    }

    private void onLoadMoreItems() {
        final ArrayList<String> sampleData = generateData();
        for (String data : sampleData) {
            mAdapter.add(data);
        }
        // stash all the data in our backing store
        mData.addAll(sampleData);
        // notify the adapter that we can update now
        mAdapter.notifyDataSetChanged();
        mHasRequestedMore = false;
    }

    private ArrayList<String> generateData() {
        ArrayList<String> listData = new ArrayList<String>();
        listData.add("http://www.for-fitness-friends.de/wp-content/uploads/Bankdr%C3%BCcken-420x283.jpg");
        listData.add("http://www.uebungen.ws/wp-content/uploads/2012/01/Positiv-Kurzhantel-Fliegende.jpg"); //Fliegende
        listData.add("http://www.uebungen.ws/wp-content/uploads/2012/01/Schr%C3%A4gbankdr%C3%BCcken.jpg"); //schraegbankdrucken
        listData.add("http://www.uebungen.ws/wp-content/uploads/2012/01/Brustpresse.jpg");
        listData.add("http://www.uebungen.ws/wp-content/uploads/2012/01/Butterfly-am-Kabelzug.jpg");
        listData.add("http://mhstatic.de/fm/1/thumbnails/Fitness_Lexikon_Uebung_133a.jpg.2409867.jpg");
        listData.add("http://mhstatic.de/fm/1/thumbnails/MH_0909_Liegest%C3%BCtz_A_800.jpg.2432539.jpg");
        return listData;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Toast.makeText(this, "Item Clicked: " + position, Toast.LENGTH_SHORT).show();
    }
}