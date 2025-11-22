package org.iesvdm.datosbanco.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@Builder


    public class User {

        private long id;
        private String nombre;
        private String direccion;
        private String telefono;
        private String fecha_nac;

        // Constructor vacío
        public User() {}

        // Constructor completo
        public User(long id, String nombre, String direccion, String telefono, String fecha_nac) {
            this.id = id;
            this.nombre = nombre;
            this.direccion = direccion;
            this.telefono = telefono;
            this.fecha_nac = fecha_nac;
        }

        // ======= GETTERS =======
        public long getId() { return id; }
        public String getNombre() { return nombre; }
        public String getDireccion() { return direccion; }
        public String getTelefono() { return telefono; }
        public String getFecha_nac() { return fecha_nac; }

        // ======= SETTERS =======
        public void setId(long id) { this.id = id; }
        public void setNombre(String nombre) { this.nombre = nombre; }
        public void setDireccion(String direccion) { this.direccion = direccion; }
        public void setTelefono(String telefono) { this.telefono = telefono; }
        public void setFecha_nac(String fecha_nac) { this.fecha_nac = fecha_nac; }

        // ======= BUILDER MANUAL =======
        public static class Builder {
            private long id;
            private String nombre;
            private String direccion;
            private String telefono;
            private String fecha_nac;

            public Builder id(long id) {
                this.id = id;
                return this;
            }

            public Builder nombre(String nombre) {
                this.nombre = nombre;
                return this;
            }

            public Builder direccion(String direccion) {
                this.direccion = direccion;
                return this;
            }

            public Builder telefono(String telefono) {
                this.telefono = telefono;
                return this;
            }

            public Builder fecha_nac(String fecha_nac) {
                this.fecha_nac = fecha_nac;
                return this;
            }

            public User build() {
                return new User(id, nombre, direccion, telefono, fecha_nac);
            }


        }

        public static Builder builder() {
            return new Builder();
        }
    }

