package com.ciclabsindia.cic.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.ciclabsindia.cic.CertificateDetailsActivity;
import com.ciclabsindia.cic.DraftDetailsActivity;
import com.ciclabsindia.cic.GenerateExcelActivity;
import com.ciclabsindia.cic.GenerateCertificateActivity;
import com.ciclabsindia.cic.GenerateDraftActivity;
import com.ciclabsindia.cic.R;
import com.ciclabsindia.cic.database.DatabaseHandler;

import java.io.File;
import java.util.ArrayList;

public class RecyclerViewAdapterRecent extends RecyclerView.Adapter<RecyclerViewAdapterRecent.ViewHolder> {
    Context context;
    Integer[] icon;
    ArrayList<String> docs_type, invoice_num, shipper_name, date_time;

    DatabaseHandler handler;

    public RecyclerViewAdapterRecent(Context context, Integer[] icon, ArrayList<String> docs_type,
                                     ArrayList<String> invoice_no, ArrayList<String> shipper_name, ArrayList<String> date_time) {
        this.context = context;
        this.icon = icon;
        this.docs_type = docs_type;
        this.invoice_num = invoice_no;
        this.shipper_name = shipper_name;
        this.date_time = date_time;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout card;
        ImageView imageView;
        TextView t1, t2, t3;
        ImageButton btn_edit, btn_share, btn_more;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.card);
            imageView = itemView.findViewById(R.id.imageView);
            t1 = itemView.findViewById(R.id.textView1);
            t2 = itemView.findViewById(R.id.textView2);
            t3 = itemView.findViewById(R.id.textView3);
            btn_edit = itemView.findViewById(R.id.button1);
            btn_share = itemView.findViewById(R.id.button2);
            btn_more = itemView.findViewById(R.id.button3);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.custom_layout_card_basic, parent, false);
        View view = LayoutInflater.from(context).inflate(R.layout.custom_layout_card_stylish, parent, false);
        ViewHolder holder = new ViewHolder(view);
        handler = new DatabaseHandler(context);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String invoice_no = invoice_num.get(position);
        String doc_type = docs_type.get(position);
        String doc_id = doc_type.toUpperCase().charAt(0) + "_" + invoice_no;

        if (docs_type.get(position).equalsIgnoreCase("certificate"))
            holder.imageView.setImageResource(icon[0]);
        else if (docs_type.get(position).equalsIgnoreCase("draft"))
            holder.imageView.setImageResource(icon[1]);

        holder.t1.setText(invoice_num.get(position));
        holder.t2.setText(shipper_name.get(position));
        holder.t3.setText(date_time.get(position));

        //##################### Opening PDF of a Document from Recycler View #####################
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (doc_type.equals("draft")) {
                    Dialog dlg = new Dialog(context);
                    dlg.setContentView(R.layout.custom_dialog_open_doc);
                    dlg.show();

                    LinearLayout pdf_btn = dlg.findViewById(R.id.open_pdf);
                    LinearLayout excel_btn = dlg.findViewById(R.id.open_excel);

                    // Opening Draft PDF
                    pdf_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(context, GenerateDraftActivity.class);
                            i.putExtra("invoice_no", invoice_no);
                            context.startActivity(i);
                        }
                    });

                    // Opening Draft Excel
                    excel_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(context, GenerateExcelActivity.class);
                            i.putExtra("invoice_no", invoice_no);
                            context.startActivity(i);
                        }
                    });
                }

                // Opening Certificate PDF
                else if (doc_type.equals("certificate")) {
                    Intent i = new Intent(context, GenerateCertificateActivity.class);
                    i.putExtra("invoice_no", invoice_no);
                    context.startActivity(i);
                }
            }
        });

        //##################### Edit Button #####################
        holder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = null;
                if (doc_type.equalsIgnoreCase("draft"))
                    i = new Intent(context, DraftDetailsActivity.class);
                else if (doc_type.equalsIgnoreCase("certificate"))
                    i = new Intent(context, CertificateDetailsActivity.class);
                i.putExtra("invoice_no", invoice_no);
                context.startActivity(i);
            }
        });

        //##################### Sharing Button #####################
        holder.btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (doc_type.equals("draft")) {
                    PopupMenu p = new PopupMenu(context, holder.btn_share);
                    p.getMenuInflater().inflate(R.menu.share_menu, p.getMenu());
                    p.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            File file = null;
                            Intent i = new Intent(Intent.ACTION_SEND);

                            switch (item.getItemId()) {
                                // Share Draft PDF
                                case R.id.item1:
                                    file = new File(context.getExternalFilesDir(null), "/" + doc_id.replace("/", "%") + ".pdf");
                                    i.setType("application/pdf");
                                    break;

                                // Share Draft Excel
                                case R.id.item2:
                                    file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "/" + doc_id.replace("/", "%") + ".xls");
                                    i.setType("application/vnd.ms-excel");
                                    break;
                            }

                            // Sharing Draft
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                Uri uri = FileProvider.getUriForFile(context, "com.ciclabsindia.cic", file);
                                i.putExtra(Intent.EXTRA_STREAM, uri);
                            } else
                                i.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
                            context.startActivity(i);
                            return false;
                        }
                    });
                    p.show();
                }
                else if (doc_type.equals("certificate")) {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    File file = new File(context.getExternalFilesDir(null), "/" + doc_id.replace("/", "%") + ".pdf");
                    i.setType("application/pdf");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Uri uri = FileProvider.getUriForFile(context, "com.ciclabsindia.cic", file);
                        i.putExtra(Intent.EXTRA_STREAM, uri);
                    } else
                        i.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
                    context.startActivity(i);
                }
            }
        });

        //##################### More Button #####################
        holder.btn_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu p = new PopupMenu(context, holder.btn_more);
                p.getMenuInflater().inflate(R.menu.more_menu, p.getMenu());
                p.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId())
                        {
                            case R.id.item1:
                                long result = handler.deleteDocument(invoice_no, doc_type);
                                if (result > 0)
                                    Toast.makeText(context, "Record deleted", Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(context, "Unable to Delete!!", Toast.LENGTH_SHORT).show();
                                notifyItemRemoved(holder.getAdapterPosition());
                                break;
                        }
                        return false;
                    }
                });
                p.show();
            }
        });
    }

    public void clearList() {
        int size = invoice_num.size();
        if (size > 0) {
            docs_type.subList(0, size).clear();
            invoice_num.subList(0, size).clear();
            shipper_name.subList(0, size).clear();
            date_time.subList(0, size).clear();
            notifyItemRangeRemoved(0, size);
        }
    }

    @Override
    public int getItemCount() {
        return invoice_num.size();
    }
}