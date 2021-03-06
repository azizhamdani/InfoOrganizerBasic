package io.tutorial.turntotech.infoOrganizerSample;

import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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


public class ProductFragment extends Fragment {

    // RecyvlerView
    private RecyclerView product_recycler_view;
    private ProductFragment.VerticalProductAdapter recyclerProductAdapter;
    // list of individual products AND list of All products

    //private ArrayList<String> appleProducts,samsungProducts,motorolaProducts,microsoftProducts;
    //List<List<String>> listOfAllProducts;
    ImageButton addButton;
    ImageButton backButton;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_main2, container, false);

        product_recycler_view= (RecyclerView) view.findViewById(R.id.vertical_recycler_view);

        // Get the Company to display correct Products
        int companyNo = ((StartActivity) getActivity()).getCurrentCompanyNo();

        //listOfAllProducts = new ArrayList<List<String>>();

//        // apple Products
//        appleProducts=new ArrayList<String>();
//        appleProducts.add("Iphone");
//        appleProducts.add("IPad");
//        appleProducts.add("IPod");
//
//        // Samsung Products
//        samsungProducts =new ArrayList<String>();
//        samsungProducts.add("Galaxy s");
//        samsungProducts.add("Galaxy Note");
//        samsungProducts.add("J7");
//
//        // Moto Products
//        motorolaProducts=new ArrayList<String>();
//        motorolaProducts.add("Moto E");
//        motorolaProducts.add("Moto G");
//        motorolaProducts.add("Moto X");
//
//        // Microsoft Products
//        microsoftProducts=new ArrayList<String>();
//        microsoftProducts.add("Lumia 540");
//        microsoftProducts.add("Lumia 640");
//        microsoftProducts.add("Lumia 925");

        // add all products to the main list
        //listOfAllProducts.add(appleProducts);
        //listOfAllProducts.add(samsungProducts);
        //listOfAllProducts.add(motorolaProducts);
        //listOfAllProducts.add(microsoftProducts);

        //recyclerProductAdapter = new VerticalProductAdapter(listOfAllProducts.get(companyNo));
        recyclerProductAdapter = new VerticalProductAdapter(dao.getDAOProductList(companyNo));

        LinearLayoutManager layoutmanager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        product_recycler_view.setLayoutManager(layoutmanager);

        product_recycler_view.setAdapter(recyclerProductAdapter);
        product_recycler_view.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), product_recycler_view ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        //Toast.makeText(getContext(),listOfCompany.get(position),Toast.LENGTH_SHORT).show();
//                        Toast.makeText(getContext(), DAO.getInstance().getDAOCompany(position).getCompany_name(),
//                                Toast.LENGTH_SHORT).show();


                        // set the current product position
                        ((StartActivity) getActivity()).setCurrentProductNo(position);

                        // Go to Child not Found Screen
                        Fragment webFragment = new WebFragment();
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.mainLayout, webFragment);
                        fragmentTransaction.addToBackStack(null);

                        // Commit the transaction
                        fragmentTransaction.commit();

                    }

                    @Override public void onLongItemClick(View view, final int position) {
                        // do what you want
//                        Toast.makeText(getContext(), "Long press on position :"+dao.getCompanies().size(),
//                                Toast.LENGTH_LONG).show();
                        //recyclerAdapter.removeAt(position);
                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                        builder.setMessage("What do you want to do with this product?")
                                .setCancelable(false)
                                .setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        int current_com = ((StartActivity) getActivity()).getCurrentCompanyNo();
                                        Product productToRemove = dao.getDAOProduct(current_com, position);  // get the product
                                        dao.removeProduct(productToRemove);
                                        recyclerProductAdapter.removeAt(position);
                                        recyclerProductAdapter.notifyDataSetChanged();
                                    }
                                })
                                .setNegativeButton("UPDATE", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        ((StartActivity) getActivity()).setUpdate(true);
                                        // set the current product position
                                        //Toast.makeText(getContext(), "current pro pos: " + position, Toast.LENGTH_LONG ).show();
                                        ((StartActivity) getActivity()).setCurrentProductNo(position);
                                        //((StartActivity) getActivity()).setCurrentCompanyNo(position);

                                        // Go to Child not Found Screen
                                        Fragment addProduct = new AddProduct();
                                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                        fragmentTransaction.replace(R.id.mainLayout, addProduct);
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

        // ActionBar SetUp
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.toolbar);
        backButton = (ImageButton)activity.findViewById(R.id.imageButton);
        addButton = (ImageButton)activity.findViewById(R.id.imageButton2);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(),"Back",Toast.LENGTH_LONG).show();
                Fragment company = new CompanyFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.mainLayout, company);
                fragmentTransaction.addToBackStack(null);
                // Commit the transaction
                fragmentTransaction.commit();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(),"Add",Toast.LENGTH_LONG).show();
                // Go to Child not Found Screen
                Fragment addProduct = new AddProduct();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.mainLayout, addProduct);
                fragmentTransaction.addToBackStack(null);

                // Commit the transaction
                fragmentTransaction.commit();
            }
        });
        return view;
    }

    public class VerticalProductAdapter extends RecyclerView.Adapter<ProductFragment.VerticalProductAdapter.MyViewHolder> {

        //private List<String> verticalList;
        private ArrayList<Product> verticalList;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView textProductName;
            public ImageView productImage;

            public MyViewHolder(View view) {
                super(view);
                textProductName = (TextView) view.findViewById(R.id.textProductName);
                productImage = (ImageView) view.findViewById(R.id.product_img);

            }
        }


        public VerticalProductAdapter(ArrayList<Product> verticalList) {
            this.verticalList = verticalList;
        }

        @Override
        public ProductFragment.VerticalProductAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.vertical_product_item, parent, false);

            return new ProductFragment.VerticalProductAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {
            holder.textProductName.setText(verticalList.get(position).getProduct_name());
            Picasso.with(holder.productImage.getContext()).load(verticalList.get(position).getLogo_URL()).into(holder.productImage);
            holder.textProductName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                   //Toast.makeText(getContext(),holder.textProductName.getText().toString(),Toast.LENGTH_SHORT).show();
                }
            });
        }
        // add a row
        public void insertAt(int position, Product product){
            verticalList.add(position, product);
            notifyItemInserted(position);
        }
        //removes the row
        public void removeAt(int position) {
            if(!verticalList.isEmpty()) {
                verticalList.remove(position);
                //dao.removeCompany(position);   // delete should be callled in here?????????????
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, verticalList.size());
            }
        }
        public void insert(Product product){
            verticalList.add(product);
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return verticalList.size();
        }
    }
}