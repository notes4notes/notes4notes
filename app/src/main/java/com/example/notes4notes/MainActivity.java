package com.example.notes4notes;

import android.content.pm.PackageManager;
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
    int permsRequestCode = 66;
    String[] perms = {
            "android.permission.READ_INTERNAL_STORAGE",
            "android.permission.WRITE_INTERNAL_STORAGE",
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestPermissions(perms,permsRequestCode);
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
                final Fragment homeFragment    = new HomeFragment    ();
                final Fragment profileFragment = new ProfileFragment();

                // Default
                fragmentManager.beginTransaction().replace(R.id.flContainer,homeFragment).commit();
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

    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults) {

        switch (permsRequestCode) {
            case 66:
                boolean readExternal = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean writeExternal = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                boolean readInternal = grantResults[2] == PackageManager.PERMISSION_GRANTED;
                boolean writeInternal = grantResults[3] == PackageManager.PERMISSION_GRANTED;
                break;
        }
    }

}// end of Class
