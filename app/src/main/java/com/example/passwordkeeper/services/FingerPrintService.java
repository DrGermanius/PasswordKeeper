package com.example.passwordkeeper.services;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.passwordkeeper.activites.MainActivity;

import java.util.concurrent.Executor;

import androidx.annotation.NonNull;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;


public class FingerPrintService {
    public BiometricPrompt.PromptInfo promptInfo;

    private Executor executor;
    private BiometricPrompt biometricPrompt;

    public FingerPrintService(Context context, FragmentActivity fragmentActivity) {
        Intent intent = new Intent(context, MainActivity.class);
        executor = ContextCompat.getMainExecutor(context);

        biometricPrompt = new BiometricPrompt(fragmentActivity,
                executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode,
                                              @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(context,
                        "Authentication error: " + errString, Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onAuthenticationSucceeded(
                    @NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                context.startActivity(intent);
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(context, "Authentication failed",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric login")
                .setSubtitle("Log in using your biometric credential")
                .setNegativeButtonText("Cancel")
                .build();

    }

    public void auth() {
        biometricPrompt.authenticate(promptInfo);
    }
}
