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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.developer.wise4rmgod.naijaakwa.R;
import com.developer.wise4rmgod.naijaakwa.presenter.UploadDesignerGalleryPresenter;
import com.developer.wise4rmgod.naijaakwa.view.UploadDesignerGalleryMVP;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class UploadDesignersGallery extends AppCompatActivity implements UploadDesignerGalleryMVP.view {
  private UploadDesignerGalleryPresenter uploadDesignerGalleryPresenter;
  @BindView(R.id.uploadgallerycategory)
    EditText uploadgallerycategory;
    @BindView(R.id.uploadgallerydescription)
    EditText uploadgallerydescription;
    @BindView(R.id.uploadgallerytitle)
    EditText uploadgallerytitle;
    @BindView(R.id.uploadgalleryprofileimg)
    CircleImageView uploadgalleryprofileimg;
  @BindView(R.id.uploadgallerypostbtn)
    Button uploadgallerypostbtn;
  @BindView(R.id.chooseuploadimg)
  ImageView chooseuploadimg;
  FirebaseStorage storage = FirebaseStorage.getInstance();
  StorageReference storageRef = storage.getReference().child("NaijaAkwa").child("userid");
  FirebaseDatabase database = FirebaseDatabase.getInstance();
  DatabaseReference ref = database.getReference().child("NaijaAkwa").child("userid");
  Uri filePath;
  String id;
  String dateToStr;
  int PICK_IMAGE_REQUEST = 111;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_designers_gallery);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        id = getIntent().getStringExtra("id");
        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        dateToStr = format.format(today);

        uploadDesignerGalleryPresenter = new UploadDesignerGalleryPresenter(this);
        uploadgallerypostbtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
           uploadDesignerGalleryPresenter.postgallerybtn();
          }
        });

        chooseuploadimg.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            uploadDesignerGalleryPresenter.pickimagebtn();
          }
        });
    }

    @Override
    public void pickimage() {
      Intent intent = new Intent();
      intent.setType("image/*");
      intent.setAction(Intent.ACTION_PICK);
      intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
      startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);
    }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
      filePath = data.getData();
      try {
        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
        uploadgalleryprofileimg.setImageBitmap(bitmap);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

    @Override
    public void postgallery() {
      final String pushid= ref.push().getKey();

     // binding.progressBar.setVisibility(View.VISIBLE);

      if(filePath != null) {
        //   binding.progressBar.setVisibility(View.VISIBLE);

        final StorageReference childRef = storageRef.child("gallery").child(id).child(pushid);

        //uploading the image
        final UploadTask uploadTask = childRef.putFile(filePath);

        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
          @Override
          public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
            childRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
              @Override
              public void onSuccess(Uri uri) {

                Map<String, String> users = new HashMap<>();
                users.put("id", pushid);
                users.put("userid",id);
                users.put("title", uploadgallerytitle.getText().toString());
                users.put("description", uploadgallerydescription.getText().toString());
                users.put("date",dateToStr);
                users.put("img",uri.toString());
                ref.child(id).child("Gallery").child(pushid).setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                  @Override
                  public void onComplete(@NonNull Task<Void> task) {

                    if (task.isSuccessful()) {
                    //  binding.progressBar.setVisibility(View.GONE);

                      Toast.makeText(getApplicationContext(), "Post Successful", Toast.LENGTH_SHORT).show();

                    } else {
                    //  binding.progressBar.setVisibility(View.GONE);
                      Toast.makeText(getApplicationContext(), "Error Posting Image ", Toast.LENGTH_SHORT).show();
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
           // binding.progressBar.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Upload Failed -> " + e, Toast.LENGTH_SHORT).show();
          }
        });
      }

      else {
        Toast.makeText(getApplicationContext(), "Select an image", Toast.LENGTH_SHORT).show();
      }



    }
}
