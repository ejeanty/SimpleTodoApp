package CodePath.com.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import CodePath.com.Activities.MainActivity;
import CodePath.com.R;


public class Edition extends DialogFragment {


    EditText inputEdit;
    Button validateEdition;

    String text_from_activity;
    Integer position_from_acticvity;

    public static Edition newInstance(String text, Integer position) {

        Bundle args = new Bundle();

        args.putString("text", text);
        args.putInt("position", position);
        Edition fragment = new Edition();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.edit_taks, container, false);
        inputEdit = (EditText) view.findViewById(R.id.input_edit);
        validateEdition = (Button) view.findViewById(R.id.validate_edition);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        try{
            position_from_acticvity = getArguments().getInt("position");
            text_from_activity = getArguments().getString("text");
        }catch (Exception e){

        }


        if(!TextUtils.isEmpty(text_from_activity)){
            inputEdit.setText(text_from_activity);
        }




        validateEdition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(inputEdit.getText().toString())){
                    MainActivity activity = (MainActivity) getActivity();

                    if(activity != null){
                        activity.onFinishEditing(inputEdit.getText().toString(), position_from_acticvity);
                    }

                    dismiss();
                }else{
                    dismiss();
                }

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
