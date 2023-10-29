package ui;

import model.Doctor;

import java.rmi.MarshalledObject;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class UiPatientMenu {
    public static void shorPatientMenu(){
        int response = 0;
        do {
            System.out.println("\n\n");
            System.out.println("Patien");
            System.out.println("Welcome: " + UIMenu.patiendLogged.getName());
            System.out.println("1. Book an appointment");
            System.out.println("2. My appointments");
            System.out.println("0. Logout");

            Scanner sc = new Scanner(System.in);
            response = Integer.valueOf(sc.nextLine());

            switch (response){
                case 1:
                    showBookAppointmentMenu();
                    break;
                case 2:
                    showPatientMyAppointments();
                    break;
                case 0:
                    UIMenu.showMenu();
                    break;
            }

        }while( response !=0);
    }

    private static void showBookAppointmentMenu(){
        int response=0;
        do{
            System.out.println("::Book an appointment");
            System.out.println(":: Select date: ");
            //Numeración de la lista de fechas
            //Indice de la fecha selecciona por el paciente
            Map<Integer, Map<Integer, Doctor>> doctors = new TreeMap<>();
            int k = 0;
            for (int i = 0; i < UiDoctorMenu.doctorsAvailableAppointments.size(); i++) {

                ArrayList<Doctor.AvailableAppointment> availableAppointments = UiDoctorMenu.doctorsAvailableAppointments.get(i).getAvailableAppointments();

                Map<Integer, Doctor> doctorAppointments = new TreeMap<>();
                for (int j = 0; j < availableAppointments.size(); j++) {

                  k++;
                  System.out.println(k + "." + availableAppointments.get(j).getDate());
                  doctorAppointments.put(Integer.valueOf(j),UiDoctorMenu.doctorsAvailableAppointments.get(i));
                  doctors.put(Integer.valueOf(k), doctorAppointments);

                }
            }

            Scanner sc = new Scanner(System.in);
            int responseDateSelected = Integer.valueOf(sc.nextLine());

            Map<Integer, Doctor> doctorAvailbaleSelected = doctors.get(responseDateSelected);
            Integer indexDate = 0;
            Doctor doctorSelected = new Doctor("","");
            for (Map.Entry<Integer, Doctor> doc :doctorAvailbaleSelected.entrySet()) {
                indexDate = doc.getKey();
                doctorSelected = doc.getValue();
            }

            System.out.println(doctorSelected.getName()
                    + ". Date " + doctorSelected.getAvailableAppointments().get(indexDate).getDate()
                    + ". Time: " + doctorSelected.getAvailableAppointments().get(indexDate).getTime());

            System.out.println("Confirm your appointment: \n1. Yes \n2. Change date");
            response = Integer.valueOf(sc.nextLine());

            if(response  == 1){
                UIMenu.patiendLogged.addAppointmentDoctors(
                        doctorSelected,
                        doctorSelected.getAvailableAppointments().get(indexDate).getDate(null),
                        doctorSelected.getAvailableAppointments().get(indexDate).getTime());

                shorPatientMenu();
            }

        }while(response!=0);
    }

    private static void showPatientMyAppointments(){
        int response=0;
        do{
            System.out.println("::My appointemts");
            if(UIMenu.patiendLogged.getAppointmentDoctors().size() == 0){
                System.out.println("Don´t have appointments");
                break;
            }

            for (int i = 0; i < UIMenu.patiendLogged.getAppointmentDoctors().size(); i++) {
                int j = i +1;
                System.out.println(j + "." +
                        "Date: " + UIMenu.patiendLogged.getAppointmentDoctors().get(i).getDate()+
                        " Time: " + UIMenu.patiendLogged.getAppointmentDoctors().get(i).getTime()+
                        "\n Doctor: " + UIMenu.patiendLogged.getAppointmentDoctors().get(i).getDoctor().getName());
            }

            System.out.println("0. Return");

        }while(response!=0);
    }
}
