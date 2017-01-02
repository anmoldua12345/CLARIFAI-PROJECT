package com.example.joginderpal.gallerypro;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import clarifai2.api.ClarifaiResponse;
import clarifai2.dto.input.ClarifaiInput;
import clarifai2.dto.input.image.ClarifaiImage;
import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.prediction.Concept;

/**
 * Created by joginderpal on 02-01-2017.
 */
public class third extends Activity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    List<String> li;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third);
        recyclerView= (RecyclerView) findViewById(R.id.rv);
       new doit().execute();
    }


    public class doit extends AsyncTask<Void,Void,Void> {

        ClarifaiResponse<List<ClarifaiOutput<Concept>>> output1;
        @Override
        protected Void doInBackground(Void... voids) {
              li=new ArrayList<String>();
            final ClarifaiClient client = new ClarifaiBuilder("suzEQunh9SzD87nOtES7VyBW8be2ooAmXwIpIOLM", "_X-v_n_5v2WHKNdP1ZsL2eLx9ShZaLferEt2JMyV").buildSync();
            output1= client.getDefaultModels().generalModel().predict()
                    .withInputs(
                            ClarifaiInput.forImage(ClarifaiImage.of(new File(("/sdcard/flashCropped.png"))))
                    ).executeSync();

            for (int i=0;i<output1.get().get(0).data().size();i++){
                li.add(output1.get().get(0).data().get(i).name());
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pd.dismiss();
            layoutManager=new LinearLayoutManager(third.this);
            recyclerView.setLayoutManager(layoutManager);
            adapter=new RecyclerAdapter(third.this,li);
            recyclerView.setAdapter(adapter);


        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd=new ProgressDialog(third.this);
            pd.setMessage("FETCHING");
            pd.show();
        }
    }


}


