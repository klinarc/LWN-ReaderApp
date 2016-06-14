package klinar.kronlachner.binder.app;


import android.app.*;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import klinar.kronlachner.binder.myapplication.R;


public class ArticleFragment extends ListFragment {

    public ArticleFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createArticleListView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        return view;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        createArticleListView();

    }


    @Override
    public void onStart() {
        super.onStart();
    }

    private void createArticleListView(){
        ArrayAdapter<Article> article_list_adapter = new Article_List_Adapter();
        ListView article_list_view = (ListView) MainActivity.getInstance().findViewById(R.id.article_list_view);
        article_list_view.setAdapter(article_list_adapter);

        article_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "Item: ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
