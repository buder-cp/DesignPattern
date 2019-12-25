package ctrip.module1;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import ctrip.business.event.EventBus;
import ctrip.business.M;

public class ModuleOneActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        Button btn1 = findViewById(R.id.btn_one);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.turn2Acitivity(ModuleOneActivity.this, M.MODULE2_PAY);
            }
        });
    }
}
