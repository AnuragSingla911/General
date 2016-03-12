package example.general.android.com.generalexample;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import example.general.android.com.generalexample.modal.MainModal;
import example.general.android.com.generalexample.modal.Section;
import example.general.android.com.generalexample.ui.layoutmanager.ParentRecycleAdapter;
import example.general.android.com.generalexample.ui.layoutmanager.WrapLinearLayoutManager;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private MainModal modal;
    private RecyclerView mParentRecyclerView;

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null && getArguments().containsKey("modal")){
            modal = getArguments().getParcelable("modal");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_main, container, false);
        mParentRecyclerView = (RecyclerView)view.findViewById(R.id.parentRecyclerview);
        mParentRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mParentRecyclerView.setAdapter(new ParentRecycleAdapter(getActivity(),modal));
        return view;
    }
}
