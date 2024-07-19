package com.example.inventorypayrollmanager;

import android.content.Context;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private static final String FILENAME = "empleados.dat";

    // Método para guardar la lista de empleados en el almacenamiento interno
    public static void guardarEmpleados(Context context, List<Empleado> empleados) {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(empleados);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para cargar la lista de empleados desde el almacenamiento interno
    public static List<Empleado> cargarEmpleados(Context context) {
        List<Empleado> empleados = new ArrayList<>();
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            empleados = (List<Empleado>) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return empleados;
    }
}
