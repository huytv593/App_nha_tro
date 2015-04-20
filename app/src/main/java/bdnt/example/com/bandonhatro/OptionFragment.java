package bdnt.example.com.bandonhatro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class OptionFragment extends Fragment {
    Button btnDangTin;
    Button btnLinkToLoginScreen;
    Button btnGioiThieu;
    Button btnInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_option, container, false);
        btnLinkToLoginScreen = (Button) v.findViewById(R.id.btnLinkToLoginScreen);
        btnLinkToLoginScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);

            }
        });
        btnDangTin = (Button) v.findViewById(R.id.btnNewPost);
        btnDangTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewPostActivity.class);
                startActivity(intent);
            }
        });
        btnInfo = (Button) v.findViewById(R.id.btnViewInfo);
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InfoActivity.class);
                startActivity(intent);
            }
        });

//                b.setContentView(R.layout.logi
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


        return v;
    }
}