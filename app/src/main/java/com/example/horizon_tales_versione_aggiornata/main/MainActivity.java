package com.example.horizon_tales_versione_aggiornata.main;

import androidx.appcompat.app.AppCompatActivity;

/*import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
*/
public class MainActivity extends AppCompatActivity {

/*
public class MainActivity extends AppCompatActivity implements View.OnCreateContextMenuListener, ComicsApiUserFragment.OnComicClickListener {

    private static final String TAG = "MainUserActivity";
    private FragmentManager fragmentManager;
    private GoogleSignInClient mGoogleSignInClient;
    private BottomNavigationView bottomNavigationView;
    private MaterialToolbar toolbar;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private DatabaseReference userRef;
    private boolean isProfileFormComplete = false; //variabile per tracciare se il form del profilo è stato completato

    @Override
    public void onComicClick(Comic comic) {
        openComicDetailFragment(comic);
    }

    private void openComicDetailFragment(Comic comic) {
        ComicsMarvelDetailFragment fragment = new ComicsMarvelDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable("comic", comic);
        fragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void configureGoogleSignIn(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void signOutFromGoogle() {
        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(this, new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build());
        googleSignInClient.signOut().addOnCompleteListener(this, task -> Log.d("GoogleSignOut", "User signed out from Google"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureGoogleSignIn();

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        toolbar = findViewById(R.id.top_appbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setBackground(null);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.navigation_home) {
                    openFragment(new HomeFragment());
                    return true;
                } else if (id == R.id.navigation_category_user) {
                    openFragment(new CategoryUserFragment());
                    return true;
                } else if (id == R.id.navigation_list_comics_user) {
                    openFragment(new ComicsPdfListUserFragment());
                    return true;
                } else if (id == R.id.navigation_detail_comics_user) {
                    openFragment(new ComicsPdfDetailUserFragment());
                    return true;
                } else if (id == R.id.navigation_view_comics_user) {
                    openFragment(new ComicsPdfViewUserFragment());
                    return true;
                } else if (id == R.id.navigation_preferiti) {
                    openFragment(new PreferitiFragment());
                    return true;
                } else if (id == R.id.navigation_ricerca) {
                    openFragment(new ComicsInfoFragment());
                    return true;
                } else if (id == R.id.navigation_profile) {
                    openFragment(new ProfileFragment());
                    return true;
                } else if (id == R.id.searchUserFragment) {
                    openFragment(new SearchUserFragment());
                    return true;
                } else if (id == R.id.chatsFragment) {
                    openFragment(new ChatsFragment());
                    return true;
                } else if (id == R.id.detailUserProfileFragment) {
                    openFragment(new DetailUserProfileFragment());
                    return true;
                } else if (id == R.id.navigation_character_info) {
                    openFragment(new CharacterInfoFragment());
                    return true;
                } else if (id == R.id.navigation_comics_avanzato) {
                    openFragment(new ComicsAvanzatoInfoFragment());
                    return true;
                } else if (id == R.id.nav_marvel_comics_detail) {
                    openFragment(new ComicsMarvelDetailFragment());
                    return true;
                } else if (id == R.id.navigation_api_comics_user) {
                    openFragment(new ComicsApiUserFragment());
                    return true;
                } else if (id == R.id.navigation_comics_user) {
                    openFragment(new ComicsUserFragment());
                    return true;
                }
                return false;
            }
        });

        fragmentManager = getSupportFragmentManager();
        if (currentUser == null) {
            navigateToLogin();
        } else {
            if (savedInstanceState == null) {
                openFragment(new HomeFragment());
            }
        }
        if(currentUser == null){
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }

        // Verifica se l'intent contiene l'extra per mostrare direttamente il ProfileFragment
        if (getIntent().getBooleanExtra("showProfileFragment", false)) {
            openFragment(new ProfileFragment());
            // Imposta anche l'elemento della BottomNavigationView su quello corrispondente, se necessario
            bottomNavigationView.setSelectedItemId(R.id.navigation_profile);
        }
    }

    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_custom_icon) {
            View menuItemView = findViewById(R.id.action_custom_icon);
            PopupMenu popupMenu = new PopupMenu(this, menuItemView);
            popupMenu.inflate(R.menu.popup_menu);
            popupMenu.setOnMenuItemClickListener(this::handleMenuSelection);
            popupMenu.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean handleMenuSelection(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.navigation_category_user) {
            openFragment(new CategoryUserFragment());
            //return true;
        } else if (id == R.id.searchUserFragment) {
            openFragment(new SearchUserFragment());
            //return true;
        } else if (id == R.id.chatsFragment) {
            openFragment(new ChatsFragment());
            //return true;
        } else if (id == R.id.navigation_character_info) {
            openFragment(new CharacterInfoFragment());
            //return true;
        } else if (id == R.id.nav_marvel_comics_detail) {
            openFragment(new ComicsMarvelDetailFragment());
            //return true;
        } else if (id == R.id.navigation_api_comics_user) {
            openFragment(new ComicsApiUserFragment());
            //return true;
        } else if (id == R.id.navigation_comics_user) {
            openFragment(new ComicsUserFragment());
            //return true;
        } else if (id == R.id.navigation_logout) {
            // Effettua il logout da Firebase Auth
            FirebaseAuth.getInstance().signOut();
            // Effettua il logout da Google Sign-In
            signOutFromGoogle();
            // Passa alla LoginActivity
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void navigateToLogin() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }*/
}