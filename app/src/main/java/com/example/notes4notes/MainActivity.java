package com.example.notes4notes;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.example.notes4notes.Fragments.ComposeFragment;
import com.example.notes4notes.Fragments.HomeFragment;
import com.example.notes4notes.Fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setBottomNavigationView();
    } // end of onCreate



    private void setBottomNavigationView(){

        /**Bottom Navigation Variables*/
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        //bottomNavigationView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            final FragmentManager fragmentManager = getSupportFragmentManager();
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                final Fragment composeFragment = new ComposeFragment();
                final Fragment homeFragment   = new HomeFragment    ();
                final Fragment profileFragment = new ProfileFragment();

                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        fragmentManager.beginTransaction().replace(R.id.flContainer,homeFragment).commit();
                        break;
                    case R.id.action_compose:
                        fragmentManager.beginTransaction().replace(R.id.flContainer,composeFragment).commit();
                        break;
                    case R.id.action_profile:
                        fragmentManager.beginTransaction().replace(R.id.flContainer,profileFragment).commit();
                        break;
                    default:
                        return true;
                }
                return true;
            } // onNavigationItemSelected
        }); //setOnNavigationItemSelectedListener
        // Default view
        bottomNavigationView.setSelectedItemId(R.id.action_home);
    } // end of method setBottomNavigationView.
}// end of Class
