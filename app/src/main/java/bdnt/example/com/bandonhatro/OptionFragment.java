package bdnt.example.com.bandonhatro;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class OptionFragment extends Fragment {
    Button btnDangTin;
    Button btnGioiThieu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_option, container, false);
        btnDangTin = (Button) v.findViewById(R.id.btnDangTin);
        btnDangTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);

            }
        });
//        btnDangTin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Dialog b = new Dialog(getActivity());
//                b.setContentView(R.layout.login);
//                b.setTitle("Đăng nhập");
//                btnLinkDangKi = (Button) b.findViewById(R.id.btnLinkToRegisterScreen);
//                btnLinkDangKi.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        final Dialog c = new Dialog(getActivity());
//                        c.setContentView(R.layout.signup);
//                        c.setTitle("Đăng kí");
////                        final Button btnHuyBo = (Button) c.findViewById(R.id.btnHuyBo);
////                        btnHuyBo.setOnClickListener(new View.OnClickListener() {
////                            @Override
////                            public void onClick(View view) {
////                                c.cancel();
////                            }
////                        });
//                        c.show();
//
//
//                    }
//                });
//                b.show();
//            }
//        });
//        btnGioiThieu = (Button) v.findViewById(R.id.btnGioiThieu);
//        btnGioiThieu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
        return v;
    }
}