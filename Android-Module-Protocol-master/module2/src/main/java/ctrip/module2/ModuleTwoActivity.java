package ctrip.module2;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ctrip.business.M;
import ctrip.business.event.EventBus;


public class ModuleTwoActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        Button btn2 = findViewById(R.id.btn_two);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.turn2Acitivity(ModuleTwoActivity.this, M.MODULE1_LOGIN);
            }
        });
    }
}
