package lds.bcc.ldsattendancechecker;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private List<MenuForm> form;

    public DataAdapter(List<MenuForm> form) {
        this.form = form;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, int i) {

        viewHolder.menu_icon.setImageResource(form.get(i).getPhotoid());
        viewHolder.menu_name.setText(form.get(i).getMenuName());
    }

    @Override
    public int getItemCount() {
        return form.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView menu_name;
        private ImageView menu_icon;

        public ViewHolder(View view) {
            super(view);

            menu_name = (TextView) view.findViewById(R.id.menu_name);
            menu_icon = (ImageView) view.findViewById(R.id.menu_icon);
        }
    }

}