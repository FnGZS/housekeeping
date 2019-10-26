package com.houseWork.service.appointment;

import com.houseWork.entity.appointment.Appointment;

import java.util.List;
import java.util.Map;

public interface AppointmentService {
    Appointment add(Appointment appointment);

    List<Appointment> getAll(Map map);

    Appointment getById(Integer appId);
}
