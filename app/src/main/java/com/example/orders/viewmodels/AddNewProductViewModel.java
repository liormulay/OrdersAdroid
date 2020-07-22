package com.example.orders.viewmodels;

import android.net.Uri;

import androidx.lifecycle.ViewModel;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class AddNewProductViewModel extends ViewModel {

    public void onSubmit(String productName, int productQuantity, float productPrice, Uri productImageUri) {
        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        StorageReference riversRef = storageRef.child(productName + ".jpg");

        riversRef.putFile(productImageUri).continueWithTask(task -> {
            if (!task.isSuccessful()) {
                throw task.getException();
            }

            // Continue with the task to get the download URL
            //change made here
            return riversRef.getDownloadUrl();
        }).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Uri downloadUri = task.getResult();
                System.out.println("Upload success: " + downloadUri);
            } else {
                // Handle failures
                // ...
            }
        });
    }
}
