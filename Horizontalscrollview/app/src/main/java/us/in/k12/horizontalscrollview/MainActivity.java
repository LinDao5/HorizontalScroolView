package us.in.k12.horizontalscrollview;

import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private HorizontalScrollView horizontalScrollView ;
    private LinearLayout container;
    private String cities[] = new String[]{
            "London", "Bangkok", "Paris", "Dubai", "Istanbul", "New York",
            "Singapore", "Kuala Lumpur", "Hong Kong", "Tokyo", "Barcelona",
            "Vienna", "Los Angeles", "Prague", "Rome", "Seoul", "Mumbai", "Jakarta",
            "Berlin", "Beijing", "Moscow", "Taipei", "Dublin", "Vancouver"};
    private ArrayList<String> data = new ArrayList<>();
    private TextView testTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindData();
        setUIRef();
        bindHZSWData();
    }

    private void setUIRef(){
        horizontalScrollView = (HorizontalScrollView)findViewById(R.id.horizontalScrollView);
        horizontalScrollView.setHorizontalScrollBarEnabled(false);
        container = (LinearLayout)findViewById(R.id.horizontalScrollViewItemContainer);
        testTextView = (TextView)findViewById(R.id.testTextView);
    }

    private void bindHZSWData(){
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.setMargins(20,0,20,10);
        for (int i = 0; i < data.size(); i++){
            TextView textView = new TextView(this);
            textView.setText(data.get(i));
            textView.setTextColor(Color.WHITE);
            textView.setLayoutParams(layoutParams);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    performItemClick(view);
                }
            });
            container.addView(textView);
            container.invalidate();
        }
    }

    private void performItemClick(View view){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int screenWidth = displayMetrics.widthPixels;
        int scrollX = (view.getLeft() - (screenWidth/2) + (view.getWidth()/2));
        horizontalScrollView.smoothScrollTo(scrollX, 0);
        String s = "CenterLocked  Item:" + ((TextView)view).getText();
        testTextView.setText(s);
    }

    private void bindData(){
        Collections.addAll(data, cities);
    }
}
