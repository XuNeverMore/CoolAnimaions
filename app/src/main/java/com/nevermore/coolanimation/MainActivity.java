package com.nevermore.coolanimation;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private List<Page> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        initData();
        MyAdapter adapter = new MyAdapter();
        recyclerView.setAdapter(adapter);

    }

    private void initData() {
        mList = new ArrayList<>();
        Page page = new Page("RainView",RainActivity.class);
        mList.add(page);

    }


    class MyAdapter extends RecyclerView.Adapter<ViewHolder>{

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            final Page page = mList.get(position);
            holder.textView.setText(page.name);
            holder.itemView.setOnClickListener(v->startActivity(new Intent(MainActivity.this,page.aClass)));

        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(android.R.id.text1);
        }
    }

    class Page{
        String name;
        Class<? extends AppCompatActivity> aClass;

        public Page(String name, Class<? extends AppCompatActivity> aClass) {
            this.name = name;
            this.aClass = aClass;
        }
    }

}
