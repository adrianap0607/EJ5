//Universidad del Valle de Guatemala	                                                                                            
//Adriana Palacios 
//CC2008 - Introducción a la Programación Orientada a Objetos 
//carné 23044
//Semestre II, 2023

//Creacion clase Libero
public class Libero extends Jugador {
    private int recibos;

    public Libero(String nombre, String pais, int errores, int aces, int totalServicios, int recibos) {
        super(nombre, pais, errores, aces, totalServicios);
        this.recibos = recibos;
    }
//Metodo para calcular efectividad de libero
    @Override
    public double calcularEfectividad() {
        return ((recibos - getErrores()) * 100.0 / (recibos + getErrores())) + (getAces() * 100.0 / getTotalServicios());
    }

    public int getRecibos() {
        return recibos;
    }

    @Override
    public String getTipoJugador() {
        return "Libero";
    }
}
