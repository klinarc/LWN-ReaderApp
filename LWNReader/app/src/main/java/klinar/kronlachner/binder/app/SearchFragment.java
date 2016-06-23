package klinar.kronlachner.binder.app;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import klinar.kronlachner.binder.myapplication.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {
    private ListView articleListView;
    private List<Article> articles;
    private SearchView inputSearch;
    private ArrayAdapter<Article> article_list_adapter;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);
        articles = MainActivity.getInstance().getArticleList();

        articleListView = (ListView) view.findViewById(R.id.search_list_view);
        inputSearch = (SearchView) view.findViewById(R.id.inputSearch);

        //Create and Set Adapter for ListView
        article_list_adapter = new Article_List_Adapter(getActivity(), R.layout.article_list_row, articles);
        articleListView.setAdapter(article_list_adapter);

        //Set Property for SearchView
        inputSearch.setQueryHint("Search");

        //SEARCH
        inputSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                article_list_adapter.getFilter().filter(query);
                return false;
            }
        });
        return view;
    }
}
