package com.developer.wise4rmgod.naijaakwa.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.developer.wise4rmgod.naijaakwa.R;
import com.developer.wise4rmgod.naijaakwa.presenter.RegisterBuyerPresenter;
import com.developer.wise4rmgod.naijaakwa.view.RegisterBuyerMVP;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterBuyersActivity extends AppCompatActivity implements RegisterBuyerMVP.view {
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference().child("NaijaAkwa").child("Buyers").child("userid");    //change the url according to your firebase app
    Uri filePath;
    int PICK_IMAGE_REQUEST = 111;
    @BindView(R.id.fullname)
    EditText fullname;
    @BindView(R.id.profile)
    ImageView profile;
    @BindView(R.id.stateSpinner)
    Spinner stateSpinner;
    @BindView(R.id.city)
    EditText city;
    @BindView(R.id.address)
    EditText address;
    @BindView(R.id.sexSpinner)
    Spinner sexSpinner;
    @BindView(R.id.registerbuyers)
    Button registerbuyers;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference().child("NaijaAkwa").child("Buyer").child("userid");
    private RegisterBuyerPresenter registerBuyerPresenter;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_buyers);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        registerBuyerPresenter = new RegisterBuyerPresenter(this);
        String statearray[] = getResources().getStringArray(R.array.states_arrays);
        String sexarray[] = getResources().getStringArray(R.array.sex_arrays);

        ArrayAdapter sex = new ArrayAdapter(this, android.R.layout.simple_spinner_item, sexarray);
        sex.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sexSpinner.setAdapter(sex);

        //Setting the state ArrayAdapter data on the Spinner
        ArrayAdapter state = new ArrayAdapter(this, android.R.layout.simple_spinner_item, statearray);
        state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateSpinner.setAdapter(state);


        // onclick event on the register button
        registerbuyers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerBuyerPresenter.registerbtn();

            }
        });
        // onclick event on the upload button
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerBuyerPresenter.uploadbtn();

            }
        });




    }

    @Override
    public void register() {
        final String id = getIntent().getStringExtra("id");
        final String email = getIntent().getStringExtra("email");
        String status = getIntent().getStringExtra("status");
        final String password = getIntent().getStringExtra("password");
        final String role = getIntent().getStringExtra("role");
       progressBar.setVisibility(View.VISIBLE);




                    if(filePath != null) {
                   //   progressBar.setVisibility(View.VISIBLE);

                        final StorageReference childRef = storageRef.child("gallery").child("profilepix").child(id);

                        //uploading the image
                        UploadTask uploadTask = childRef.putFile(filePath);
                            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {



                                    childRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(final Uri uri) {
                                            final Map<String, String> users = new HashMap<>();
                                            users.put("id", id);
                                            users.put("email", email);
                                            users.put("password", password);
                                            users.put("state", stateSpinner.getSelectedItem().toString());
                                            users.put("sex", sexSpinner.getSelectedItem().toString());
                                            users.put("role", role);
                                            users.put("address", address.getText().toString());
                                            users.put("city", city.getText().toString());
                                            users.put("img",childRef.getDownloadUrl().toString());
                                            users.put("full_name", fullname.getText().toString());

                                            ref.child(id).setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {

                                                        progressBar.setVisibility(View.GONE);
                                                        Snackbar.make(scrollView, R.string.successful_message, Snackbar.LENGTH_SHORT)
                                                                .show();

                                                        Toast.makeText(getApplicationContext(), uri.toString(), Toast.LENGTH_SHORT).show();
                                                        Intent feeds = new Intent(getApplicationContext(), FeedsActivity.class);
                                                        feeds.putExtra("id",id);
                                                        feeds.putExtra("role",role);
                                                        startActivity(feeds);
                                                        Toast.makeText(getApplicationContext(), uri.toString(), Toast.LENGTH_SHORT).show();
                                                    }

                                                    else {
                                                        progressBar.setVisibility(View.GONE);
                                                        Snackbar.make(scrollView, R.string.unsuccessful_message, Snackbar.LENGTH_SHORT)
                                                                .show();
                                                    }
                                                }
                                            });


                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            progressBar.setVisibility(View.GONE);
                                            Toast.makeText(getApplicationContext(), "Upload Failed -> " + e, Toast.LENGTH_SHORT).show();

                                        }
                                    });




                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(), "Upload Failed -> " + e, Toast.LENGTH_SHORT).show();

                                }
                            });

                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Select an image", Toast.LENGTH_SHORT).show();
                    }


                }



    @Override
    public void upload() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                profile.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }





}
