package com.example.microusuarios.response;

import java.util.List;

import com.example.microusuarios.model.Mantenimiento;

import lombok.Data;

@Data
public class MantenimientoResponse {
    private List<Mantenimiento> mantenimiento;

    public List<Mantenimiento> getMantenimiento() {
        return this.mantenimiento;
    }

    public void setMantenimiento(List<Mantenimiento> mantenimiento) {
        this.mantenimiento = mantenimiento;
    }
}
