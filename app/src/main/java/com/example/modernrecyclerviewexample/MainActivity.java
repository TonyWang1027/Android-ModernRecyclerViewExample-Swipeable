package com.example.modernrecyclerviewexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yanzhenjie.recyclerview.*;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /* Fields */
    // Contains recycler view object
    private SwipeRecyclerView mSwipeRecyclerView;

    // Responsible for laying out each item in the view (align single item in the list)
    private RecyclerView.LayoutManager mLayoutManager;

    // Bridge between ArrayList and RecycleView
    private RecyclerViewAdapter mRecyclerViewAdapter;

    // Holds the list view menu creator object
    private SwipeMenuCreator mSwipeMenuCreator;

    // Create a list of ExampleItem list
    private ArrayList<ExampleItem> mExampleItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addItemToList();

        // Bind Java object with XML layout file
        mSwipeRecyclerView = findViewById(R.id.recyclerView);

        // Increase performance of the RecyclerView, cause RecyclerView will not change the size
        mSwipeRecyclerView.setHasFixedSize(true);

        // The 'LinearLayoutManager' allows you to specify an orientatio
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerViewAdapter = new RecyclerViewAdapter(mExampleItemList);

        // set layout manager
        mSwipeRecyclerView.setLayoutManager(mLayoutManager);

        // start to create menu items
        mSwipeMenuCreator = new SwipeMenuCreator() {
            /*
            * To add menu item to the left of the cardView, use leftMenu variable
            * To add menu item to the right of the cardView, use rightMenu variable
            * */
            @Override
            public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int position) {
                /*
                * initialize each menu item width and height
                *
                * 1. MATCH_PARENT - auto adapt the height to parent's height
                * 2. specify height - ie. 80
                * */

                // For menu item width, set to 80dp - this value is coming from dimens.xml file
                int width = getResources().getDimensionPixelSize(R.dimen.dp_80);

                // For menu item height, set to match parent height, which is the height of each individual card item
                int height = ViewGroup.LayoutParams.MATCH_PARENT;

                // create a menu item
                SwipeMenuItem deleteItem = new SwipeMenuItem(MainActivity.this).setBackground(R.color.red)
                        .setText("Remove")
                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(height);

                // add a item to the right menu
                rightMenu.addMenuItem(deleteItem);
            }
        };

        // set swipe menu creator
        mSwipeRecyclerView.setSwipeMenuCreator(mSwipeMenuCreator);

        // RecyclerView item click listener - when one of the item in RecyclerView is clicked, this method will be called
        mSwipeRecyclerView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int adapterPosition) {
                // adapterPosition - correspond to which item user is clicked
                Toast.makeText(getApplicationContext(), "You clicked item number " + adapterPosition + " row", Toast.LENGTH_SHORT).show();
            }
        });

        // RecyclerView menu item click listener
        mSwipeRecyclerView.setOnItemMenuClickListener(new OnItemMenuClickListener() {
            @Override
            public void onItemClick(SwipeMenuBridge menuBridge, int adapterPosition) {
                menuBridge.closeMenu();

                // right menu or left menu
                int direction = menuBridge.getDirection();
                // which menu item
                int menuPosition = menuBridge.getPosition();

                String message = "row number: " + adapterPosition + ", item number: " + menuPosition;
                if (direction == SwipeRecyclerView.RIGHT_DIRECTION) {
                    // if user clicked item on the right menu
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                } else if (direction == SwipeRecyclerView.LEFT_DIRECTION) {
                    // if user clicked item on the left menu
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                }
            }
        });

        // set adapter - this must write below the OnClickListener, otherwise an error will occur
        mSwipeRecyclerView.setAdapter(mRecyclerViewAdapter);
    }

    public void addItemToList() {
        ExampleItem item1 = new ExampleItem("Chat Group 0", "UserName: message");
        mExampleItemList.add(item1);
        ExampleItem item2 = new ExampleItem("Chat Group 1", "UserName: message");
        mExampleItemList.add(item2);
        ExampleItem item3 = new ExampleItem("Chat Group 2", "UserName: message");
        mExampleItemList.add(item3);
        ExampleItem item4 = new ExampleItem("Chat Group 3", "UserName: message");
        mExampleItemList.add(item4);
    }
}