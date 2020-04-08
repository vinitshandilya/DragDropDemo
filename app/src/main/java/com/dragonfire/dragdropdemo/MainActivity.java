package com.dragonfire.dragdropdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.graphics.Color;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<TextView> cells = new ArrayList<>();
        cells.add((TextView) findViewById(R.id.text1));
        cells.add((TextView) findViewById(R.id.text2));
        cells.add((TextView) findViewById(R.id.text3));
        cells.add((TextView) findViewById(R.id.text4));
        cells.add((TextView) findViewById(R.id.text5));
        cells.add((TextView) findViewById(R.id.text6));
        cells.add((TextView) findViewById(R.id.text7));
        cells.add((TextView) findViewById(R.id.text8));
        cells.add((TextView) findViewById(R.id.text9));


        for(final TextView cell : cells) {
            cell.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    ClipData data = ClipData.newPlainText("value", cell.getText());
                    cell.startDrag(data, new View.DragShadowBuilder(v), null, 0);
                    return true;
                }
            });

            //on drag listener
            cell.setOnDragListener(new View.OnDragListener() {
                @Override
                public boolean onDrag(View v, DragEvent event) {
                    switch(event.getAction()) {
                        case DragEvent.ACTION_DRAG_STARTED:
                            return true;

                        case DragEvent.ACTION_DRAG_ENTERED:
                            v.setBackgroundColor(Color.LTGRAY);
                            return true;

                        case DragEvent.ACTION_DRAG_LOCATION:
                            return true;

                        case DragEvent.ACTION_DRAG_EXITED:
                            v.setBackgroundColor(Color.TRANSPARENT);
                            return true;

                        case DragEvent.ACTION_DROP:
                            v.setBackgroundColor(Color.TRANSPARENT);
                            int dragVal = Integer.parseInt(event.getClipData().getItemAt(0).getText().toString());
                            int viewVal = Integer.parseInt(((TextView) v).getText().toString());
                            ((TextView) v).setText("" + (dragVal + viewVal));
                            return true;

                        case DragEvent.ACTION_DRAG_ENDED:
                            return true;

                        default:
                            break;
                    }
                    return false;
                }
            });

        }





    }
}
