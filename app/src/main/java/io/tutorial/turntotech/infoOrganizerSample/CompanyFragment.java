package io.tutorial.turntotech.infoOrganizerSample;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static io.tutorial.turntotech.infoOrganizerSample.StartActivity.dao;


/**
 * A simple {@link Fragment} subclass.
 */
public class CompanyFragment extends Fragment implements CallBack {

    private RecyclerView recycler_view;
    //private ArrayList<String> listOfComany;
    private  VerticalAdapter recyclerAdapter;
    ImageButton addButton;
    ImageButton backButton;
   // public static DAO dao;   // is this ok to make this public?????

    String csv = "";
    // This is for the Network calls
    NetworkManager networkManager;

    //--------
    private ArrayList<Company> companies = dao.getCompanies();
    //private DAO dao;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_main, container, false);


        recycler_view= (RecyclerView) view.findViewById(R.id.vertical_recycler_view);
        //recyclerAdapter=new VerticalAdapter(dao.getCompanies());
        recyclerAdapter=new VerticalAdapter(companies);

        networkManager = NetworkManager.getSharedInstance();
        // create the URL
        // check if Array List is null
        String url = getURL();

        //Toast.makeText(getContext(), url, Toast.LENGTH_SHORT).show();
        networkManager.downloadData(getContext(),
                "https://download.finance.yahoo.com/d/quotes.csv?s="+(url)+"&f=nl1",this);


        LinearLayoutManager layoutmanager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recycler_view.setLayoutManager(layoutmanager);

        recycler_view.setAdapter(recyclerAdapter);


        recycler_view.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), recycler_view ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        //Toast.makeText(getContext(),listOfCompany.get(position),Toast.LENGTH_SHORT).show();
                        //Toast.makeText(getContext(), dao.getDAOCompany(position).getCompany_name(),
                         //       Toast.LENGTH_SHORT).show();


                        ((StartActivity) getActivity()).setCurrentCompanyNo(position);

                        // Go to Child not Found Screen
                        Fragment productFragment = new ProductFragment();
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.mainLayout, productFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();

                    }

                    @Override
                    public void onLongItemClick(View view, final int position) {
                        // do what you want
//                        Toast.makeText(getContext(), "Long press on position :"+dao.getCompanies().size(),
//                                Toast.LENGTH_LONG).show();
                        //recyclerAdapter.removeAt(position);
                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                        builder.setMessage("What do you want to do with this item?")
                                .setCancelable(false)
                                .setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Company companyToRemove = dao.getDAOCompany(position);
                                        dao.removeCompany(companyToRemove);
                                        recyclerAdapter.removeAt(position);
                                        recyclerAdapter.notifyDataSetChanged();
                                    }
                                })
                                .setNegativeButton("UPDATE", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        ((StartActivity) getActivity()).setUpdate(true);
                                        ((StartActivity) getActivity()).setCurrentCompanyNo(position);

                                        // Go to Child not Found Screen
                                        Fragment addCompany = new AddCompany();
                                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                        fragmentTransaction.replace(R.id.mainLayout, addCompany);
                                        fragmentTransaction.addToBackStack(null);
                                        // Commit the transaction
                                        fragmentTransaction.commit();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();


                    }
                })
        );

        // Action Bar Setup
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.toolbar);
        backButton = (ImageButton)activity.findViewById(R.id.imageButton);
        addButton = (ImageButton)activity.findViewById(R.id.imageButton2);
        backButton.setVisibility(View.INVISIBLE);



        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(),"Add",Toast.LENGTH_LONG).show();
                // Go to Child not Found Screen
                Fragment addCompany = new AddCompany();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.mainLayout, addCompany);
                fragmentTransaction.addToBackStack(null);
                // Commit the transaction
                fragmentTransaction.commit();
            }
        });

        return view;
    }

    public String getURL(){
        String temp = "";
        for(int i = 0; i < dao.getCompanies().size(); i++){
            if(i < dao.getCompanies().size() - 1)
                temp += dao.getCompanies().get(i).getStock_ticker() + "+";
            else
                temp += dao.getCompanies().get(i).getStock_ticker();
        }
        return temp;
    }
    @Override
    public void update() {
        String resultStr = "";
        for (String str : networkManager.lines){
            resultStr += "\n";
            resultStr += str;
        }
        //csv = resultStr;
        String [] lines = resultStr.split("\\n");
        int arrSize = lines.length;
        int listSize = dao.getCompanies().size();
        if( arrSize > listSize){

            for(int i = 1; i < arrSize; i++){
                String[] temp_arr = lines[i].split(",");
                String temp = temp_arr[1];
                // I had to store the arrayList for the recycler view
                // companiesArrayList holds the current viewd items
                Company tempCompany = companies.get(i-1);
                companies.get(i-1).setStock_price(temp);
                tempCompany.setStock_price(temp);
                dao.updateCompany(tempCompany);
                recyclerAdapter.notifyDataSetChanged();
            }
        }
        //Toast.makeText(getContext(), resultStr, Toast.LENGTH_LONG).show();

    }

    public class VerticalAdapter extends RecyclerView.Adapter<VerticalAdapter.MyViewHolder> {

        private List<Company> companyList;
        // private List<String verticalList;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView txtView;
            //-------------
            public TextView textView2;
            public ImageView imageView;

            public MyViewHolder(View view) {
                super(view);
                txtView = (TextView) view.findViewById(R.id.txtView);
                //-----------
                textView2 = (TextView) view.findViewById(R.id.textView2);
                imageView = (ImageView) view.findViewById(R.id.imageView);

            }
        }
        /*
        public VerticalAdapter(List<String> verticalList) {
            this.verticalList = verticalList;
        }
        */
        public VerticalAdapter(List<Company> verticalList) {
            this.companyList = verticalList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.vertical_item_view, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            //holder.txtView.setText(verticalList.get(position));
            holder.txtView.setText(companyList.get(position).getCompany_name());
            holder.textView2.setText(companyList.get(position).getStock_price());
            //holder.textView2.setText(companyList.get(position).getStock_ticker());
            Picasso.with(holder.imageView.getContext()).load(companyList.get(position).getLogoURL()).into(holder.imageView);
            // If you want to access separate part of it

            holder.txtView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Toast.makeText(getContext(),holder.txtView.getText().toString(),Toast.LENGTH_SHORT).show();
                }

            });
        }
        // add a row
        public void insertAt(int position, Company company){
            companyList.add(position, company);
            notifyItemInserted(position);
        }
        //removes the row
        public void removeAt(int position) {
            if(!companyList.isEmpty()) {
                companyList.remove(position);
                //dao.removeCompany(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, companyList.size());
            }
        }
        public void insert(Company company){
            companyList.add(company);
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return companyList.size();
        }
    }
}
