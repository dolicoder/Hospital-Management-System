package hospitalsystem;
import java.util.*;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class MainHospital {
    static int TotalHospitalMoney = 37000000;

    public static void main(String[] args) {

        HospitalManagement hospital = new HospitalManagement();
        hospital.hiredDoctors();

        VaccineDepartment vaccineDepartment = new VaccineDepartment();
        vaccineDepartment.initializeVaccines();
        Bst b = new Bst();
        Scanner scanner = new Scanner(System.in);

        int randomIndex;
        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    vaccineDepartment.Vaccinate("COVID-19");
                } catch (NullPointerException e) {
                    System.out.println("An error occurred: " + e.getMessage());
                    System.out.println("Failed to administer the vaccine.");
                }
            }
        };

        long delay = 300000;
        b.create();

        timer.scheduleAtFixedRate(task, 0, delay);

        while (true) {
            System.out.println("Hospital Management System Menu:");
            System.out.println("1. Add a new patient");
            System.out.println("2. Vaccinate a patient");
            System.out.println("3. View current Patients");
            System.out.println("4. Blood Donor");
            System.out.println("5. Blood Receiver");
            System.out.println("6. Display Receiver");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        try {
                            System.out.print("Enter patient name: ");
                            String patientName = scanner.nextLine();
                            System.out.print("Enter patient age: ");
                            int patientAge = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Enter patient illness: ");
                            String patientIllness = scanner.nextLine().trim();
                            System.out.println("Enter the Remainder:");
                            int remainder = scanner.nextInt();
                            Patient newPatient = new Patient(patientName, patientAge, patientIllness, remainder);

                            try {
                                Doctor assindoc = hospital.assignDoctor(newPatient);
                            } catch (NullPointerException e) {
                                System.out.print("An error occurred: " + e.getMessage());
                                System.out.print("We apologize for the inconvenience, but the doctor is currently unavailable at the moment.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Input error: Please enter valid data for the patient.");
                            scanner.nextLine();
                        }
                        break;

                    case 2:
                        System.out.print("Enter the vaccine name to administer: ");
                        String vaccineName = scanner.nextLine();
                        try {
                            vaccineDepartment.Vaccinate(vaccineName);
                        } catch (NullPointerException e) {
                            System.out.println("An error occurred: " + e.getMessage());
                            System.out.println("Failed to administer the vaccine.");
                        }
                        break;

                    case 3:
                    	try {
                            for (Doctor d : hospital.doctors) {
                                if (d == null) {
                                    return;
                                }
                                if (d.patientQueue.peek() != null) {
                                    System.out.printf(d.getName() + " is currently attending to a patient " + d.patientQueue.peek().getName() +
                                            "    Timing     " + d.patientQueue.peek().getDateAndTime().getHours() + ":" +
                                            d.patientQueue.peek().getDateAndTime().getMinutes() + "\n");
                                } else {
                                    System.out.printf(d.getName() + " is currently attending no patient yet " + "\n");
                                }
                                if (d.patientQueue.arrqueue[1] != null) {
                                    System.out.println("patient " + d.patientQueue.arrqueue[1].getName() + " timing: " +
                                            d.patientQueue.arrqueue[1].dateAndTime.getHours() + " " +
                                            d.patientQueue.arrqueue[1].dateAndTime.getMinutes());
                                }
                                if (d.patientQueue.arrqueue[2] != null) {
                                    System.out.println("patient " + d.patientQueue.arrqueue[2].getName() + " timing: " +
                                            d.patientQueue.arrqueue[2].dateAndTime.getHours() + " " +
                                            d.patientQueue.arrqueue[2].dateAndTime.getMinutes());
                                }
                            }
                        } catch (NullPointerException e) {
                            System.out.println("An error occurred: " + e.getMessage());
                            System.out.println("Failed to display patient information.");
                        }
                        break;

                    case 4:
                        b.donatecreate();
                        break;

                    case 5:
                        b.receivercreate();
                        break;

                    case 6:
                        b.donate();
                        break;

                    case 7:
                        System.out.println("Exiting the program.");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input error: Please enter a valid choice (1-7).");
                scanner.nextLine();
            } catch (NoSuchElementException e) {
                System.out.println("Input error: Please enter a valid choice (1-7).");
                break;
            }
        }
    }
}





/* OUTPUT

Hospital Management System Menu:
1. Add a new patient
2. Vaccinate a patient
3. View current Patients
4. Blood Donor
Patient Vaccinated
5. Blood Receiver
6. Display Receiver
7. Exit
Enter your choice: 1
Enter patient name: acb
Enter patient age: 54
Enter patient illness: Stroke
Enter the Remainder:
9
Assigned Doctor: Dr. Davis
Patient: acb
Timing: 11:0
Hospital Management System Menu:
1. Add a new patient
2. Vaccinate a patient
3. View current Patients
4. Blood Donor
5. Blood Receiver
6. Display Receiver
7. Exit
Enter your choice: 2
Enter the vaccine name to administer: COVID-19
Patient Vaccinated
Hospital Management System Menu:
1. Add a new patient
2. Vaccinate a patient
3. View current Patients
4. Blood Donor
5. Blood Receiver
6. Display Receiver
7. Exit
Enter your choice: 1
Enter patient name: xyz
Enter patient age: 78
Enter patient illness: Skin Cancer
Enter the Remainder:
2
Assigned Doctor: Dr. Johnson
Patient: xyz
Timing: 11:0
Hospital Management System Menu:
1. Add a new patient
2. Vaccinate a patient
3. View current Patients
4. Blood Donor
5. Blood Receiver
6. Display Receiver
7. Exit
Enter your choice: 3
Dr. Smith is currently attending no patient yet 
Dr. Johnson is currently attending to a patient xyz    Timing     11:0
Dr. Brown is currently attending no patient yet 
Dr. Williams is currently attending no patient yet 
Dr. Davis is currently attending to a patient acb*/