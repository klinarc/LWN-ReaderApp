package klinar.kronlachner.binder.app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import klinar.kronlachner.binder.myapplication.R;
import klinar.kronlachner.binder.app.MainActivity;

class Article_List_Adapter extends ArrayAdapter<Article> implements Filterable {
    public static final String TAG = "List_Adapter Test";
    public List<Article> articles;
    public List<Article> storedArticles;
    CustomFilter filter;

    public Article_List_Adapter(Context _c, int textViewResourceId, List<Article> articles) {
        super(_c, textViewResourceId, articles);
        this.articles = articles;
        this.storedArticles = new ArrayList<Article>(articles);
    }

    public class ViewHolder {
        ImageView article_icon;
        TextView articleTitle;
        TextView articleCategory;
        TextView articleDate;
        TextView articleAuthor;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return articles.size();
    }
    @Override
    public Article getItem(int pos) {
        // TODO Auto-generated method stub
        return articles.get(pos);
    }
    @Override
    public long getItemId(int pos) {
        // TODO Auto-generated method stub
        return articles.indexOf(getItem(pos));
    }

    @Override
    public View getView(int _position, View _convertView, ViewGroup _parent) {
        View view = _convertView;
        ViewHolder viewHolder;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.article_list_row, null);
            viewHolder = new ViewHolder();
            viewHolder.article_icon = (ImageView) view.findViewById(R.id.article_category_icon);
            viewHolder.articleTitle = (TextView) view.findViewById(R.id.article_title);
            viewHolder.articleCategory = (TextView) view.findViewById(R.id.article_category);
            viewHolder.articleDate = (TextView) view.findViewById(R.id.article_date);
            viewHolder.articleAuthor = (TextView) view.findViewById(R.id.article_author);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) _convertView.getTag();
        }

        //Find the article to work with
        //Article currentArticle = getItem(_position);
        Article currentArticle = articles.get(_position);

        //fill the Article_View
        switch (currentArticle.getCategory()) {
            case "Kernel": {
                viewHolder.article_icon.setImageResource(R.drawable.ic_list_letter_k);
            }
            break;
            case "Security": {
                viewHolder.article_icon.setImageResource(R.drawable.ic_list_letter_s);
            }
            break;
            default: {
                viewHolder.article_icon.setImageResource(R.drawable.ic_list_letter_s);
            }
        }
        viewHolder.articleTitle.setText(currentArticle.getTitle());
        viewHolder.articleCategory.setText(currentArticle.getCategory());
        viewHolder.articleDate.setText(currentArticle.getDate());
        viewHolder.articleAuthor.setText(currentArticle.getAuthor());
        return view;
    }

    public Filter getFilter() {
        if (filter == null) {
            filter = new CustomFilter();
        }
        return filter;
    }

    class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence _constraint) {
            FilterResults results = new FilterResults();

            if(_constraint == null || _constraint.length() == 0){
                results.values = storedArticles;
                results.count = storedArticles.size();
            }else{
                List<Article> filteredArticles = new ArrayList<Article>();
                articles = storedArticles;

                for (Article a : articles) {
                    if (a.getTitle().toUpperCase().contains(_constraint.toString().toUpperCase())) {
                        filteredArticles.add(a);
                    }
                }
                results.values = filteredArticles;
                results.count = filteredArticles.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count > 0) {
                articles = (ArrayList<Article>) results.values;
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    }
}
