package com.info.dynamicviews;


import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import static com.info.dynamicviews.ViewHandler.Input;
import static com.info.dynamicviews.ViewHandler.getHint;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewsFragment#newInstance} factory method to
 * create an instance of getContext() fragment.
 */
public class ViewsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    LinearLayout linearLayout;
    AppCompatButton btnGetValue;
    HashMap<String, String> fieldsValueMap = new HashMap<>();
    HashMap<Fields, View> fieldsViewMap = new HashMap<>();

    public ViewsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ViewsFragment newInstance(String param1, String param2) {
        ViewsFragment fragment = new ViewsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_views, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        linearLayout = view.findViewById(R.id.llViews);
        btnGetValue = view.findViewById(R.id.btnGetValue);
        String dummyDynamicJSON = "[{\"field_id\": 1,\"label\": \"Offer Name\",\"attribute_type_id\": 1,\"component\": \"textbox\",\"display_order\": 1,\"is_mandatory\": 1,\"max_length\": 20,\"component_type\": \"text\",\"label_code\": \"offer_name\"},{\"field_id\": 5,\"label\": \"Offer Value\",\"attribute_type_id\": 5,\"component\": \"textbox\",\"display_order\": 2,\"is_mandatory\": 1,\"max_length\": 3,\"component_type\": \"int\",\"label_code\": \"offer_value\"},{\"field_id\": 6,\"label\": \"Desc\",\"attribute_type_id\": 6,\"component\": \"textbox\",\"display_order\": 3,\"is_mandatory\": 0,\"max_length\": 200,\"component_type\": \"text\",\"label_code\": \"desc\"},{\"field_id\": 7,\"label\": \"Product Name\",\"attribute_type_id\": 7,\"component\": \"textbox\",\"display_order\": 5,\"is_mandatory\": 0,\"max_length\": 50,\"component_type\": \"text\",\"label_code\": \"product_name\"}, {\"field_id\": 8,\"label\": \"Exp. Date\",\"attribute_type_id\": 8,\"component\": \"textbox\",\"display_order\": 4,\"is_mandatory\": 1,\"max_length\": 15,\"component_type\": \"date\",\"label_code\": \"exp_date\"}]";
        ArrayList<Fields> fields = new Gson().fromJson(dummyDynamicJSON, new TypeToken<List<Fields>>() {
        }.getType());

        for (int i = 0; i < fields.size(); i++) {
            Fields field = fields.get(i);
            switch (field.getComponent()) {
                case ViewHandler.TEXT_BOX:
                    addEditTexts(field);
                    break;
                case "":
                    break;
            }
        }


        btnGetValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isValid = true;
                for (Map.Entry<Fields, View> entry : fieldsViewMap.entrySet()) {

                    Fields field = entry.getKey();
                    View fieldView = entry.getValue();
                    if (field.getIsMandatory() == 1) {

                        switch (field.getComponent()) {

                            case ViewHandler.TEXT_BOX:
                                TextInputLayout textInputLayout = (TextInputLayout) fieldView;
                                if (fieldsValueMap.get(field.getLabelCode()) == null || fieldsValueMap.get(field.getLabelCode()).isEmpty()) {
                                    textInputLayout.setErrorEnabled(true);
                                    textInputLayout.setError(ViewHandler.getErrorMessage(field.getLabel()));
                                    isValid = false;
                                } else {
                                    textInputLayout.setErrorEnabled(false);
                                }
                                break;

                        }
                    }
                }

                if (isValid) {
                    SaveData saveData = new SaveData();
                    saveData.setTemplateId(8785l);
                    saveData.setUserId(2345l);
                    saveData.setOfferDetail(fieldsValueMap);

                    String outPutJSON = new Gson().toJson(saveData);
                    Log.e("outPutJSON", outPutJSON);
                }

            }
        });

    }


    private void addRadioButtons() {

        //RadioButtons are always added inside a RadioGroup
        RadioGroup radioGroup = new RadioGroup(getContext());
        radioGroup.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.addView(radioGroup);
        for (int i = 1; i <= 2; i++) {
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setText("Option " + String.valueOf(i));
            radioGroup.addView(radioButton);
            setRadioButtonAttributes(radioButton);
        }
        addLineSeperator();
    }

    private void addTextViews(String text) {
        //Adding a LinearLayout with HORIZONTAL orientation
        LinearLayout textLinearLayout = new LinearLayout(getContext());
        textLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.addView(textLinearLayout);
        TextView textView = new TextView(getContext());
        textView.setText(text);
        setTextViewAttributes(textView);
        textLinearLayout.addView(textView);

    }



    private void addCheckBoxes() {

        LinearLayout checkBoxLayout = new LinearLayout(getContext());
        checkBoxLayout.setOrientation(LinearLayout.VERTICAL);

        linearLayout.addView(checkBoxLayout);

        for (int i = 1; i <= 3; i++) {
            CheckBox checkBox = new CheckBox(getContext());
            checkBox.setText("CheckBox " + String.valueOf(i));
            setCheckBoxAttributes(checkBox);
            checkBoxLayout.addView(checkBox);
        }
        addLineSeperator();
    }

    private void addEditTexts(final Fields field) {

        final TextInputLayout textInputLayout = new TextInputLayout(getContext());
        linearLayout.addView(textInputLayout);
        AppCompatEditText editText = new AppCompatEditText(getContext());
        editText.setHint(getHint(field.getLabel(), field.getIsMandatory()));
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(field.getMaxLength())});
        editText.setInputType(Input.valueOf(field.getComponentType().toUpperCase()).getValue());

        setInputLayoutAttributes(textInputLayout);
        textInputLayout.addView(editText);
        fieldsViewMap.put(field, textInputLayout);

        if(field.getComponentType().equals("date")){
            editText.setFocusable(false);
            editText.setCursorVisible(false);
            new DatePickerView(getActivity(), editText);
        }


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s != null && !s.toString().isEmpty()) {
                    fieldsValueMap.put(field.getLabelCode(), s.toString().trim());
                    textInputLayout.setErrorEnabled(false);
                }
            }
        });
    }

    private void setEditTextAttributes(AppCompatEditText editText) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        params.setMargins(convertDpToPixel(16),
                convertDpToPixel(16),
                convertDpToPixel(16),
                0
        );

        editText.setLayoutParams(params);
    }

    private void setInputLayoutAttributes(TextInputLayout textInputLayout) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        params.setMargins(convertDpToPixel(16),
                convertDpToPixel(16),
                convertDpToPixel(16),
                0
        );

        textInputLayout.setLayoutParams(params);
    }

    private void setCheckBoxAttributes(CheckBox checkBox) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        params.setMargins(convertDpToPixel(16),
                convertDpToPixel(16),
                convertDpToPixel(16),
                0
        );

        checkBox.setLayoutParams(params);

        //getContext() is used to place the checkbox on the right side of the textview
        //By default, the checkbox is placed at the left side
        TypedValue typedValue = new TypedValue();
        getActivity().getTheme().resolveAttribute(android.R.attr.listChoiceIndicatorMultiple,
                typedValue, true);

        checkBox.setButtonDrawable(null);
        checkBox.setCompoundDrawablesWithIntrinsicBounds(0, 0,
                typedValue.resourceId, 0);
    }

    private void setTextViewAttributes(TextView textView) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        params.setMargins(convertDpToPixel(16),
                convertDpToPixel(16),
                0, 0
        );

        textView.setTextColor(Color.BLACK);
        textView.setLayoutParams(params);
    }

    private void setRadioButtonAttributes(RadioButton radioButton) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        params.setMargins(convertDpToPixel(16),
                convertDpToPixel(16),
                0, 0
        );

        radioButton.setLayoutParams(params);
    }

    //getContext() function to convert DPs to pixels
    private int convertDpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return Math.round(px);
    }

    private void addLineSeperator() {
        LinearLayout lineLayout = new LinearLayout(getContext());
        lineLayout.setBackgroundColor(Color.GRAY);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                2);
        params.setMargins(0, convertDpToPixel(10), 0, convertDpToPixel(10));
        lineLayout.setLayoutParams(params);
        linearLayout.addView(lineLayout);
    }


}
