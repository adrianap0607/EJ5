//Universidad del Valle de Guatemala	                                                                                            
//Adriana Palacios 
//CC2008 - Introducción a la Programación Orientada a Objetos 
//carné 23044
//Semestre II, 2023

public class Auxiliares extends Jugador {
    private int ataques;
    private int bloqueosEfectivos;
    private int bloqueosFallidos;
//constructor clase auxiliares 
    public Auxiliares(String nombre, String pais, int errores, int aces, int totalServicios, int ataques, int bloqueosEfectivos, int bloqueosFallidos) {
        super(nombre, pais, errores, aces, totalServicios);
        this.ataques = ataques;
        this.bloqueosEfectivos = bloqueosEfectivos;
        this.bloqueosFallidos = bloqueosFallidos;
    }
//calcula la efectividad de auxiliar 
    @Override
    public double calcularEfectividad() {
        return ((ataques + bloqueosEfectivos - bloqueosFallidos - getErrores()) * 100.0 /
                (ataques + bloqueosEfectivos + bloqueosFallidos + getErrores())) + (getAces() * 100.0 / getTotalServicios());
    }

    public int getAtaques() {
        return ataques;
    }

    public int getBloqueosEfectivos() {
        return bloqueosEfectivos;
    }

    public int getBloqueosFallidos() {
        return bloqueosFallidos;
    }

    @Override
    public String getTipoJugador() {
        return "Auxiliar";
    }
}
