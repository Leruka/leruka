package com.leruka.leruka.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.support.v7.widget.Toolbar;

import com.leruka.leruka.R;
import com.leruka.leruka.helper.Message;
import com.leruka.leruka.main.Central;
import com.leruka.leruka.user.LoginResult;
import com.leruka.leruka.user.Register;
import com.leruka.leruka.user.LUser;
import com.leruka.protobuf.ErrorCodes;
import com.leruka.protobuf.User;

public class RegisterActivity extends LerukaActivity {

    private EditText inputName;
    private EditText inputPass1;
    private EditText inputPass2;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.inputName = (EditText) findViewById(R.id.inputUsername);
        this.inputPass1 = (EditText) findViewById(R.id.inputPassword1);
        this.inputPass2 = (EditText) findViewById(R.id.inputPassword2);

        // init loading animation
        this.progressDialog = new ProgressDialog(this);
        this.progressDialog.setTitle("Account erstellen");
        this.progressDialog.setMessage("Deine Eingaben werden überprüft und dein Account wird erstellt...");

    }

    // Spinner
    public void showProgressDialog() {
        this.progressDialog.show();
    }

    public void hideProgressDialog() {
        this.progressDialog.dismiss();
    }

    public void onRegister(View view) {

        // Get the username and password
        String name = this.inputName.getText().toString();
        String pass1 = this.inputPass1.getText().toString();
        String pass2 = this.inputPass2.getText().toString();

        // Trigger register in the backend
        LoginResult result = Register.register(name, pass1, pass2);

        // When the request has not been send, show an error
        if (!result.isSuccess()) {
            Message.showErrorMessage(result.getMessage());
        }
        else {
            // show spinner, if send
            this.showProgressDialog();
        }
    }

    private void goToMainActivity() {
        // Go to the main menu
        Intent intent = new Intent(this, UserMainActivity.class);
        startActivity(intent);
    }

    // Static


    public static void processResponse(User.ResponseRegister register) {
        // Check for success
        if (register != null && register.getSuccess()) {
            RegisterActivity.receiveSuccessRegister(register);
        }
        else {
            RegisterActivity.receiveFailedRegister(register);
        }
    }

    private static void receiveSuccessRegister(User.ResponseRegister register) {

        // If still in this activity, show dialog
        Activity currentActivity = Central.getCurrentActivity();
        if (currentActivity.getClass().equals(RegisterActivity.class)) {
            RegisterActivity activity = ((RegisterActivity) currentActivity);
            activity.showDialog();
        }
        else {
            // Show a success message
            Message.showMessage("Registrierung war erfolgreich");
        }

        // Try to login
        LoginActivity.processResponse(register.getLogin());
    }

    private static void receiveFailedRegister(User.ResponseRegister register) {
        // If still in this activity, hide the spinner
        Activity currentActivity = Central.getCurrentActivity();
        if (currentActivity.getClass().equals(RegisterActivity.class)) {
            ((RegisterActivity) currentActivity).hideProgressDialog();
        }

        // Check for null
        if (register == null) {
            Message.showErrorMessage("Es gab ein unbekanntes Problem bei der Kommunikation mit dem Server");
            return;
        }

        // Check, if an error code has been send
        if (register.getErrorCodeCount() < 1) {
            Message.showErrorMessage("Registrierung fehlgeschlagen, bitte versuche es später erneut.");
            return;
        }

        // Show all error codes
        for (ErrorCodes.ErrorCode code : register.getErrorCodeList()) {
            Message.showErrorMessage(com.leruka.leruka.net.ErrorCodes.getReadableError(code));
        }
    }


    public void showDialog() {
        // Show popup
        (new RegisterSuccessPopup(this)).create().show();
    }

    // Popup when register is successfull
    private class RegisterSuccessPopup extends AlertDialog.Builder {

        RegisterSuccessPopup(Context context) {
            super(context);
            this.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            goToMainActivity();
                        }
                    });
            this.setTitle("Erfolg");
            this.setMessage("Dein Account wurde erfolgereich erstellt");
        }

        RegisterSuccessPopup(Context context, int themeResId) {
            super(context, themeResId); // Should not be used
        }
    }

}
