package models;

import managers.GestorMisiones;

public class Jugador extends Entidad {
  private InventarioJugador inventario;
  private GestorMisiones misiones;
  private Ubicacion ubicacionActual;
  private int capacidadMax = 100;  

  public Jugador(String nombre, Ubicacion ubicacionInicial) {
    super(nombre, 100, 15);
    this.inventario = new InventarioJugador(capacidadMax);
    this.ubicacionActual = ubicacionInicial;
    this.misiones = new GestorMisiones();
  }

  @Override
  public void recibirDanio(int danio) {
      this.vida -= danio;
      if (this.vida < 0) this.vida = 0;
  }

  @Override
  public String atacar(Entidad objetivo) {
    String resultado = "";
    objetivo.recibirDanio(this.ataque);
    resultado += (this.nombre + " ataca a " + objetivo.getNombre() + " causando " + this.ataque + " puntos de daño.");
    return resultado;
}

  public void curarse(int cantidadCuracion, String nombre) {
    this.vida = Math.min(100, getVida() + cantidadCuracion);
  }

  public String mostrarInventario() {
    return inventario.listarObjetos();
  }

  public Ubicacion getUbicacionActual() {
    return ubicacionActual;
  }

  public InventarioJugador getInventario() {
    return inventario;
  }

  public void setInventario(InventarioJugador inventario) {
    this.inventario = inventario;
  }

  public void setUbicacionActual(Ubicacion ubicacionActual) {
    this.ubicacionActual = ubicacionActual;
  }

  public int getCapacidadMax() {
    return capacidadMax;
  }

  public void setCapacidadMax(int capacidadMax) {
    this.capacidadMax = capacidadMax;
  }


  public String mostrarMisiones(){
    StringBuilder sb = new StringBuilder();
    sb.append(misiones.listarMisionesActivas()).append("\n");
    sb.append(misiones.listarMisionesCompletadas()).append("\n");
    return sb.toString();
  }

  public void agregarNuevaMision(IMision nuevaMision){
    misiones.agregarMision(nuevaMision);
  }

  public void actualizarMisiones(String tipo, String data){
    misiones.actualizarMisiones(tipo, data);
  }

  


}//fin de clase