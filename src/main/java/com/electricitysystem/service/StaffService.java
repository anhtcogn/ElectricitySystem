package com.electricitysystem.service;

import com.electricitysystem.entity.StaffEntity;

public interface StaffService {

    StaffEntity getStaffById(int id);

    StaffEntity getStaffByUsername(String username);
}

