import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Doctor;
import model.Paciente;
import model.Cita;
import model.Administrador;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Doctor> doctores = new ArrayList<>();
        List<Paciente> pacientes = new ArrayList<>();
        List<Cita> citas = new ArrayList<>();

        while (true) {
            System.out.println("\nMENU:");
            System.out.println("1. Dar de alta doctor");
            System.out.println("2. Dar de alta paciente");
            System.out.println("3. Crear cita");
            System.out.println("4. Mostrar citas");
            System.out.println("5. Salir");
            System.out.print("Ingrese la opción deseada: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el nombre del doctor:");
                    String nombreDoctor = scanner.nextLine();
                    System.out.println("Ingrese la especialidad del doctor:");
                    String especialidadDoctor = scanner.nextLine();
                    Doctor doctor = new Doctor(generarId(doctores), nombreDoctor, especialidadDoctor);
                    doctores.add(doctor);
                    break;
                case 2:
                    System.out.println("Ingrese el nombre del paciente:");
                    String nombrePaciente = scanner.nextLine();
                    Paciente paciente = new Paciente(generarId(pacientes), nombrePaciente);
                    pacientes.add(paciente);
                    break;
                case 3:
                    if (doctores.isEmpty() || pacientes.isEmpty()) {
                        System.out.println("Debe dar de alta al menos un doctor y un paciente antes de crear una cita.");
                        break;
                    }
                    System.out.println("Ingrese el índice del doctor:");
                    mostrarDoctores(doctores);
                    int indiceDoctor = scanner.nextInt();
                    System.out.println("Ingrese el índice del paciente:");
                    mostrarPacientes(pacientes);
                    int indicePaciente = scanner.nextInt();
                    Doctor doctorSeleccionado = doctores.get(indiceDoctor);
                    Paciente pacienteSeleccionado = pacientes.get(indicePaciente);
                    System.out.println("Ingrese la fecha y hora de la cita (YYYY-MM-DD HH:MM):");
                    scanner.nextLine(); // Limpiar buffer
                    String fechaHoraStr = scanner.nextLine();
                    LocalDateTime fechaHora = LocalDateTime.parse(fechaHoraStr.replace(" ", "T"));
                    System.out.println("Ingrese el motivo de la cita:");
                    String motivo = scanner.nextLine();
                    Cita cita = new Cita(generarId(citas), fechaHora, motivo, doctorSeleccionado, pacienteSeleccionado);
                    citas.add(cita);
                    break;
                case 4:
                    mostrarCitas(citas);
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
            }
        }
    }

    private static int generarId(List<?> lista) {
        if (lista.isEmpty()) {
            return 1; // Si la lista está vacía, devolvemos 1 como primer id
        } else {
            // Obtenemos el id del último elemento y le sumamos 1 para generar un nuevo id
            return lista.size() + 1;
        }
    }

    private static void mostrarDoctores(List<Doctor> doctores) {
        for (int i = 0; i < doctores.size(); i++) {
            System.out.println(i + ". " + doctores.get(i).getNombre());
        }
    }

    private static void mostrarPacientes(List<Paciente> pacientes) {
        for (int i = 0; i < pacientes.size(); i++) {
            System.out.println(i + ". " + pacientes.get(i).getNombre());
        }
    }

    private static void mostrarCitas(List<Cita> citas) {
        if (citas.isEmpty()) {
            System.out.println("No hay citas registradas.");
        } else {
            System.out.println("Citas registradas:");
            for (Cita cita : citas) {
                System.out.println("Fecha y hora: " + cita.getFechaHora());
                System.out.println("Motivo: " + cita.getMotivo());
                System.out.println("Doctor: " + cita.getDoctor().getNombre());
                System.out.println("Paciente: " + cita.getPaciente().getNombre());
                System.out.println();
            }
        }
    }
}
