package com.houseWork.service.appointment;

import com.houseWork.dao.appointment.AppointmentMapper;
import com.houseWork.entity.appointment.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AppointmentServiceImpl implements AppointmentService{

    @Autowired
    private AppointmentMapper appointmentMapper;

    @Override
    public Appointment add(Appointment appointment) {
        appointmentMapper.insert(appointment);
        return appointmentMapper.findById(appointment.getId());
    }

    @Override
    public List<Appointment> getAll(Map map) {
        return appointmentMapper.getAll(map);
    }

    @Override
    public Appointment getById(Integer appId) {
        return appointmentMapper.findById(appId);
    }
}
