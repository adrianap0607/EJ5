//Universidad del Valle de Guatemala	                                                                                            
//Adriana Palacios 
//CC2008 - Introducción a la Programación Orientada a Objetos 
//carné 23044
//Semestre II, 2023


//Creacion clase pasador 
public class Pasador extends Jugador {
    private int pases;
    private int fintas;

    public Pasador(String nombre, String pais, int errores, int aces, int totalServicios, int pases, int fintas) {
        super(nombre, pais, errores, aces, totalServicios);
        this.pases = pases;
        this.fintas = fintas;
    }
// metodo para calcular efectividad pasador
    @Override
    public double calcularEfectividad() {
        return ((pases + fintas - getErrores()) * 100.0 / (pases + fintas + getErrores())) + (getAces() * 100.0 / getTotalServicios());
    }

    public int getPases() {
        return pases;
    }

    public int getFintas() {
        return fintas;
    }
//obtener tipo de jugador 
    @Override
    public String getTipoJugador() {
        return "Pasador";
    }
}
