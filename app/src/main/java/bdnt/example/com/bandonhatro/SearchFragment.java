package bdnt.example.com.bandonhatro;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SearchFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private Spinner citySpinner;
    private Spinner districtSpinner;
    private Spinner precinctSpinner;
    private Spinner streetSpinner;
    private Spinner squareSpinner;
    private Spinner priceSpinner;
    Button btnSearch;
    Map<String, String> dictionary;
    Map<String, String[]> arrayMap;
    Map<String, String> params;
    String[] city = {"--Chọn tỉnh/thành phố--", "Hà Nội"};
    String[] hanoi = {"--Chọn quận/quyện--", "Cầu Giấy", "Ba Đình", "Đống Đa"};
    String[] caugiay = {"--Chọn phường/xã--", "Dịch Vọng Hậu", "Mai Dịch", "Dịch Vọng"};
    String[] badinh = {"--Chọn phường/xã--", "Đội Cấn"};
    String[] dongda = {"--Chọn phường/xã--", "Láng Thượng", "Láng Hạ"};
    String[] dichvonghau = {"--Chọn đường phố--", "Xuân Thủy"};
    String[] maidich = {"--Chọn đường phố--", "Hồ Tùng Mậu", "Phạm Văn Đồng", "Doãn Kế Thiện"};
    String[] dichvong = {"--Chọn đường phố--", "Thành Thái"};
    String[] doican = {"--Chọn đường phố--", "Ngọc Hà"};
    String[] langthuong = {"--Chọn đường phố--", "Pháo Đài Láng"};
    String[] langha = {"--Chọn đường phố--", "Huỳnh Thúc Kháng"};
    String[] square = {"--Chọn diện tích--", "Dưới 15m2", "Từ 15m2 đến 20m2", "Trên 20m2"};
    String[] price = {"--Chọn mức giá--", "Từ 1tr đến 2tr", "Từ 2tr đến 5tr"};
    ArrayList<String> cityList;
    ArrayList<String> districtList;
    ArrayList<String> precinctList;
    ArrayList<String> streetList;
    ArrayList<String> squareList;
    ArrayList<String> priceList;
    ArrayAdapter<String> cityStringArrayAdapter;
    ArrayAdapter<String> districtStringArrayAdapter;
    ArrayAdapter<String> precinctStringArrayAdapter;
    ArrayAdapter<String> streetStringArrayAdapter;
    ArrayAdapter<String> squareStringArrayAdapter;
    ArrayAdapter<String> priceStringArrayAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        initData();
        citySpinner = (Spinner) v.findViewById(R.id.city_spinner);
        districtSpinner = (Spinner) v.findViewById(R.id.district_spinner);
        precinctSpinner = (Spinner) v.findViewById(R.id.precinct_spinner);
        streetSpinner = (Spinner) v.findViewById(R.id.street_spinner);
        squareSpinner = (Spinner) v.findViewById(R.id.square_spinner);
        priceSpinner = (Spinner) v.findViewById(R.id.price_spinner);
        cityStringArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, cityList);
        districtStringArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, districtList);
        precinctStringArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, precinctList);
        streetStringArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, streetList);
        squareStringArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, squareList);
        priceStringArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, priceList);
        citySpinner.setAdapter(cityStringArrayAdapter);
        districtSpinner.setAdapter(districtStringArrayAdapter);
        precinctSpinner.setAdapter(precinctStringArrayAdapter);
        streetSpinner.setAdapter(streetStringArrayAdapter);
        squareSpinner.setAdapter(squareStringArrayAdapter);
        priceSpinner.setAdapter(priceStringArrayAdapter);
        citySpinner.setOnItemSelectedListener(this);
        districtSpinner.setOnItemSelectedListener(this);
        precinctSpinner.setOnItemSelectedListener(this);
        streetSpinner.setOnItemSelectedListener(this);
        squareSpinner.setOnItemSelectedListener(this);
        priceSpinner.setOnItemSelectedListener(this);

        btnSearch = (Button) v.findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //        Intent searchIntent = new Intent(getActivity(), ResultSearchActivity.class);
