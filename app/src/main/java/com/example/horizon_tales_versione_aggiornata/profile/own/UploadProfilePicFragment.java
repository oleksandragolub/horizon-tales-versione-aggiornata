package com.example.horizon_tales_versione_aggiornata.profile.own;
/*
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;*/


import androidx.fragment.app.Fragment;

public class UploadProfilePicFragment extends Fragment {

   /* private static final int STORAGE_PERMISSION_REQUEST_CODE = 101;
    private static final String TAG = "UploadProfilePicFragment";
    private Uri imageUri = null;
    private ProgressDialog progressDialog;

    FragmentUploadProfilePicBinding binding;
    StorageReference storageReference;
    ImageView imageViewUploadPic;
    TextView btnBack;
    Button buttonUploadPicChoose, buttonUploadPic;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;

    private final ActivityResultLauncher<Intent> galleryActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            imageUri = data.getData();
                            Log.d(TAG, "onActivityResult: Picked From Gallery " + imageUri);
                            Glide.with(getActivity()).load(imageUri).into(binding.imageViewProfileDp);
                        } else {
                            Log.e(TAG, "onActivityResult: Data intent is null");
                            Toast.makeText(getActivity(), "Failed to select image", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );

    private final ActivityResultLauncher<Intent> cameraActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Log.d(TAG, "onActivityResult: " + imageUri);
                        Glide.with(getActivity()).load(imageUri).into(binding.imageViewProfileDp);
                    } else {
                        Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentUploadProfilePicBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        loadUserInfo();

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Aspetta per favore");
        progressDialog.setCanceledOnTouchOutside(false);

        buttonUploadPicChoose = binding.btnScegliImmagine;
        buttonUploadPic = binding.btnCaricaImmagine;
        imageViewUploadPic = binding.imageViewProfileDp;

        //buttonUploadPicChoose.setOnClickListener(v -> showImageAttachMenu());
        buttonUploadPicChoose.setOnClickListener(v -> {
            showImageAttachMenu();

        });

        buttonUploadPic.setOnClickListener(v -> {
            if (imageUri != null) {
                uploadImage();
            } else {
                Toast.makeText(getActivity(), "Nessuna immagine selezionata", Toast.LENGTH_SHORT).show();
            }
        });

        binding.txtBack.setOnClickListener(v -> {
            if (getActivity() != null) {
                openFragment(new ProfileFragment());
            }
        });

        return root;
    }

   /* @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_REQUEST_CODE) {
            Map<String, Integer> perms = new HashMap<>();
            perms.put(android.Manifest.permission.CAMERA, PackageManager.PERMISSION_GRANTED);
            perms.put(android.Manifest.permission.READ_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
            perms.put(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
            for (int i = 0; i < permissions.length; i++) {
                perms.put(permissions[i], grantResults[i]);
            }
            if (perms.get(android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
                    perms.get(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                    perms.get(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                showImageAttachMenu();
            } else {
                Toast.makeText(getActivity(), "Alcuni permessi non sono stati concessi. Impossibile procedere.", Toast.LENGTH_SHORT).show();
            }
        }
    }*/
  /*  private void uploadImage() {
        progressDialog.setMessage("Updating profile image");
        progressDialog.show();

        String filePathAndName = "ProfileImages/" + mAuth.getUid();

        StorageReference reference = FirebaseStorage.getInstance().getReference(filePathAndName);
        reference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Log.d(TAG, "onSuccess: Profile image uploaded");
                Log.d(TAG, "onSuccess: Getting url of uploaded image");

                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isSuccessful());
                String uploadedImageUrl = "" + uriTask.getResult();

                Log.d(TAG, "onSuccess: Uploaded Image URL: " + uploadedImageUrl);

                updateProfile(uploadedImageUrl);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "onFailure: Failed to upload image due to " + e.getMessage());
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Failed to upload image due to " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateProfile(String uploadedImageUrl) {
        Log.d(TAG, "updateProfile: Updating user profile");
        progressDialog.setMessage("Updating user profile...");
        progressDialog.show();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("profileImage", uploadedImageUrl);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Utenti registrati");
        databaseReference.child(mAuth.getUid()).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d(TAG, "onSuccess: Profile updated...");
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Profile updated...", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "onFailure: Failed to update db due to " + e.getMessage());
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Failed to update db due to " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadUserInfo() {
        Log.d(TAG, "loadUserInfo: Loading user info of user " + mAuth.getUid());

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Utenti registrati");
        reference.child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String profileImage = "" + snapshot.child("profileImage").getValue();
                if (profileImage != null && !profileImage.equals("null")) {
                    Glide.with(getActivity()).load(profileImage).placeholder(R.drawable.profile_icone).into(binding.imageViewProfileDp);
                } else {
                    Glide.with(getActivity()).load(R.drawable.profile_icone).into(binding.imageViewProfileDp);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "Failed to load user info", Toast.LENGTH_SHORT).show();
            }
        });
    }*/

   /* private void showImageAttachMenu() {
        PopupMenu popupMenu = new PopupMenu(getActivity(), binding.btnScegliImmagine);
        popupMenu.getMenu().add(Menu.NONE, 0, 0, "Camera");
        popupMenu.getMenu().add(Menu.NONE, 1, 1, "Galleria");

        popupMenu.show();

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int which = item.getItemId();
                if (which == 0) {
                    pickImageCamera();

                } else if (which == 1) {
                    pickImageGallery();
                }

                return false;
            }
        });
    }*/

   /* private void showImageAttachMenu() {
        if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            // Permissions are already granted, show the image attach menu
            displayImageAttachMenu();
        } else {
            // Request necessary permissions
            requestPermissions(new String[]{android.Manifest.permission.CAMERA, android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED && grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                // All requested permissions are granted
                displayImageAttachMenu();
            } else {
                // Permissions were not granted
                Toast.makeText(getActivity(), "Permission denied. Unable to proceed.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void displayImageAttachMenu() {
        PopupMenu popupMenu = new PopupMenu(getActivity(), binding.btnScegliImmagine);
        popupMenu.getMenu().add(Menu.NONE, 0, 0, "Camera");
        popupMenu.getMenu().add(Menu.NONE, 1, 1, "Galleria");
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int which = item.getItemId();
                if (which == 0) {
                    pickImageCamera();
                } else if (which == 1) {
                    pickImageGallery();
                }
                return false;
            }
        });
    }



    private void pickImageGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        galleryActivityResultLauncher.launch(intent);
    }

    private void pickImageCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Pick");
        values.put(MediaStore.Images.Media.DESCRIPTION, "Sample Image Description");
        imageUri = getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        cameraActivityResultLauncher.launch(intent);
    }

    private void openFragment(Fragment fragment) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }*/
}
