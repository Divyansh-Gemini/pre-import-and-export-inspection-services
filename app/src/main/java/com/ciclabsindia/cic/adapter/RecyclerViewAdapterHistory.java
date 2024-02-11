package com.ciclabsindia.cic.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
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
import com.ciclabsindia.cic.GenerateCertificateActivity;
import com.ciclabsindia.cic.GenerateDraftActivity;
import com.ciclabsindia.cic.GenerateExcelActivity;
import com.ciclabsindia.cic.R;
import com.ciclabsindia.cic.database.DatabaseHandler;

import java.io.File;
import java.util.ArrayList;

public class RecyclerViewAdapterHistory extends RecyclerView.Adapter<RecyclerViewAdapterHistory.ViewHolder> {
    Context context;
    Integer[] icon;
    ArrayList<String> docs_type, invoice_num, shipper_name, date_time;

    DatabaseHandler handler;

    public RecyclerViewAdapterHistory(Context context, Integer[] icon, ArrayList<String> docs_type,
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
        ImageButton btn_more;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.card_small);
            imageView = itemView.findViewById(R.id.imageView);
            t1 = itemView.findViewById(R.id.textView1);
            t2 = itemView.findViewById(R.id.textView2);
            t3 = itemView.findViewById(R.id.textView3);
            btn_more = itemView.findViewById(R.id.button3);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_layout_card_small, parent, false);
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
        holder.card.setOnClickListener(v -> {
            if (doc_type.equals("draft")) {
                Dialog dlg = new Dialog(context);
                dlg.setContentView(R.layout.custom_dialog_open_doc);
                dlg.show();

                LinearLayout pdf_btn = dlg.findViewById(R.id.open_pdf);
                LinearLayout excel_btn = dlg.findViewById(R.id.open_excel);

                // Opening Draft PDF
                pdf_btn.setOnClickListener(v12 -> {
                    Intent i = new Intent(context, GenerateDraftActivity.class);
                    i.putExtra("invoice_no", invoice_no);
                    context.startActivity(i);
                });

                // Opening Draft Excel
                excel_btn.setOnClickListener(v1 -> {
                    Intent i = new Intent(context, GenerateExcelActivity.class);
                    i.putExtra("invoice_no", invoice_no);
                    context.startActivity(i);
                });
            }

            // Opening Certificate PDF
            else if (doc_type.equals("certificate")) {
                Intent i = new Intent(context, GenerateCertificateActivity.class);
                i.putExtra("invoice_no", invoice_no);
                context.startActivity(i);
            }
        });

        //##################### More Button #####################
        holder.btn_more.setOnClickListener(v -> {
            PopupMenu p = new PopupMenu(context, holder.btn_more);
            p.getMenuInflater().inflate(R.menu.history_menu, p.getMenu());
            p.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.item1:
                        Intent i1 = null;
                        if (doc_type.equalsIgnoreCase("draft"))
                            i1 = new Intent(context, DraftDetailsActivity.class);
                        else if (doc_type.equalsIgnoreCase("certificate"))
                            i1 = new Intent(context, CertificateDetailsActivity.class);
                        i1.putExtra("invoice_no", invoice_no);
                        context.startActivity(i1);
                        break;

                    case R.id.item2:
                        Intent i2 = new Intent(Intent.ACTION_SEND);
                        File file = new File(context.getExternalFilesDir(null), "/" + doc_id.replace("/", "%") + ".pdf");
                        i2.setType("application/pdf");
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            Uri uri = FileProvider.getUriForFile(context, "com.ciclabsindia.cic", file);
                            i2.putExtra(Intent.EXTRA_STREAM, uri);
                        } else
                            i2.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
                        context.startActivity(i2);
                        break;

                    case R.id.item3:
                        long result = handler.deleteDocument(invoice_no, doc_type);
                        if (result > 0)
                            Toast.makeText(context, "Record deleted", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(context, "Unable to Delete!!", Toast.LENGTH_SHORT).show();
                        notifyItemRemoved(holder.getAdapterPosition());
                        break;
                }
                return false;
            });
            p.show();
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