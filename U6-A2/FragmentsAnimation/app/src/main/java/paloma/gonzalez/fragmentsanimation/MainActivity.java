package paloma.gonzalez.fragmentsanimation;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnFragmentoA=findViewById(R.id.btnFragmentoA);
        btnFragmentoA.setOnClickListener(new View.OnClickListener() {
            fragment_a fragmentoA=new fragment_a();
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().
                        beginTransaction().
                        setTransition(FragmentTransaction.TRANSIT_ENTER_MASK).
                        replace(R.id.contenedor,fragmentoA).
                        addToBackStack(null).
                        commit();
            }
        });

        Button btnFragmentoB=findViewById(R.id.btnFragmentoB);
        btnFragmentoB.setOnClickListener(new View.OnClickListener() {
            fragment_b fragmentoB=new fragment_b();
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().
                        beginTransaction().
                        setCustomAnimations(R.anim.enter_anim,R.anim.exit_anim).
                        replace(R.id.contenedor,fragmentoB).
                        addToBackStack(null).
                        commit();
            }
        });
    }
}
