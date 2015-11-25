package com.leruka.leruka.activity;

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
import com.leruka.leruka.user.Register;
import com.leruka.leruka.user.User;

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
        // show spinner
        this.showProgressDialog();
        // Get the username and password
        String name = this.inputName.getText().toString();
        String pass1 = this.inputPass1.getText().toString();
        String pass2 = this.inputPass2.getText().toString();
        // Trigger register in the backend
        Register.register(name, pass1, pass2);

    }

    public void onReceiveRegister() {
        // Show popup
        (new RegisterSuccessPopup(this)).create().show();
    }

    public void toMain() {
        // Change the activity
        Intent intent = new Intent(this, UserMainActivity.class);
        startActivity(intent);
    }

    private void onBack() {
        //TODO
    }

    // Popup when register is successfull
    private class RegisterSuccessPopup extends AlertDialog.Builder {

        RegisterSuccessPopup(Context context) {
            super(context);
            this.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            toMain();
                        }
                    });
            this.setTitle("Erfolg");
            this.setMessage("Dein Account " + User.getCurrentUser().getUserName()
                    + " wurde erfolgereich erstellt");
        }

        RegisterSuccessPopup(Context context, int themeResId) {
            super(context, themeResId); // Should not be used
        }
    }

}
