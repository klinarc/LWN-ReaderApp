package klinar.kronlachner.binder.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import klinar.kronlachner.binder.myapplication.R;
import klinar.kronlachner.binder.app.MainActivity;

class Article_List_Adapter extends ArrayAdapter<Article>{

    public static final String TAG = "List_Adapter Test";
    public List<Article> articles;

    public Article_List_Adapter(Context _c, int textViewResourceId, List<Article> articles) {
        super(_c, textViewResourceId, articles);
        this.articles = articles;
    }


    @Override
    public View getView(int _position, View _convertView, ViewGroup _parent) {

        //make sure we have a view to work with(may have been given null)
        View article_view = _convertView;
        if(article_view == null){
            LayoutInflater articleInflater = LayoutInflater.from(getContext());
            article_view =  articleInflater.inflate(R.layout.article_list_row, _parent, false);
        }

        //Find the article to work with
        Article currentArticle = articles.get(_position);

        //fill the Article_View
        switch(currentArticle.getCategory()){
            case "Kernel":{
                ImageView article_icon = (ImageView) article_view.findViewById(R.id.article_category_icon);
                article_icon.setImageResource(R.drawable.ic_list_letter_k);
            }break;
            case "Security":{
                ImageView article_icon = (ImageView) article_view.findViewById(R.id.article_category_icon);
                article_icon.setImageResource(R.drawable.ic_list_letter_s);
            }break;
            default: {
                ImageView article_icon = (ImageView) article_view.findViewById(R.id.article_category_icon);
                article_icon.setImageResource(R.drawable.ic_list_letter_s);
                //Log.i(TAG, "unexpected article_category id encountered in article_list_adapter");
            }
        }
        //Title
        TextView article_title = (TextView) article_view.findViewById(R.id.article_title);
        article_title.setText(currentArticle.getTitle());

        //Category
        TextView article_category = (TextView) article_view.findViewById(R.id.article_category);
        article_category.setText(currentArticle.getCategory());

        //Date
        TextView article_date = (TextView) article_view.findViewById(R.id.article_date);
        article_date.setText(currentArticle.getDate());

        //Author
        TextView article_author = (TextView) article_view.findViewById(R.id.article_author);
        article_author.setText(currentArticle.getAuthor());

        return article_view;
    }
}
