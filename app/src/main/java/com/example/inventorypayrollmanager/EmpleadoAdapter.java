package com.example.inventorypayrollmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class EmpleadoAdapter extends ArrayAdapter<Empleado> {

    private Context context;
    private List<Empleado> listaEmpleados;

    public EmpleadoAdapter(Context context, List<Empleado> listaEmpleados) {
        super(context, 0, listaEmpleados);
        this.context = context;
        this.listaEmpleados = listaEmpleados;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null) {
            listItem = LayoutInflater.from(context).inflate(R.layout.item_empleado, parent, false);
        }

        Empleado empleado = listaEmpleados.get(position);

        TextView textViewNombre = listItem.findViewById(R.id.textViewNombre);
        textViewNombre.setText(empleado.getNombre());

        TextView textViewPuesto = listItem.findViewById(R.id.textViewPuesto);
        textViewPuesto.setText(empleado.getPuesto());

        return listItem;
    }
}
