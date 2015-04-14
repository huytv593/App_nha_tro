package bdnt.example.com.bandonhatro;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    public String thanhpho = null;
    private Spinner town;
    private Spinner huyenSpiner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        List<String> towns = new ArrayList<String>();
        List<String> huyen = new ArrayList<String>();
        town = (Spinner) v.findViewById(R.id.thanhpho_spinner);
        town.setOnItemSelectedListener(this);
        towns.add("-- Chọn tỉnh/thành phố--");
        towns.add("Hà Nội");
        towns.add("Thái Bình");
        towns.add("Hải Phòng");
        towns.add("Hồ Chí Minh");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, towns);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        town.setAdapter(dataAdapter);

        huyenSpiner = (Spinner) v.findViewById(R.id.huyen_spinner);
        huyenSpiner.setOnItemSelectedListener(this);
        huyen.add("-- Chọn quận/huyện--");
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, huyen);
        // Drop down layout style - list view with radio button
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        huyenSpiner.setAdapter(dataAdapter2);


        Spinner dientichSpinner = (Spinner) v.findViewById(R.id.dientich_spinner);
        dientichSpinner.setOnItemSelectedListener(this);
        List<String> dientich = new ArrayList<String>();
        dientich.add("-- Chọn diện tích --");
        dientich.add("dưới 15m2");
        dientich.add("từ 15m2 đến 20m2");
        dientich.add("trên 20m2");
        dientich.add("trung cư mini");
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, dientich);
        // Drop down layout style - list view with radio button
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        dientichSpinner.setAdapter(dataAdapter3);

        Spinner giaSpinner = (Spinner) v.findViewById(R.id.gia_spinner);
        giaSpinner.setOnItemSelectedListener(this);
        List<String> gia = new ArrayList<String>();
        gia.add("-- Chọn mức giá --");
        gia.add("dưới 1triệu");
        gia.add("từ 1 đến 2 triệu");
        gia.add("trên 2 triệu");
        ArrayAdapter<String> dataAdapter4 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, gia);
        // Drop down layout style - list view with radio button
        dataAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        giaSpinner.setAdapter(dataAdapter4);

        return v;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner currentSpiner = (Spinner) parent;

        if (currentSpiner.getId() == R.id.thanhpho_spinner) {
            String item = parent.getItemAtPosition(position).toString();
            List<String> huyen = this.loadHuyen(item);
            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, huyen);
            // Drop down layout style - list view with radio button
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // attaching data adapter to spinner
            huyenSpiner.setAdapter(dataAdapter2);
        } else if (currentSpiner.getId() == R.id.huyen_spinner) {
            String item = parent.getItemAtPosition(position).toString();

        } else {

        }
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

    private List<String> loadHuyen(String town) {
        List<String> result = new ArrayList<String>();
        if (town.equals("Hà Nội")) {
            result.add("-- Chọn quận/huyện --");
            result.add("Từ Liêm");
            result.add("Cầu Giấy");
            result.add("Hai Bà Tưng");
            result.add("Hoàn Kiếm");

        } else if (town.equals("Thái Bình")) {
            result.add("-- Chọn quận/huyện --");
            result.add("Thái Thụy");
            result.add("Đông Hưng");
        } else {
            result.add("-- Chọn quận/huyện --");
        }
        return result;
    }

}