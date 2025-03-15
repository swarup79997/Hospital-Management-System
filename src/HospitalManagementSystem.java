import java.util.*;
import java.io.*;

class Doctor implements Serializable {
    String name, specialization;
    int experience;

    Doctor(String name, String specialization, int experience) {
        this.name = name;
        this.specialization = specialization;
        this.experience = experience;
    }

    @Override
    public String toString() {
        return name + "," + specialization + "," + experience;
    }
}

class Patient implements Serializable {
    String name, disease;
    int age;
    double consultationFee;

    Patient(String name, int age, String disease) {
        this.name = name;
        this.age = age;
        this.disease = disease;
        this.consultationFee = 1000.0;
    }

    @Override
    public String toString() {
        return name + "," + age + "," + disease + "," + consultationFee;
    }
}

class Appointment implements Serializable {
    Patient patient;
    String doctorName, appointmentDate;

    Appointment(Patient patient, String doctorName, String appointmentDate) {
        this.patient = patient;
        this.doctorName = doctorName;
        this.appointmentDate = appointmentDate;
    }

    @Override
    public String toString() {
        return patient.name + "," + doctorName + "," + appointmentDate;
    }
}

class Test implements Serializable {
    String testName;
    double cost;

    Test(String testName, double cost) {
        this.testName = testName;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return testName + "," + cost;
    }
}

