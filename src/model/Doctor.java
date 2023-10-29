package model;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Doctor extends User{

    //Atributo
    private String speciality;
    //Se crea un colección de objetos y se instancia
    private ArrayList<AvailableAppointment> availableAppointments = new ArrayList<>();


    //Metodo constructor
    public Doctor(String name, String email){
        super(name,email);
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }


    //Se cre un metodo que se encarga de incrementar la data
    public void addAvailableAppointment(String date, String time){

        availableAppointments.add(new AvailableAppointment(date, time));

    }
    //Se crea un elemento que se encarga de devolver la coleccion con la data creada
    public ArrayList<AvailableAppointment> getAvailableAppointments(){
        return  availableAppointments;
    }

    @Override
    public String toString() {
        return super.toString() + "\nSpeciality: " + speciality + "\nAvailable: " + availableAppointments.toString();
    }

    @Override
    public void showDataUser() {
        System.out.println("Empelado del Hospital: Cruz Roja");
        System.out.println("Departamento: Cancerologia");
    }

    //Se crea una clase estatica anidada
    public static class AvailableAppointment{
        private int id_avaiableAppointment;
        private Date date;
        private String time;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        public AvailableAppointment(String date, String time){
            try{
                this.date = format.parse(date);
            } catch (ParseException e){
                e.printStackTrace();
            }
            this.time = time;
        }

        public int getId_avaiableAppointment() {
            return id_avaiableAppointment;
        }

        public void setId_avaiableAppointment(int id_avaiableAppointment) {
            this.id_avaiableAppointment = id_avaiableAppointment;
        }

        public Date getDate(String DATE) {
            return date;
        }

        public String getDate() {

            return format.format(date);
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        @Override
        public String toString() {
            return "Available appointment \nDate: " +  date + "\nTime: " + time;
        }
    }
}
