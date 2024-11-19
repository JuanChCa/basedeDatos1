
import java.io.Serializable;

public class estudiante implements Serializable {
    private String nombre;
    private int edad;
    private String matricula;
    private double notaMedia;
    private boolean matriculado;

    // Constructor
    public estudiante(String nombre, int edad, String matricula, double notaMedia, boolean matriculado) {
        this.nombre = nombre;
        this.edad = edad;
        this.matricula = matricula;
        this.notaMedia = notaMedia;
        this.matriculado = matriculado;
    }

    @Override
    public String toString() {
        return "Estudiante{" +  "nombre='" + nombre + '\'' +  ", edad=" + edad + ", matricula='" + matricula + '\'' +
                ", notaMedia=" + notaMedia +    (matriculado ? "Si":"No")  +
                '}';
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getMatricula() {
        return matricula;
    }

    public double getNotaMedia() {
        return notaMedia;
    }

    public boolean isMatriculado() {
        return matriculado;
    }
}