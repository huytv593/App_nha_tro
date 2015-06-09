package bdnt.example.com.bandonhatro;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rey.material.app.DialogFragment;
import com.rey.material.app.SimpleDialog;

import bdnt.example.com.bandonhatro.helper.SessionManager;

public class OptionFragment extends DialogFragment {
    TextView btnDangTin;

    TextView btnGioiThieu;
    TextView btnGopY;
    private SessionManager session;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_option, container, false);
        session = new SessionManager(getActivity());
        btnGioiThieu= (TextView) v.findViewById(R.id.btnGioiThieu);
        btnGopY = (TextView) v.findViewById(R.id.btnGopY);
        btnGopY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),GopY.class);
                startActivity(intent);
            }
        });
        btnGioiThieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Gioithieu.class);
                startActivity(intent);
            }
        });
        btnDangTin = (TextView) v.findViewById(R.id.btnNewPost);
        btnDangTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Toast.makeText(getActivity(),"clickeed",Toast.LENGTH_SHORT).show();

                if (session.isLoggedIn()) {
//             User is already logged in. Take him to main activity
                    Intent intent = new Intent(getActivity(), NewPostActivity.class);
                    startActivity(intent);
                } else {

//             User is already logged in. Take him to main activity


                    com.rey.material.app.Dialog.Builder builder = null;
                    builder = new SimpleDialog.Builder(R.style.SimpleDialogLight) {
                        @Override
                        public void onPositiveActionClicked(DialogFragment fragment) {
                            Intent intent = new Intent(getActivity(),LoginActivity.class);
                            startActivity(intent);
                            super.onPositiveActionClicked(fragment);
                        }

                        @Override
                        public void onNegativeActionClicked(DialogFragment fragment) {
//                            Toast.makeText(getActivity(), "Disagreed", Toast.LENGTH_SHORT).show();
                            super.onNegativeActionClicked(fragment);
                        }
                    };

                    ((SimpleDialog.Builder) builder).message("Vui lòng đăng nhập để đăng tin nhà trọ.")
                            .title("Đăng Tin")
                            .positiveAction("Đăng Nhập")
                            .negativeAction("Hủy");

                    DialogFragment fragment = DialogFragment.newInstance(builder);
                    fragment.show(getFragmentManager(), null);
                }
            }
        });

        return v;
    }
}