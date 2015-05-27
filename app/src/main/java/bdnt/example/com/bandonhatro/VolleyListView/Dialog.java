package bdnt.example.com.bandonhatro.VolleyListView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rey.material.widget.Slider;

import bdnt.example.com.bandonhatro.R;

public class Dialog extends Activity {
    Button btnClose;
    Button btnOke;
     TextView tv_continuous;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        btnClose= (Button) findViewById(R.id.btnClose);
        btnOke = (Button) findViewById(R.id.btnOke);
        Slider sl_continuous = (Slider)findViewById(R.id.slider);
         tv_continuous = (TextView) findViewById(R.id.slider_tv_continuous);
        tv_continuous.setText("0 Triệu");
        sl_continuous.setOnPositionChangeListener(new Slider.OnPositionChangeListener() {
            @Override
            public void onPositionChanged(Slider view, float oldPos, float newPos, int oldValue, int newValue) {
                float value=newValue;
                tv_continuous.setText(value/10+" Triệu");
            }
        });
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();;
            }
        });
        btnOke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();;
            }
        });
    }


}
