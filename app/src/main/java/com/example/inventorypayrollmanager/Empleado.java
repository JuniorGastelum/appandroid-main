package com.example.inventorypayrollmanager;


public class Empleado {

    private String nombre;
    private String fechaNacimiento;
    private String puesto;
    private String fechaIngreso;
    private double sueldoBase;
    private double retenciones;
    private double impuestos;

    public Empleado(String nombre, String fechaNacimiento, String puesto, String fechaIngreso,
                    double sueldoBase, double retenciones, double impuestos) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.puesto = puesto;
        this.fechaIngreso = fechaIngreso;
        this.sueldoBase = sueldoBase;
        this.retenciones = retenciones;
        this.impuestos = impuestos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public double calcularSueldoMensual() {
        double impuestoSobreRenta = 0.0;
        if (sueldoBase > 1000 && sueldoBase <= 4000) {
            impuestoSobreRenta = sueldoBase * 0.112;
        } else if (sueldoBase > 4000) {
            impuestoSobreRenta = sueldoBase * 0.187;
        }

        double sueldoMensual = sueldoBase - retenciones - impuestos - impuestoSobreRenta;
        return sueldoMensual;
    }

    public double calcularSueldoQuincenal() {
        return calcularSueldoMensual() / 2;
    }
}