public class HospitalManagementSystem {
    static List<Doctor> doctors = new ArrayList<>();
    static List<Patient> patients = new ArrayList<>();
    static List<Appointment> appointments = new ArrayList<>();
    static List<Test> availableTests = new ArrayList<>();
    static Map<String, List<Test>> patientTests = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadData();
        loadTests(); // Load tests from the file
        while (true) {
            System.out.println("\nWelcome to the Hospital Management System");
            System.out.println("1. Manage Doctors");
            System.out.println("2. Manage Patients");
            System.out.println("3. Appointment System");
            System.out.printf("4. Test Management\n");
            System.out.printf("5. Billing System\n");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    manageDoctors();
                    break;
                case 2:
                    managePatients();
                    break;
                case 3:
                    manageAppointments();
                    break;
                case 4:
                    manageTestManagement();
                    break;
                case 5:
                    manageBillingSystem();
                    break;
                case 6:
                    saveData();
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice, try again!");
            }
        }
    }

    static void manageDoctors() {
        System.out.println("1. Add Doctor\n2. View Doctors\n3. Update Doctor\n4. Delete Doctor");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Enter Doctor Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter Specialization: ");
                String specialization = scanner.nextLine();
                System.out.print("Enter Experience (years): ");
                int experience = scanner.nextInt();
                doctors.add(new Doctor(name, specialization, experience));
                saveDoctors();
                System.out.println("Doctor added successfully!");
                break;
            case 2:
                displayDoctors();
                break;
            case 3:
                updateDoctor();
                break;
            case 4:
                deleteDoctor();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    static void updateDoctor() {
        System.out.print("Enter Doctor Name to update: ");
        String name = scanner.nextLine();
        Optional<Doctor> doctorOpt = doctors.stream().filter(d -> d.name.equals(name)).findFirst();

        if (doctorOpt.isPresent()) {
            Doctor doctor = doctorOpt.get();
            System.out.print("Enter New Specialization: ");
            doctor.specialization = scanner.nextLine();
            System.out.print("Enter New Experience (years): ");
            doctor.experience = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            saveDoctors(); // Save updated doctors list
            System.out.println("Doctor updated successfully!");
        } else {
            System.out.println("Doctor not found.");
        }
    }

    static void deleteDoctor() {
        System.out.print("Enter Doctor Name to delete: ");
        String name = scanner.nextLine();
        doctors.removeIf(d -> d.name.equals(name));
        saveDoctors(); // Save updated doctors list
        System.out.println("Doctor deleted successfully!");
    }

    static void managePatients() {
        System.out.println("1. Add Patient\n2. View Patients\n3. Update Patient\n4. Delete Patient");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Enter Patient Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter Age: ");
                int age = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter Disease: ");
                String disease = scanner.nextLine();
                patients.add(new Patient(name, age, disease));
                savePatients();
                System.out.println("Patient added successfully!");
                break;
            case 2:
                displayPatients();
                break;
            case 3:
                updatePatient();
                break;
            case 4:
                deletePatient();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    static void updatePatient() {
        System.out.print("Enter Patient Name to update: ");
        String name = scanner.nextLine();
        Optional<Patient> patientOpt = patients.stream().filter(p -> p.name.equals(name)).findFirst();

        if (patientOpt.isPresent()) {
            Patient patient = patientOpt.get();
            System.out.print("Enter New Age: ");
            patient.age = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter New Disease: ");
            patient.disease = scanner.nextLine();
            savePatients(); // Save updated patients list
            System.out.println("Patient updated successfully!");
        } else {
            System.out.println("Patient not found.");
        }
    }

    static void deletePatient() {
        System.out.print("Enter Patient Name to delete: ");
        String name = scanner.nextLine();
        patients.removeIf(p -> p.name.equals(name));
        savePatients(); // Save updated patients list
        System.out.println("Patient deleted successfully!");
    }

    static void manageAppointments() {
        System.out
                .println("1. Schedule Appointment\n2. View Appointments\n3. Update Appointment\n4. Delete Appointment");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                scheduleAppointment();
                break;
            case 2:
                displayAppointments();
                break;
            case 3:
                updateAppointment();
                break;
            case 4:
                deleteAppointment();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    static void scheduleAppointment() {
        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Appointment Date (DD/MM/YYYY): ");
        String date = scanner.nextLine();
        Optional<Patient> patient = patients.stream().filter(p -> p.name.equals(name)).findFirst();
        if (patient.isPresent()) {
            System.out.print("Available Doctors on " + date + ":\n");
            displayAvailableDoctors(date);
            System.out.print("Enter Doctor Name to assign: ");
            String doctorName = scanner.nextLine();

            if (isDoctorAvailable(doctorName, date)) {
                appointments.add(new Appointment(patient.get(), doctorName, date));
                saveAppointments();
                System.out.println("Appointment scheduled successfully!");
            } else {
                System.out.println("Doctor is not available on this date.");
            }
        } else {
            System.out.println("Patient not found.");
        }
    }

    static void updateAppointment() {
        System.out.print("Enter Patient Name for Appointment to update: ");
        String patientName = scanner.nextLine();
        Optional<Appointment> appointmentOpt = appointments.stream()
                .filter(a -> a.patient.name.equals(patientName)).findFirst();

        if (appointmentOpt.isPresent()) {
            Appointment appointment = appointmentOpt.get();
            System.out.print("Enter New Appointment Date (DD/MM/YYYY): ");
            String newDate = scanner.nextLine();
            appointment.appointmentDate = newDate;
            saveAppointments(); // Save updated appointments list
            System.out.println("Appointment updated successfully!");
        } else {
            System.out.println("Appointment not found for this patient.");
        }
    }

    static void deleteAppointment() {
        System.out.print("Enter Patient Name for Appointment to delete: ");
        String patientName = scanner.nextLine();
        appointments.removeIf(a -> a.patient.name.equals(patientName));
        saveAppointments(); // Save updated appointments list
        System.out.println("Appointment deleted successfully!");
    }

    static void manageTestManagement() {
        System.out.println("1. Assign Tests\n2. Add New Test \n3. View Tests");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1) {
            System.out.print("Enter Patient Name: ");
            String patientName = scanner.nextLine();
            Optional<Patient> patient = patients.stream().filter(p -> p.name.equals(patientName)).findFirst();
            if (patient.isPresent()) {
                Patient p = patient.get();
                System.out.println("Available Tests:");
                displayTests();
                while (true) {
                    System.out.print("Enter Test number to assign: ");
                    int testNumber = scanner.nextInt();
                    scanner.nextLine();
                    if (testNumber > 0 && testNumber <= availableTests.size()) {
                        Test test = availableTests.get(testNumber - 1);
                        assignTestToPatient(p.name, test);
                        System.out.println("Test assigned: " + test.testName);
                    } else {
                        System.out.println("Invalid test number.");
                    }
                    System.out.print("Assign another test? (y/n): ");
                    String assignMore = scanner.nextLine();
                    if (assignMore.equalsIgnoreCase("n")) {
                        break;
                    }
                }
                System.out.println("Tests assigned successfully!");
            } else {
                System.out.println("Patient not found.");
            }
        } else if (choice == 2) {
            System.out.print("Enter Test Name: ");
            String testName = scanner.nextLine();
            System.out.print("Enter Test Cost: ");
            double cost = scanner.nextDouble();
            scanner.nextLine();
            availableTests.add(new Test(testName, cost));
            saveTests(); // Save the new test
            System.out.println("New test added successfully!");
        }else if(choice == 3){
            displayTests();
        }
        else {
            System.out.println("Invalid choice.");
        }
    }

    static void assignTestToPatient(String patientName, Test test) {
        if (!patientTests.containsKey(patientName)) {
            patientTests.put(patientName, new ArrayList<>());
        }
        patientTests.get(patientName).add(test);
    }

    static void manageBillingSystem() {
        System.out.println("1. Generate Bill");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1) {
            System.out.print("Enter Patient Name for Billing: ");
            String patientName = scanner.nextLine();
            Optional<Patient> patient = patients.stream().filter(p -> p.name.equals(patientName)).findFirst();
            if (patient.isPresent()) {
                System.out.print("Enter Number of Days for Hospital Stay: ");
                int stayDays = scanner.nextInt();
                scanner.nextLine();
                Patient p = patient.get();
                double totalBill = p.consultationFee;

                // Calculate test costs
                double testCost = 0;
                if (patientTests.containsKey(patientName)) {
                    for (Test test : patientTests.get(patientName)) {
                        testCost += test.cost;
                    }
                }
                totalBill += testCost;

                // Add hospital stay charges (Rs 400 per day)
                double stayCharges = stayDays * 400;
                totalBill += stayCharges;

                // Display billing details in a table format
                System.out.println("\nFinal Bill for " + patientName + ":");
                System.out.println("----------------------------------------------------");
                System.out.printf("| %-30s | %-15s |\n", "Description", "Amount (Rs)");
                System.out.println("----------------------------------------------------");
                System.out.printf("| %-30s | %-15.2f |\n", "Consultation Fee", p.consultationFee);
                System.out.printf("| %-30s | %-15.2f |\n", "Tests Cost", testCost);
                System.out.printf("| %-30s | %-15.2f |\n", "Hospital Stay Charges", stayCharges);
                System.out.println("----------------------------------------------------");
                System.out.printf("| %-30s | %-15.2f |\n", "Total Bill", totalBill);
                System.out.println("----------------------------------------------------");
            } else {
                System.out.println("Patient not found.");
            }
        }
    }

    static void displayAvailableDoctors(String date) {
        System.out.println("-----------------------------------------------");
        System.out.printf("| %-20s | %-20s |\n", "Doctor Name", "Specialization");
        System.out.println("-----------------------------------------------");
        for (Doctor doctor : doctors) {
            if (isDoctorAvailable(doctor.name, date)) {
                System.out.printf("| %-20s | %-20s |\n", doctor.name, doctor.specialization);
            }
        }
        System.out.println("-----------------------------------------------");
    }

    static boolean isDoctorAvailable(String doctorName, String date) {
        return appointments.stream().noneMatch(a -> a.doctorName.equals(doctorName) && a.appointmentDate.equals(date));
    }

    static void displayDoctors() {
        System.out.println("------------------------------------------------------------");
        System.out.printf("| %-20s | %-20s | %-10s |\n", "Doctor Name", "Specialization", "Experience");
        System.out.println("------------------------------------------------------------");
        for (Doctor doctor : doctors) {
            System.out.printf("| %-20s | %-20s | %-10d |\n", doctor.name, doctor.specialization, doctor.experience);
        }
        System.out.println("------------------------------------------------------------");
    }

    static void displayPatients() {
        System.out.println("------------------------------------------------------------");
        System.out.printf("| %-20s | %-10s | %-20s |\n", "Patient Name", "Age", "Disease");
        System.out.println("------------------------------------------------------------");
        for (Patient patient : patients) {
            System.out.printf("| %-20s | %-10d | %-20s |\n", patient.name, patient.age, patient.disease);
        }
        System.out.println("------------------------------------------------------------");
    }

    static void displayAppointments() {
        System.out.println("------------------------------------------------------------------");
        System.out.printf("| %-20s | %-20s | %-15s |\n", "Patient Name", "Doctor Name", "Appointment Date");
        System.out.println("------------------------------------------------------------------");
        for (Appointment appointment : appointments) {
            System.out.printf("| %-20s | %-20s | %-16s |\n", appointment.patient.name, appointment.doctorName,
                    appointment.appointmentDate);
        }
        System.out.println("------------------------------------------------------------------");
    }

    static void displayTests() {
        System.out.println("-------------------------------------");
        System.out.printf("| %-20s | %-10s |\n", "Test Name", "Cost (Rs)");
        System.out.println("-------------------------------------");
        for (int i = 0; i < availableTests.size(); i++) {
            Test test = availableTests.get(i);
            System.out.printf("| %-20s | %-10.2f |\n", test.testName, test.cost);
        }
        System.out.println("-------------------------------------");
    }

    static void saveData() {
        saveDoctors();
        savePatients();
        saveAppointments();
        saveTests(); // Save tests data
    }

    // Update these methods with the correct paths

    static void loadData() {
        loadDoctors();
        loadPatients();
        loadAppointments();
    }

    static void loadDoctors() {
        try (BufferedReader br = new BufferedReader(new FileReader("../data/doctors.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    doctors.add(new Doctor(parts[0], parts[1], Integer.parseInt(parts[2])));
                }
            }
        } catch (IOException e) {
            System.out.println("No doctors data found, starting fresh.");
        }
    }

    static void loadPatients() {
        try (BufferedReader br = new BufferedReader(new FileReader("../data/patients.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    Patient patient = new Patient(parts[0], Integer.parseInt(parts[1]), parts[2]);
                    patient.consultationFee = Double.parseDouble(parts[3]);
                    patients.add(patient);
                } else if (parts.length == 3) {
                    patients.add(new Patient(parts[0], Integer.parseInt(parts[1]), parts[2]));
                }
            }
        } catch (IOException e) {
            System.out.println("No patients data found, starting fresh.");
        }
    }

    static void loadAppointments() {
        try (BufferedReader br = new BufferedReader(new FileReader("../data/appointments.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    Optional<Patient> patient = patients.stream().filter(p -> p.name.equals(parts[0])).findFirst();
                    if (patient.isPresent()) {
                        appointments.add(new Appointment(patient.get(), parts[1], parts[2]));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("No appointments data found, starting fresh.");
        }
    }

    static void saveDoctors() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("../data/doctors.txt"))) {
            for (Doctor doctor : doctors) {
                bw.write(doctor.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving doctors data: " + e.getMessage());
        }
    }

    static void savePatients() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("../data/patients.txt"))) {
            for (Patient patient : patients) {
                bw.write(patient.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving patients data: " + e.getMessage());
        }
    }

    static void saveAppointments() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("../data/appointments.txt"))) {
            for (Appointment appointment : appointments) {
                bw.write(appointment.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving appointments data: " + e.getMessage());
        }
    }

    static void loadTests() {
        try (BufferedReader br = new BufferedReader(new FileReader("../data/tests.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    availableTests.add(new Test(parts[0], Double.parseDouble(parts[1])));
                }
            }
            System.out.println("Tests loaded successfully.");
        } catch (IOException e) {
            System.out.println("No tests data found, starting fresh.");
        }
    }

    static void saveTests() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("../data/tests.txt"))) {
            for (Test test : availableTests) {
                bw.write(test.toString());
                bw.newLine();
            }
            System.out.println("Tests saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving tests data: " + e.getMessage());
        }
    }

}
