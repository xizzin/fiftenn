package com.example.fifteen_two;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddBook extends AppCompatActivity {
    private EditText editbookName, addbookAuthor;
    private DataBaseHelper dbHelper;
    private Button addButton;
    private int bookId = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adding_card);

        editbookName = findViewById(R.id.addTextName);
        addbookAuthor = findViewById(R.id.addauthor);
        addButton = findViewById(R.id.addButton);

        dbHelper = new DataBaseHelper(this);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {addBookToDatabase();
            }
        });
    }

    private void addBookToDatabase() {
        String bookName = editbookName.getText().toString().trim();
        String bookAuthor = addbookAuthor.getText().toString().trim();

        if (bookName.isEmpty() || bookAuthor.isEmpty()) {
            Toast.makeText(this, "Заполните все поля для ввода", Toast.LENGTH_SHORT).show();
            return;
        }

        long result = dbHelper.addBook(bookId, bookName, bookAuthor);

        if (result > 0) {
            Toast.makeText(this, "Книга успешно добавлена", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AddBook.this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Ошибка при добавлении", Toast.LENGTH_SHORT).show();
        }
    }
}
