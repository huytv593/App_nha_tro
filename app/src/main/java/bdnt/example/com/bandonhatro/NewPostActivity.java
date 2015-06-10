package bdnt.example.com.bandonhatro;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.rey.material.widget.Spinner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class NewPostActivity extends Activity {
    private Spinner citySpinner;
    private Spinner districtSpinner;
    private Spinner precinctSpinner;
    private Spinner streetSpinner;
    private ImageView back;
    ImageView imvPost;


    HashMap<String, String> dictionary;
    HashMap<String, String[]> arrayMap;
    HashMap<String, String> params;
    String[] city = {"--Chọn tỉnh/thành phố--", "Hà Nội"};
    String[] hanoi = {"--Chọn quận/quyện--", "Cầu Giấy", "Ba Đình", "Đống Đa"};
    String[] caugiay = {"--Chọn phường/xã--", "Dịch Vọng Hậu", "Mai Dịch", "Dịch Vọng"};
    String[] badinh = {"--Chọn phường/xã--", "Đội Cấn"};
    String[] dongda = {"--Chọn phường/xã--", "Láng Thượng", "Láng Hạ"};
    String[] dichvonghau = {"--Chọn đường phố--", "Xuân Thủy", "impossible to find"};
    String[] maidich = {"--Chọn đường phố--", "Hồ Tùng Mậu", "Phạm Văn Đồng", "Doãn Kế Thiện"};
    String[] dichvong = {"--Chọn đường phố--", "Thành Thái"};
    String[] doican = {"--Chọn đường phố--", "Ngọc Hà"};
    String[] langthuong = {"--Chọn đường phố--", "Pháo Đài Láng"};
    String[] langha = {"--Chọn đường phố--", "Huỳnh Thúc Kháng"};

    ArrayList<String> cityList;
    ArrayList<String> districtList;
    ArrayList<String> precinctList;
    ArrayList<String> streetList;

    ArrayAdapter<String> cityStringArrayAdapter;
    ArrayAdapter<String> districtStringArrayAdapter;
    ArrayAdapter<String> precinctStringArrayAdapter;
    ArrayAdapter<String> streetStringArrayAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);
        initData();
        citySpinner = (Spinner) findViewById(R.id.spnCity);
        districtSpinner = (Spinner) findViewById(R.id.spnDistric);
        precinctSpinner = (Spinner) findViewById(R.id.spnArea);
        streetSpinner = (Spinner) findViewById(R.id.spnStreet);
        imvPost = (ImageView) findViewById(R.id.imvSend);
        back= (ImageView) findViewById(R.id.imvNewPostBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        cityStringArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, cityList);
        districtStringArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, districtList);
        precinctStringArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, precinctList);
        streetStringArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, streetList);
        districtList.add("--Chọn quận/huyện--");
        precinctList.add("--Chọn phường/xã--");
        streetList.add("--Chọn đường phố--");
        citySpinner.setAdapter(cityStringArrayAdapter);
        districtSpinner.setAdapter(districtStringArrayAdapter);
        precinctSpinner.setAdapter(precinctStringArrayAdapter);

        streetSpinner.setAdapter(streetStringArrayAdapter);

        citySpinner.setOnItemSelectedListener(new com.rey.material.widget.Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(com.rey.material.widget.Spinner spinner, View view, int position, long l) {
                com.rey.material.widget.Spinner currentSpinner;
                currentSpinner = spinner;
                if (position == 0) {
                    emptyArrayList(districtList);

                    districtStringArrayAdapter.notifyDataSetChanged();
                    params.put("city", "");
                } else {
                    int pos = currentSpinner.getSelectedItemPosition();
                    String item = cityList.get(pos);
                    loadDistrictContent(item);
                    params.put("city", item);

                }
            }
        });
        districtSpinner.setOnItemSelectedListener(new com.rey.material.widget.Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(com.rey.material.widget.Spinner spinner, View view, int position, long l) {
                com.rey.material.widget.Spinner currentSpinner;
                currentSpinner = (com.rey.material.widget.Spinner) spinner;
                if (position == 0) {
                    emptyArrayList(precinctList);
                    precinctList.add("--Chọn phường/xã--");
                    precinctStringArrayAdapter.notifyDataSetChanged();
                    params.put("district", "");
                } else {
                    int pos = currentSpinner.getSelectedItemPosition();
                    String item = districtList.get(pos);
                    loadPrecinctContent(item);
                    params.put("district", item);

                }
            }
        });
        precinctSpinner.setOnItemSelectedListener(new com.rey.material.widget.Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(com.rey.material.widget.Spinner spinner, View view, int position, long l) {
                com.rey.material.widget.Spinner currentSpinner;
                currentSpinner = (com.rey.material.widget.Spinner) spinner;
                if (position == 0) {
                    emptyArrayList(streetList);
                    streetList.add("--Chọn đường phố--");
                    streetStringArrayAdapter.notifyDataSetChanged();
                    params.put("precinct", "");
                } else {
                    int pos = currentSpinner.getSelectedItemPosition();
                    String item = precinctList.get(pos);
                    loadStreetContent(item);
                    params.put("precinct", item);
                }
            }
        });
        streetSpinner.setOnItemSelectedListener(new com.rey.material.widget.Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(com.rey.material.widget.Spinner spinner, View view, int position, long l) {
                com.rey.material.widget.Spinner currentSpinner;
                currentSpinner = (com.rey.material.widget.Spinner) spinner;
                if (position == 0) {
                    params.put("street", "");
                } else {
                    int pos = currentSpinner.getSelectedItemPosition();
                    String item = streetList.get(pos);
                    params.put("street", item);
                }
            }
        });


        imvPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(NewPostActivity.this);
                builder1.setMessage("Đăng tin thành công");
                builder1.setCancelable(true);
                builder1.setPositiveButton("OK.",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });


                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });


    }

    //    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        Spinner currentSpinner = (Spinner) parent;
//        switch (currentSpinner.getId()) {
//            case R.id.spinner2:
//                if (position == 0) {
//                    emptyArrayList(districtList);
//                    districtList.add("--Chọn quận huyện--");
//                    districtStringArrayAdapter.notifyDataSetChanged();
//                    params.put("city", "");
//                } else {
//                    String item = currentSpinner.getItemAtPosition(position).toString();
//                    loadDistrictContent(item);
//                    params.put("city", item);
//
//                }
//
//                break;
//            case R.id.spQuanhuyen:
//                if (position == 0) {
//                    emptyArrayList(precinctList);
//                    precinctList.add("--Chọn phường xã--");
//                    precinctStringArrayAdapter.notifyDataSetChanged();
//                    params.put("district", "");
//                } else {
//                    String item = currentSpinner.getItemAtPosition(position).toString();
//                    loadPrecinctContent(item);
//                    params.put("district", item);
//                }
//                break;
//            case R.id.spPhuongxa:
//                if (position == 0) {
//                    emptyArrayList(streetList);
//                    streetList.add("--Chọn đường phố--");
//                    streetStringArrayAdapter.notifyDataSetChanged();
//                    params.put("precinct", "");
//                } else {
//                    String item = currentSpinner.getItemAtPosition(position).toString();
//                    loadStreetContent(item);
//                    params.put("precinct", item);
//                }
//                break;
//            case R.id.spDuongpho:
//                if (position == 0) {
//                    params.put("street", "");
//                } else {
//                    String item = currentSpinner.getItemAtPosition(position).toString();
//                    params.put("street", item);
//                }
//                break;
//
//        }
//    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

    private void initData() {
        //init dictionary
        dictionary = new HashMap<>();
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

}
