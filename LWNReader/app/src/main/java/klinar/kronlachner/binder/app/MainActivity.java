package klinar.kronlachner.binder.app;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import klinar.kronlachner.binder.myapplication.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final String TAG = "Nav Drawer Test";
    private static MainActivity instance;
    private List<Article> articles;
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;

        //Setup Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Setup Navigation Drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        articles = new ArrayList<>();
        setUpArticleList();

        ArticleFragment articleFragment = new ArticleFragment();
        if (articleFragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().add(R.id.layout_for_fragment, articleFragment, "").commit();
        }
    }

    public static MainActivity getInstance() {
        return instance;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        this.menu = menu;
        menu.setGroupVisible(R.id.all_items, false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_bar_favorite) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.nav_search: {
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                SearchFragment searchFragment = new SearchFragment();
                if (searchFragment != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.layout_for_fragment, searchFragment, "").commit();
                }
                setTitle("Search");
            }
            break;
            case R.id.nav_favorites: {
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                setTitle("Favorites");
            }
            break;
            case R.id.nav_kernel: {
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                KernelFragment kernelFragment = new KernelFragment();
                if (kernelFragment != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.layout_for_fragment, kernelFragment, "").commit();
                }
                setTitle("Kernel");
            }
            break;
            case R.id.nav_security: {
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                setTitle("Security");
            }
            break;
            default:
                Log.i(TAG, "unexpected nav_id click encountered in Navigation Drawer");
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void setUpArticleList() {
        articles.add(new Article("Distributors ponder a system change", "Distributions", "Jun 7, 2016", "corbet"));
        articles.add(new Article("Security updates for Tuesday", "Security", " Jun 21, 2016", "ris"));
        articles.add(new Article("Kernel prepatch 4.7-rc4", "Kernel", "Jun 20, 2016", "jake"));
        articles.add(new Article("Kernel prepatch 4.7-rc4", "Kernel", "Jun 20, 2016", "jake"));
        articles.add(new Article("Kernel prepatch 4.7-rc4", "Kernel", "Jun 20, 2016", "jake"));
        articles.add(new Article("Kernel prepatch 4.7-rc4", "Kernel", "Jun 20, 2016", "jake"));
        articles.add(new Article("Kernel prepatch 4.7-rc4", "Kernel", "Jun 20, 2016", "jake"));
        articles.add(new Article("Kernel prepatch 4.7-rc4", "Kernel", "Jun 20, 2016", "jake"));
        articles.add(new Article("Kernel prepatch 4.7-rc4", "Kernel", "Jun 20, 2016", "jake"));
        articles.add(new Article("Kernel prepatch 4.7-rc4", "Kernel", "Jun 20, 2016", "jake"));
        articles.add(new Article("Kernel prepatch 4.7-rc4", "Kernel", "Jun 20, 2016", "jake"));
        articles.add(new Article("Kernel prepatch 4.7-rc4", "Kernel", "Jun 20, 2016", "jake"));


    }

    public List<Article> getArticleList() {
        return articles;
    }

}
