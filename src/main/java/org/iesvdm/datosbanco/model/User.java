package org.iesvdm.datosbanco.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private long id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String fecha_nac;

}
