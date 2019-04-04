package e.user301.mvpexamle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private Button rButtonSingleActivity;
    private  Button rButtonMVP;
    private Button  rButtonRoomMVP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rButtonSingleActivity = findViewById(R.id.button_single);
        rButtonSingleActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SingleActivity.class));
            }
        });

        rButtonMVP = findViewById(R.id.button_mvp);
        rButtonMVP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MVPActivity.class));
            }
        });
    }
}
