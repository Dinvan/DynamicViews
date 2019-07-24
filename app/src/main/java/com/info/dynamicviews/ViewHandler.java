package com.info.dynamicviews;

import android.text.InputType;

public class ViewHandler {

    public static final String TEXT_BOX ="textbox";

    public  enum Input {
        TEXT(InputType.TYPE_CLASS_TEXT),DATE(InputType.TYPE_CLASS_TEXT), INT(InputType.TYPE_CLASS_NUMBER), PHONE(InputType.TYPE_CLASS_PHONE);
        private int value;

        Input(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public static String getHint(String hint,int mandatory){
        if(mandatory==1){
            return hint.concat(" ").concat("*");
        }else{
            return hint;
        }
    }


    public static String getErrorMessage(String label){
        return "please enter".concat(" ").concat(label);
    }

}