//        ArrayList<String> content = new ArrayList<>();
//        content.add("HaNoi");
//        content.add("TuLiem");
//
//
//        Bundle contentBundle = new Bundle();
//        contentBundle.putStringArrayList("DATA", content);
//        searchIntent.putExtras(contentBundle);
//        getActivity().startActivity(searchIntent);
//
//        Log.i("INFO", "Clicked");
//        Intent intent = new Intent(getActivity(), ListViewWithVolley.class);
//        startActivity(intent);
                String stringParams = ">>" + params.get("city") + " || " + params.get("district") + " || " + params.get("precinct")
                        + " || " + params.get("street") + " || " + params.get("minSquare") + " || " + params.get("maxSquare") + " || "
                        + params.get("minPrice") + " || " + params.get("maxPrice") + "<<";

                Toast.makeText(getActivity(), stringParams, Toast.LENGTH_SHORT).show();
                searchRequest();
            }
        });
        return v;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner currentSpinner = (Spinner) parent;
        switch (currentSpinner.getId()) {
            case R.id.city_spinner:
                if (position == 0) {
                    emptyArrayList(districtList);
                    districtList.add("--Chọn quận/quyện--");
                    districtStringArrayAdapter.notifyDataSetChanged();
                    params.put("city", "");
                } else {
                    String item = currentSpinner.getItemAtPosition(position).toString();
                    loadDistrictContent(item);
                    params.put("city", item);

                }

                break;
            case R.id.district_spinner:
                if (position == 0) {
                    emptyArrayList(precinctList);
                    precinctList.add("--Chọn phường/xã--");
                    precinctStringArrayAdapter.notifyDataSetChanged();
                    params.put("district", "");
                } else {
                    String item = currentSpinner.getItemAtPosition(position).toString();
                    loadPrecinctContent(item);
                    params.put("district", item);
                }
                break;
            case R.id.precinct_spinner:
                if (position == 0) {
                    emptyArrayList(streetList);
                    streetList.add("--Chọn đường phố--");
                    streetStringArrayAdapter.notifyDataSetChanged();
                    params.put("precinct", "");
                } else {
                    String item = currentSpinner.getItemAtPosition(position).toString();
                    loadStreetContent(item);
                    params.put("precinct", item);
                }
                break;
            case R.id.street_spinner:
                if (position == 0) {
                    params.put("street", "");
                } else {
                    String item = currentSpinner.getItemAtPosition(position).toString();
                    params.put("street", item);
                }
                break;
            case R.id.square_spinner:
                switch (position) {
                    case 0:
                        params.put("minSquare", "0");
                        params.put("maxSquare", "0");
                        break;
                    case 1:
                        params.put("minSquare", "0");
                        params.put("maxSquare", "15");
                        break;
                    case 2:
                        params.put("minSquare", "15");
                        params.put("maxSquare", "20");
                        break;
                    case 3:
                        params.put("minSquare", "20");
                        params.put("maxSquare", "1000");
                        break;
                }
                break;
            case R.id.price_spinner:
                switch (position) {
                    case 0:
                        params.put("minPrice", "0");
                        params.put("maxPrice", "0");
                        break;
                    case 1:
                        params.put("minPrice", "1000");
                        params.put("maxPrice", "2000");
                        break;
                    case 2:
                        params.put("minPrice", "2000");
                        params.put("maxPrice", "5000");
                        break;
                }
        }
    }


    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }


    private void initData() {
        //init dictionary
        dictionary = new HashMap<>();
        dictionary.put("Hà Nội", "hanoi");
        dictionary.put("Hồ Chí Minh", "hochiminh");
        dictionary.put("Cầu Giấy", "caugiay");
        dictionary.put("Ba Đình", "badinh");
        dictionary.put("Đống Đa", "dongda");
        dictionary.put("Dịch Vọng Hậu", "dichvonghau");
        dictionary.put("Mai Dịch", "maidich");
        dictionary.put("Dịch Vọng", "dichvong");
        dictionary.put("Đội Cấn", "doican");
        dictionary.put("Láng Thượng", "langthuong");
        dictionary.put("Xuân Thủy", "xuanthuy");
        dictionary.put("Hồ Tùng Mậu", "hotungmau");
        dictionary.put("Phạm Văn Đồng", "phamvandong");
        dictionary.put("Doãn Kế Thiện", "doankethien");
        dictionary.put("Thành Thái", "thanhthai");
        dictionary.put("Ngọc Hà", "ngocha");
        dictionary.put("Pháo Đài Láng", "phaodailang");
        dictionary.put("Huỳnh Thúc Kháng", "huynhthuckhang");
        //
        arrayMap = new HashMap<>();
        arrayMap.put("hanoi", hanoi);
        arrayMap.put("caugiay", caugiay);
        arrayMap.put("badinh", badinh);
        arrayMap.put("dongda", dongda);
        arrayMap.put("square", square);
        arrayMap.put("price", price);
        arrayMap.put("dichvonghau", dichvonghau);
        arrayMap.put("dichvong", dichvong);
        arrayMap.put("maidich", maidich);
        arrayMap.put("dichvong", dichvonghau);
        arrayMap.put("doican", doican);
        arrayMap.put("langthuong", langthuong);
        arrayMap.put("langha", langha);
        //
        params = new HashMap<>();
        params.put("city", "");
        params.put("district", "");
        params.put("precinct", "");
        params.put("street", "");
        params.put("minSquare", "");
        params.put("maxSquare", "");
        params.put("minPrice", "");
        params.put("maxPrice", "");
        //
        cityList = new ArrayList<>();
        addStringArrayToArrayList(cityList, city);
        districtList = new ArrayList<>();
        precinctList = new ArrayList<>();
        streetList = new ArrayList<>();
        squareList = new ArrayList<>();
        addStringArrayToArrayList(squareList, square);
        priceList = new ArrayList<>();
        addStringArrayToArrayList(priceList, price);
        //


    }

    private void emptyArrayList(ArrayList<String> arrayList) {
        for (int i = arrayList.size(); i > 0; i--) {
            arrayList.remove(i - 1);
        }
    }

    private void addStringArrayToArrayList(ArrayList<String> arrayList, String[] stringArray) {
        Collections.addAll(arrayList, stringArray);
    }

    private void loadDistrictContent(String item) {
        emptyArrayList(districtList);
        addStringArrayToArrayList(districtList, arrayMap.get(dictionary.get(item)));
        districtStringArrayAdapter.notifyDataSetChanged();
    }

    private void loadPrecinctContent(String item) {
        emptyArrayList(precinctList);
        addStringArrayToArrayList(precinctList, arrayMap.get(dictionary.get(item)));
        precinctStringArrayAdapter.notifyDataSetChanged();
    }

    private void loadStreetContent(String item) {
        emptyArrayList(streetList);
        addStringArrayToArrayList(streetList, arrayMap.get(dictionary.get(item)));
        precinctStringArrayAdapter.notifyDataSetChanged();

    }

    private void searchRequest() {
        // Tag used to cancel the request
        String tag_string_req = "req_register";
        final ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Registering ...");
        pDialog.show();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_SEARCH, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                pDialog.hide();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.hide();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url


                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

}
