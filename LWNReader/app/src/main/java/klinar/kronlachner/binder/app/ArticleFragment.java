package klinar.kronlachner.binder.app;


import android.app.*;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import klinar.kronlachner.binder.myapplication.R;


public class ArticleFragment extends android.support.v4.app.Fragment{

    private ListView articleListView;
    private List<Article> article_list;

    public ArticleFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);


        article_list = MainActivity.getInstance().getArticleList();
        articleListView = (ListView) view.findViewById(R.id.article_list_view);
        createArticleListView();
        return view;
    }

    public void createArticleListView(){
        ArrayAdapter<Article> article_list_adapter = new Article_List_Adapter(getActivity(), R.layout.article_list_row, article_list);
        articleListView.setAdapter(article_list_adapter);
        articleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
}
