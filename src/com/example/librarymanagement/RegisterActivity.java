package com.example.librarymanagement;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.librarymanagement.http.httpRequest;

/**
 * Created by ligan_000 on 2014/11/1.
 */
public class RegisterActivity extends Activity {

    private EditText stu_number, stu_name, stu_psw, stu_qq;
    private Button btn_submit;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                Toast.makeText(RegisterActivity.this, "链接服务器出错：" + msg.obj.toString(), Toast.LENGTH_SHORT).show();
            } else if (msg.obj.toString().trim().equals("false")) {
                Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(RegisterActivity.this, "恭喜您注册成功！\n现在您可以去登录了！", Toast.LENGTH_SHORT).show();
                   finish();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_register);

        stu_number = (EditText) this.findViewById(R.id.register_stu_number);
        stu_name = (EditText) this.findViewById(R.id.register_stu_name);
        stu_psw = (EditText) this.findViewById(R.id.register_stu_psw);
        stu_qq = (EditText) this.findViewById(R.id.register_stu_qq);

        btn_submit = (Button) this.findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = stu_number.getText().toString().trim();
                String name = stu_name.getText().toString();
                String psw = stu_psw.getText().toString();
                String qq = stu_qq.getText().toString();
                if (number.isEmpty() || number.length() < 12) {
                    Toast.makeText(RegisterActivity.this, "请输入正确的学号！", Toast.LENGTH_SHORT).show();
                } else if (name.isEmpty() || name.length() < 2) {
                    Toast.makeText(RegisterActivity.this, "请输入正确的姓名！", Toast.LENGTH_SHORT).show();
                } else if (psw.isEmpty() || psw.length() < 6) {
                    Toast.makeText(RegisterActivity.this, "请输入6位以上的正确密码！", Toast.LENGTH_SHORT).show();
                } else if (qq.isEmpty() || qq.length() < 7) {
                    Toast.makeText(RegisterActivity.this, "请输入正确的qq号！", Toast.LENGTH_SHORT).show();
                } else {
                    httpRequest http = new httpRequest();
                    http.register(handler, number, name, psw, qq);
                }
            }
        });

    }
}
