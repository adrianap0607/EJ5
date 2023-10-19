//Universidad del Valle de Guatemala	                                                                                            
//Adriana Palacios 
//CC2008 - Introducción a la Programación Orientada a Objetos 
//carné 23044
//Semestre II, 2023

public abstract class Jugador {
    private String nombre;
    private String pais;
    private int errores;
    private int aces;
    private int totalServicios;
//constructor clase jugador 
    public Jugador(String nombre, String pais, int errores, int aces, int totalServicios) {
        this.nombre = nombre;
        this.pais = pais;
        this.errores = errores;
        this.aces = aces;
        this.totalServicios = totalServicios;
    }
//Calcula la efectividad del jugador
    public abstract double calcularEfectividad();
//obtiene nombre de jugador 
    public String getNombre() {
        return nombre;
    }
//obtiene país de jugador 
    public String getPais() {
        return pais;
    }
//Obtiene la cantidad de errores
    public int getErrores() {
        return errores;
    }
//obtiene cantidad de aces
    public int getAces() {
        return aces;
    }
//obtiene cantidad total de servicios 
    public int getTotalServicios() {
        return totalServicios;
    }
    
    public abstract String getTipoJugador();
}
