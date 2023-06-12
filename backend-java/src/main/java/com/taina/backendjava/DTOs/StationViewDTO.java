package com.taina.backendjava.DTOs;

public class StationViewDTO {

        private String nameFin;
        private String addressFin;
        private int departureCount;
        private int returnCount;

        public StationViewDTO(String nameFin, String addressFin, int departureCount, int returnCount) {
            this.nameFin = nameFin;
            this.addressFin = addressFin;
            this.departureCount = departureCount;
            this.returnCount = returnCount;
        }

        public StationViewDTO(String nameFin, String addressFin) {
            this.nameFin=nameFin;
            this.addressFin=addressFin;
        }


        public String getNameFin () {
            return nameFin;
        }

        public void setNameFin (String nameFin){
            this.nameFin = nameFin;
        }

        public String getAddressFin () {
            return addressFin;
        }

        public void setAddressFin (String addressFin){
            this.addressFin = addressFin;
        }

        public int getDepartureCount () {
            return departureCount;
        }

        public void setDepartureCount ( int departureCount){
            this.departureCount = departureCount;
        }

        public int getReturnCount () {
            return returnCount;
        }

        public void setReturnCount ( int returnCount){
            this.returnCount = returnCount;
        }

    }



