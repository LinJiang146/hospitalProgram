package com.wei.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wei.model.user.Patient;
import org.mapstruct.Mapper;

@Mapper
public interface PatientMapper extends BaseMapper<Patient> {
}
