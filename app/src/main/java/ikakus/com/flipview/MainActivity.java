package ikakus.com.flipview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FlipView flipView = (FlipView) findViewById(R.id.flipView);
        flipView.setViewA(new ViewInput(this));
        flipView.setViewB(new ViewDummy(this,Color.GREEN));
        Button toggle = (Button) findViewById(R.id.toggle);
        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipView.toggle();
            }
        });
    }
}
