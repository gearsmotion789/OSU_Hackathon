package com.example.slimify;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ListUsersFragment extends Fragment {

    private OnListFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter mAdapter;

    DatabaseHelper mDatabaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mDatabaseHelper = new DatabaseHelper(getActivity());

        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        //if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = view.findViewById(R.id.list);
            recyclerView.setFocusable(false);   // Makes nested scroll view go to top instead of starting at grid view
            //recyclerView.setNestedScrollingEnabled(false); // Not needed b/c declared in XML file
            recyclerView.setLayoutManager(new GridLayoutManager(context,2));
            recyclerView.setAdapter(new RecyclerViewAdapter(new ArrayList<User>(), mListener));
        //}

        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(1, null, getDevicesLoaderListener);

        return view;
    }

    public class OnListFragmentInteractionListener {
        void onListFragmentInteraction(User item){
            Cursor data = mDatabaseHelper.getItemID(item.getName(), item.getWeight()); //get the id associated with that name
            int itemID = -1;
            while(data.moveToNext()){
                itemID = data.getInt(0);
            }
            if(itemID > -1){
                Intent editScreenIntent = new Intent(getActivity(), UserPage.class);
                editScreenIntent.putExtra("id", itemID);
                editScreenIntent.putExtra("name", item.getName());
                editScreenIntent.putExtra("weight", item.getWeight());
                startActivity(editScreenIntent);
            }
        }
    }

    private LoaderCallbacks<List<User>> getDevicesLoaderListener = new LoaderCallbacks<List<User>>() {
        @NonNull
        @Override
        public Loader<List<User>> onCreateLoader(int id, @Nullable Bundle bundle) {
            return new GetUsers(getActivity());
        }

        @Override
        public void onLoadFinished(@NonNull Loader<List<User>> loader, List<User> users) {
            if (users != null && !users.isEmpty()) {
                mAdapter = new RecyclerViewAdapter(users, mListener);
                recyclerView.setAdapter(mAdapter);
            }
        }

        @Override
        public void onLoaderReset(@NonNull Loader<List<User>> loader) {}
    };

    public ListUsersFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = new OnListFragmentInteractionListener();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
