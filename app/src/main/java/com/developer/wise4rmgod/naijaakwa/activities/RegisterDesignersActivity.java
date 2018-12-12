/*
 * Copyright 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://opensource.org/licenses/MIT
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.developer.wise4rmgod.naijaakwa.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ScrollView;
import android.widget.Toast;

import com.developer.wise4rmgod.naijaakwa.R;
import com.developer.wise4rmgod.naijaakwa.databinding.ActivityRegisterdesignersBinding;
import com.developer.wise4rmgod.naijaakwa.presenter.RegisterDesignersPresenter;
import com.developer.wise4rmgod.naijaakwa.view.RegisterDesignersMVP;
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

public class RegisterDesignersActivity extends AppCompatActivity implements RegisterDesignersMVP.view {
    ActivityRegisterdesignersBinding binding;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference().child("NaijaAkwa").child("Designers").child("userid");
    private RegisterDesignersPresenter registerDesignersPresenter;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference().child("NaijaAkwa").child("Designers").child("userid");
    Uri filePath;
    int PICK_IMAGE_REQUEST = 111;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerdesigners);
        getSupportActionBar().hide();

        registerDesignersPresenter = new RegisterDesignersPresenter(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_registerdesigners);
        String statearray[] = getResources().getStringArray(R.array.states_arrays);
        String sexarray[] = getResources().getStringArray(R.array.sex_arrays);
        String specializationarray[] = getResources().getStringArray(R.array.specialization_arrays);
        String levelarray[] = getResources().getStringArray(R.array.level_arrays);


        //Setting the sex ArrayAdapter data on the Spinner
        ArrayAdapter sex = new ArrayAdapter(this, android.R.layout.simple_spinner_item, sexarray);
        sex.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.sexSpinner.setAdapter(sex);
        //Setting the specialization ArrayAdapter data on the Spinner
        ArrayAdapter specialization = new ArrayAdapter(this, android.R.layout.simple_spinner_item, specializationarray);
        specialization.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.specializationSpinner.setAdapter(specialization);
        //Setting the level ArrayAdapter data on the Spinner
        ArrayAdapter level = new ArrayAdapter(this, android.R.layout.simple_spinner_item, levelarray);
        level.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.levelSpinner.setAdapter(level);
        //Setting the state ArrayAdapter data on the Spinner
        ArrayAdapter state = new ArrayAdapter(this, android.R.layout.simple_spinner_item, statearray);
        state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.stateSpinner.setAdapter(state);

        // onclick event on the register button
        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerDesignersPresenter.registerbtn();

            }
        });
        // onclick event on the upload button
        binding.profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerDesignersPresenter.uploadbtn();

            }
        });
    }




    @Override
    public void upload() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                binding.profile.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void register() {
          final String id = getIntent().getStringExtra("id");
           final String email = getIntent().getStringExtra("email");
         String status = getIntent().getStringExtra("status");
         final String password = getIntent().getStringExtra("password");
          final String role = getIntent().getStringExtra("role");
        binding.progressBar.setVisibility(View.VISIBLE);



                          if(filePath != null) {
                           //   binding.progressBar.setVisibility(View.VISIBLE);

                              final StorageReference childRef = storageRef.child("gallery").child("profilepix").child(id);

                              //uploading the image
                              final UploadTask uploadTask = childRef.putFile(filePath);

                              uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                  @Override
                                  public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                childRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        Map<String, String> users = new HashMap<>();
                                        users.put("id", id);
                                        users.put("email", email);
                                        users.put("password", password);
                                        users.put("state", binding.stateSpinner.getSelectedItem().toString());
                                        users.put("sex", binding.sexSpinner.getSelectedItem().toString());
                                        users.put("level", binding.levelSpinner.getSelectedItem().toString());
                                        users.put("specialization", binding.specializationSpinner.getSelectedItem().toString());
                                        users.put("role", role);
                                        users.put("subscription", "on");
                                        users.put("address", binding.address.getText().toString());
                                        users.put("city", binding.city.getText().toString());
                                        users.put("fullname", binding.fullname.getText().toString());
                                        users.put("phone", binding.phone.getText().toString().trim());
                                        users.put("img",uri.toString());
                                        ref.child(id).setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                if (task.isSuccessful()) {
                                                    binding.progressBar.setVisibility(View.GONE);
                                                    Snackbar.make(binding.scrollView, R.string.successful_message, Snackbar.LENGTH_SHORT)
                                                            .show();
                                                    Intent feeds = new Intent(getApplicationContext(), FeedsActivity.class);
                                                    feeds.putExtra("id", id);
                                                    startActivity(feeds);
                                                } else {
                                                    binding.progressBar.setVisibility(View.GONE);
                                                    Snackbar.make(binding.scrollView, R.string.unsuccessful_message, Snackbar.LENGTH_SHORT)
                                                            .show();
                                                }
                                            }
                                        });
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getApplicationContext(), "Error ", Toast.LENGTH_SHORT).show();

                                    }
                                });


                                  }

                              }).addOnFailureListener(new OnFailureListener() {
                                  @Override
                                  public void onFailure(@NonNull Exception e) {
                                      binding.progressBar.setVisibility(View.GONE);
                                      Toast.makeText(getApplicationContext(), "Upload Failed -> " + e, Toast.LENGTH_SHORT).show();
                                  }
                              });
                                      }

                          else {
                              Toast.makeText(getApplicationContext(), "Select an image", Toast.LENGTH_SHORT).show();
                          }

                      }



          }




