package bdnt.example.com.bandonhatro;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class NewPostActivity extends Activity implements AdapterView.OnItemSelectedListener{
    private Spinner citySpinner;
    private Spinner districtSpinner;
    private Spinner precinctSpinner;
    private Spinner streetSpinner;
    ImageView imvPost;
    TextView txtPost;

    HashMap<String, String> dictionary;
    HashMap<String, String[]> arrayMap;
    HashMap<String, String> params;
    String[] city = {"--Ch?n t?nh, thành ph?--", "Hà N?i"};
    String[] hanoi = {"--Ch?n qu?n huy?n--", "C?u Gi?y", "Ba ?ình", "??ng ?a"};
    String[] caugiay = {"--Ch?n ph??ng/xã--", "D?ch V?ng H?u", "Mai D?ch", "D?ch V?ng"};
    String[] badinh = {"--Ch?n ph??ng/xã--", "??i C?n"};
    String[] dongda = {"--Ch?n ph??ng/xã--", "Láng Th??nng", "Láng H?"};
    String[] dichvonghau = {"--Ch?n ???ng ph?--", "Xuân Th?y","Ph?m Hùng"};
    String[] maidich = {"--Ch?n ???ng ph?--", "H? Tùng M?u", "Ph?m V?n ??ng", "Doãn K? Thi?n"};
    String[] dichvong = {"--Ch?n ???ng ph?--", "Thành Thái"};
    String[] doican = {"--Ch?n ???ng ph?--", "Ng?c Hà"};
    String[] langthuong = {"--Ch?n ???ng ph?--", "Pháo ?ài Láng"};
    String[] langha = {"--Ch?n ???ng ph?--", "Hu?nh Thúc Kháng"};

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
        citySpinner = (Spinner) findViewById(R.id.spinner2);
        districtSpinner = (Spinner) findViewById(R.id.spQuanhuyen);
        precinctSpinner = (Spinner) findViewById(R.id.spPhuongxa);
        streetSpinner = (Spinner) findViewById(R.id.spDuongpho);
imvPost=(ImageView)findViewById(R.id.imvPost);
        txtPost=(TextView)findViewById(R.id.txtPost);
        cityStringArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, cityList);
        districtStringArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, districtList);
        precinctStringArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, precinctList);
        streetStringArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, streetList);

        citySpinner.setAdapter(cityStringArrayAdapter);
        districtSpinner.setAdapter(districtStringArrayAdapter);
        precinctSpinner.setAdapter(precinctStringArrayAdapter);

        streetSpinner.setAdapter(streetStringArrayAdapter);

        citySpinner.setOnItemSelectedListener(this);
        districtSpinner.setOnItemSelectedListener(this);
        precinctSpinner.setOnItemSelectedListener(this);
        streetSpinner.setOnItemSelectedListener(this);
        imvPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(NewPostActivity.this);
                builder1.setMessage("??ng tin thành công.");
                builder1.setCancelable(true);
                builder1.setPositiveButton("Oke.",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });


                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });
        txtPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(NewPostActivity.this);
                builder1.setMessage("??ng tin thành công.");
                builder1.setCancelable(true);
                builder1.setPositiveButton("Oke.",
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
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner currentSpinner = (Spinner) parent;
        switch (currentSpinner.getId()) {
            case R.id.spinner2:
                if (position == 0) {
                    emptyArrayList(districtList);
                    districtList.add("--Ch?n qu?n/huy?n--");
                    districtStringArrayAdapter.notifyDataSetChanged();
                    params.put("city", "");
                } else {
                    String item = currentSpinner.getItemAtPosition(position).toString();
                    loadDistrictContent(item);
                    params.put("city", item);

                }

                break;
            case R.id.spQuanhuyen:
                if (position == 0) {
                    emptyArrayList(precinctList);
                    precinctList.add("--Ch?n ph??ng/xã--");
                    precinctStringArrayAdapter.notifyDataSetChanged();
                    params.put("district", "");
                } else {
                    String item = currentSpinner.getItemAtPosition(position).toString();
                    loadPrecinctContent(item);
                    params.put("district", item);
                }
                break;
            case R.id.spPhuongxa:
                if (position == 0) {
                    emptyArrayList(streetList);
                    streetList.add("--Ch?n ???ng ph?--");
                    streetStringArrayAdapter.notifyDataSetChanged();
                    params.put("precinct", "");
                } else {
                    String item = currentSpinner.getItemAtPosition(position).toString();
                    loadStreetContent(item);
                    params.put("precinct", item);
                }
                break;
            case R.id.spDuongpho:
                if (position == 0) {
                    params.put("street", "");
                } else {
                    String item = currentSpinner.getItemAtPosition(position).toString();
                    params.put("street", item);
                }
                break;

        }
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }
    private void initData() {
        //init dictionary
        dictionary = new HashMap<>();
        dictionary.put("Hà N?i", "hanoi");
        dictionary.put("H? Chí Minh", "hochiminh");
        dictionary.put("C?u Gi?y", "caugiay");
        dictionary.put("Ba ?ình", "badinh");
        dictionary.put("??ng ?a", "dongda");
        dictionary.put("D?ch V?ng H?u", "dichvonghau");
        dictionary.put("Mai D?ch", "maidich");
        dictionary.put("D?ch V?ng", "dichvong");
        dictionary.put("??i C?n", "doican");
        dictionary.put("Láng Th??ng", "langthuong");
        dictionary.put("Xuân Th?y", "xuanthuy");
        dictionary.put("H? Tùng M?u", "hotungmau");
        dictionary.put("Ph?m V?n ??ng", "phamvandong");
        dictionary.put("Doãn K? Thi?n", "doankethien");
        dictionary.put("Thành Thái", "thanhthai");
        dictionary.put("Ng?c Hà", "ngocha");
        dictionary.put("Pháo ?ài Láng", "phaodailang");
        dictionary.put("Hu?nh Thúc Kháng", "huynhthuckhang");
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
