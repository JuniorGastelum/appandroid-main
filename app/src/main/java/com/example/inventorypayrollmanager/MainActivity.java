package com.example.inventorypayrollmanager;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Empleado> listaEmpleados;
    private EmpleadoAdapter adapter;

    private EditText editTextNombreCompleto;
    private EditText editTextFechaNacimiento;
    private EditText editTextPuesto;
    private EditText editTextFechaIngreso;
    private EditText editTextSueldoBase;
    private EditText editTextRetenciones;
    private EditText editTextImpuestos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar la lista de empleados y el adaptador
        listaEmpleados = new ArrayList<>();
        adapter = new EmpleadoAdapter(this, listaEmpleados);

        // Referenciar elementos de la interfaz de usuario
        editTextNombreCompleto = findViewById(R.id.editTextNombreCompleto);
        editTextFechaNacimiento = findViewById(R.id.editTextFechaNacimiento);
        editTextPuesto = findViewById(R.id.editTextPuesto);
        editTextFechaIngreso = findViewById(R.id.editTextFechaIngreso);
        editTextSueldoBase = findViewById(R.id.editTextSueldoBase);
        editTextRetenciones = findViewById(R.id.editTextRetenciones);
        editTextImpuestos = findViewById(R.id.editTextImpuestos);

        Button btnAgregarEmpleado = findViewById(R.id.btnAgregarEmpleado);
        btnAgregarEmpleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarEmpleado();
            }
        });

        // Configurar el adaptador y la lista de empleados
        ListView listViewEmpleados = findViewById(R.id.listViewEmpleados);
        listViewEmpleados.setAdapter(adapter);

        // Mostrar detalles del empleado seleccionado al hacer clic en la lista
        listViewEmpleados.setOnItemClickListener((parent, view, position, id) -> {
            Empleado empleadoSeleccionado = listaEmpleados.get(position);
            mostrarDetalleEmpleado(empleadoSeleccionado);
        });

        // Cargar empleados desde el almacenamiento interno
        listaEmpleados.addAll(FileManager.cargarEmpleados(this));
        adapter.notifyDataSetChanged();
    }

    // Método para agregar un empleado a la lista
    public void agregarEmpleado() {
        String nombreCompleto = editTextNombreCompleto.getText().toString();
        String fechaNacimiento = editTextFechaNacimiento.getText().toString();
        String puesto = editTextPuesto.getText().toString();
        String fechaIngreso = editTextFechaIngreso.getText().toString();
        double sueldoBase = Double.parseDouble(editTextSueldoBase.getText().toString());
        double retenciones = Double.parseDouble(editTextRetenciones.getText().toString());
        double impuestos = Double.parseDouble(editTextImpuestos.getText().toString());

        Empleado empleado = new Empleado(nombreCompleto, fechaNacimiento, puesto, fechaIngreso,
                sueldoBase, retenciones, impuestos);

        listaEmpleados.add(empleado);
        adapter.notifyDataSetChanged();
        FileManager.guardarEmpleados(MainActivity.this, listaEmpleados);

        // Limpiar campos después de agregar empleado (opcional)
        limpiarCampos();
    }

    // Método para mostrar los detalles del empleado seleccionado
    private void mostrarDetalleEmpleado(Empleado empleado) {
        double sueldoMensual = empleado.calcularSueldoMensual();
        double sueldoQuincenal = empleado.calcularSueldoQuincenal();
        String detalle = "Nombre: " + empleado.getNombre() + "\n" +
                "Puesto: " + empleado.getPuesto() + "\n" +
                "Sueldo Mensual: $" + sueldoMensual + "\n" +
                "Sueldo Quincenal: $" + sueldoQuincenal;

        TextView textViewDetalle = findViewById(R.id.textViewDetalleEmpleado);
        textViewDetalle.setText(detalle);
    }

    // Método para limpiar los campos del formulario después de agregar empleado (opcional)
    private void limpiarCampos() {
        editTextNombreCompleto.setText("");
        editTextFechaNacimiento.setText("");
        editTextPuesto.setText("");
        editTextFechaIngreso.setText("");
        editTextSueldoBase.setText("");
        editTextRetenciones.setText("");
        editTextImpuestos.setText("");
    }
}
