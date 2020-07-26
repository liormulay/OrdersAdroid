package com.example.orders.viewmodels;

import android.content.Context;
import android.net.Uri;

import androidx.lifecycle.ViewModel;

import com.example.orders.model.Product;
import com.example.orders.network.NetworkClient;
import com.example.orders.utils.SharedPreferencesUtils;
import com.example.orders.views.activities.SingUpActivity;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;

public class AddNewProductViewModel extends ViewModel {

    private BehaviorSubject<String> firebaseImageUrl = BehaviorSubject.create();

    public Completable onSubmit(Context context, String productName, int productQuantity, float productPrice, Uri productImageUri) {
        if (productImageUri != null) {
            uploadImageToFirebase(productName, productImageUri);
        } else {
            firebaseImageUrl.onNext("");
        }
        return firebaseImageUrl.firstOrError()
                .flatMapCompletable(imageUrl -> {
                    imageUrl = imageUrl.isEmpty() ? null : imageUrl;
                    Product product = new Product(productName, productPrice, productQuantity, imageUrl);
                    return NetworkClient.getNetworkInterface()
                            .addNewProduct(SharedPreferencesUtils.retrieveToken(context), product)
                            .subscribeOn(Schedulers.io());
                })
                .subscribeOn(Schedulers.io());

    }

    private void uploadImageToFirebase(String productName, Uri productImageUri) {
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
                firebaseImageUrl.onNext(downloadUri.toString());
                System.out.println("Upload success: " + downloadUri);
            } else {
                // Handle failures
                // ...
            }
        });
    }
}
