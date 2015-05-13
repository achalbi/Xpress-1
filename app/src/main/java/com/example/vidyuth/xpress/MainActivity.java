package com.example.vidyuth.xpress;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements OnItemClickListener {
private DrawerLayout leftdrawerlayout;
    private ListView leftlistview;
  //  private DrawerLayout rightdrawerlayout;
    private ListView rightlistview;
    private String[] left;
    private String[] right;
    private ActionBarDrawerToggle drawerlistener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        leftdrawerlayout = (DrawerLayout)findViewById(R.id.drawer_layout);
       // rightdrawerlayout = (DrawerLayout)findViewById(R.id.rightdrawer);
        leftlistview = (ListView)findViewById(R.id.leftdrawer);
        left = getResources().getStringArray(R.array.left);
        leftlistview.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, left)); //Adaptor for array

        rightlistview = (ListView)findViewById(R.id.rightdrawer);
        right = getResources().getStringArray(R.array.right);
        rightlistview.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, right));
        drawerlistener = new ActionBarDrawerToggle(this, leftdrawerlayout, R.string.help, R.string.likes){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Toast.makeText(MainActivity.this,"opened", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Toast.makeText(MainActivity.this,"closed", Toast.LENGTH_LONG).show();
            }
        };
        leftdrawerlayout.setDrawerListener(drawerlistener);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        leftlistview.setOnItemClickListener(this);
    }

 /*   @Override
    public boolean onOptionsItemSelected(MenuItem item){
        return super.onOptionsItemSelected(item);
    }*/

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerlistener.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) { //for orientation changes and all that
        super.onConfigurationChanged(newConfig);
        drawerlistener.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(drawerlistener.onOptionsItemSelected(item)){
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(drawerlistener.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this,left[position]+"selected", Toast.LENGTH_LONG).show();
        setTitle(left[position]);
        selectItem(position);

    }

    public void selectItem(int position) {
        leftlistview.setItemChecked(position, true);
        //setTitle("Works");
    }
}
