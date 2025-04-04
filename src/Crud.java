public class Crud {
    public static void main(String[] args) {
        final int NUM_ALUM = 10;
        Alumno[] alumnos = new Alumno[NUM_ALUM];

        // Crea NUM_ALUM Alumnos con datos por defecto y los añade a la BD
        for (int i = 0; i < NUM_ALUM; i++) {
            int nia = 11111 + i;
            String nombre = "Alumno" + nia;
            alumnos[i] = new Alumno(nia, nombre);
            alumnos[i].create();
        }

        // Lee los datos de la BD del último alumno y actualiza su valor
        Alumno nuevoAlumno1 = alumnos[NUM_ALUM - 1].read();
        System.out.println("NIA: " + nuevoAlumno1.getNia() + "\tNombre: " + nuevoAlumno1.getNombre());

        // Modifica los datos del 1r alumno y actualiza la BD
        alumnos[0].setNombre("Mariano Rojas");
        alumnos[0].update();

      /*
         Creamos un nuevo Alumno con los datos del último alumno. 9009 es el
         id del último alumno
      */
        Alumno nuevoAlumno = Alumno.read(11115); //
        System.out.println("NIA: " + nuevoAlumno.getNia() + "\tNombre: " + nuevoAlumno.getNombre());

        // Borramos de la BD del último alumno
        alumnos[NUM_ALUM - 1].delete();
    }
}
