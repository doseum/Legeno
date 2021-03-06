package com.example.billage.frontend.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.example.billage.R;
import com.example.billage.frontend.data.UsageList;
import com.example.billage.frontend.ui.addUsage.AddUsage;

import java.util.List;

public class DetailAdapter extends ArrayAdapter<UsageList> {

    private Context context;
    private List mList;
    private ListView mListView;


    class DetailViewHolder {
        public TextView cost;
        public TextView destination;
        public TextView time;
        public ImageView type_image;
        public TextView bank_name;
        public CardView cardView;

    }

    public DetailAdapter(Context context, List<UsageList> list, ListView listview) {
        super(context, 0, list);

        this.context = context;
        this.mList = list;
        this.mListView = listview;

    }

    // ListView의  한 줄(row)이 렌더링(rendering)될 때 호출되는 메소드로 row를 위한 view를 리턴.
    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parentViewGroup) {
        View rowView = convertView;
        DetailViewHolder viewHolder;
        String Status;

        if (rowView == null) {

            // 레이아웃을 정의한 XML 파일(R.layout.list_item)을 읽어서 계층 구조의 뷰 객체(rowView)로 변환.
            // rowView 객체는 3개의 TextView로 구성
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            rowView = layoutInflater.inflate(R.layout.select_list, parentViewGroup, false);

            viewHolder = new DetailViewHolder();

            viewHolder.cost = (TextView) rowView.findViewById(R.id.cost_selected);
            viewHolder.destination = (TextView) rowView.findViewById(R.id.destination_selected);
            viewHolder.time = (TextView) rowView.findViewById(R.id.time_selected);
            viewHolder.type_image= (ImageView) rowView.findViewById(R.id.type_image_selected);
            viewHolder.bank_name = (TextView) rowView.findViewById(R.id.bank_name_selected);
            viewHolder.cardView = (CardView) rowView.findViewById(R.id.calendar_cardview);
            rowView.setTag(viewHolder);

            Status = "created";
        } else {

            viewHolder = (DetailAdapter.DetailViewHolder) rowView.getTag();
            Status = "reused";
        }

        // 태그 분석을 위한 코드 시작
        String Tag = rowView.getTag().toString();
        int idx = Tag.indexOf("@");
        String tag = Tag.substring(idx + 1);


        UsageList usage = (UsageList) mList.get(position);
        viewHolder.destination.setText(usage.getDestination());
        viewHolder.time.setText(usage.getTime().substring(0,5));

        // 추후 수정
        viewHolder.bank_name.setText(usage.getBank_name());

        if(usage.getUsage_type().equals("입금")){
            viewHolder.cost.setText("+ "+usage.getCost()+"원");
            viewHolder.type_image.setImageResource(R.drawable.deposit);
        }else {
            viewHolder.cost.setText("- "+usage.getCost()+"원");
            viewHolder.type_image.setImageResource(R.drawable.withdarw);
        }

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (context, AddUsage.class);
                intent.putExtra("cardTypeInfo",usage.getUsage_type());
                intent.putExtra("cardCostInfo",usage.getCost());
                intent.putExtra("cardDateInfo",usage.getDate());
                intent.putExtra("cardTimeInfo",usage.getTime());
                intent.putExtra("cardDestInfo",usage.getDestination());
                intent.putExtra("cardMemoInfo",usage.getMemo());

                context.startActivity(intent);
            }
        });
        rowView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        return rowView;
    }

}

