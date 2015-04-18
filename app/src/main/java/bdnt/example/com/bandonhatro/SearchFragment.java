package bdnt.example.com.bandonhatro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    public String thanhpho = null;
    private Spinner town;
    private Spinner huyenSpiner;
    private Spinner phuongSpiner;
    private Spinner duongSpiner;
    Button btnSearch;

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
        List<String> phuong = new ArrayList<String>();
        List<String> duong = new ArrayList<String>();
        town = (Spinner) v.findViewById(R.id.thanhpho_spinner);
        town.setOnItemSelectedListener(this);
        towns.add("-- Chọn tỉnh/thành phố--");
        towns.add("Hà Nội");
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

        phuongSpiner = (Spinner) v.findViewById(R.id.phuongxa_spinner);
        phuongSpiner.setOnItemSelectedListener(this);
        phuong.add("-- Chọn phường/xã--");
        ArrayAdapter<String> dataAdapter5 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, phuong);
        // Drop down layout style - list view with radio button
        dataAdapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        phuongSpiner.setAdapter(dataAdapter5);

        duongSpiner = (Spinner) v.findViewById(R.id.duongpho_spinner);
        duongSpiner.setOnItemSelectedListener(this);
        duong.add("-- Chọn đường phố--");
        ArrayAdapter<String> dataAdapter6 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, duong);
        // Drop down layout style - list view with radio button
        dataAdapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        duongSpiner.setAdapter(dataAdapter6);

        Spinner dientichSpinner = (Spinner) v.findViewById(R.id.dientich_spinner);
        dientichSpinner.setOnItemSelectedListener(this);
        List<String> dientich = new ArrayList<String>();
        dientich.add("-- Chọn diện tích --");
        dientich.add("Dưới 15m2");
        dientich.add("Từ 15m2 đến 20m2");
        dientich.add("Trên 20m2");
        dientich.add("Chung cư mini");
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
        gia.add("Từ 1tr đến 2tr");
        gia.add("Từ 2tr đến 5tr");
        ArrayAdapter<String> dataAdapter4 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, gia);
        // Drop down layout style - list view with radio button
        dataAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        giaSpinner.setAdapter(dataAdapter4);

//        btnSearch = (Button) v.findViewById(R.id.btnSearch);
//        btnSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(),ListViewActivity.class);
//                startActivity(intent);
//
//            }
//        });

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
            result.add("Bắc Từ Liêm");
            result.add("nam Từ Liêm");
            result.add("Cầu Giấy");
            result.add("Đống Đa");
            result.add("Hà Đông");
            result.add("Hai Bà Trưng");
            result.add("Hoàn Kiếm");
            result.add("Hoàng Mai");
            result.add("Long Biên");
            result.add("Tây Hồ");
            result.add("Thanh Xuân");
            result.add("Sơn Tây");
            result.add("Ba Vì");
            result.add("Chương Mỹ");
            result.add("Đan Phượng");
            result.add("Đông Anh");
            result.add("Gia Lâm");
            result.add("Hoài Đức");
            result.add("Mê Linh");
            result.add("Thanh Trì");
            result.add("Thường Tín");

        } else if (town.equals("Hồ Chí Minh")) {
            result.add("-- Chọn quận/huyện --");
            result.add("Quận 1");
            result.add("Quận 2");
            result.add("Quận 3");
            result.add("Quận 4");
            result.add("Quận 5");
            result.add("Quận 6");
            result.add("Quận 7");
            result.add("Quận 8");
            result.add("Quận 9");
            result.add("Quận 10");
            result.add("Quận Bình Thạnh");
        } else {
            result.add("-- Chọn quận/huyện --");
        }
        return result;
    }

}