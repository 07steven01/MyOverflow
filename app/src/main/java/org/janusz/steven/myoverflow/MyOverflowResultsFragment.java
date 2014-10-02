package org.janusz.steven.myoverflow;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.janusz.steven.myoverflow.stackoverflow.StackApi;
import org.janusz.steven.myoverflow.stackoverflow.StackOverflowRestAdapter;
import org.janusz.steven.myoverflow.stackoverflow.data.ItemContainer;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import timber.log.Timber;

/**
 * Created by marek on 02.10.14.
 */
public class MyOverflowResultsFragment extends Fragment {

    @InjectView(R.id.item_list)
    ListView mStackItemListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_results, null);
        ButterKnife.inject(this, layout);
        return layout;
    }

    @Override
    public void onResume() {
        super.onResume();
        StackOverflowRestAdapter.getAdapter().create(StackApi.class)
                .getUnansweredQuestions("Bearer " + ((App) getActivity().getApplication()).
                        getAccessToken(), new Callback<ItemContainer>() {
                    @Override
                    public void success(ItemContainer itemContainer, Response response) {
                        mStackItemListView.setAdapter(new StackItemAdapter(getActivity(), itemContainer.getItems()));
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Timber.d("Error " + error.getMessage());
                    }
                });
    }
}
