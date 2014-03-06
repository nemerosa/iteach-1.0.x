package net.nemerosa.iteach.service.impl;

import net.nemerosa.iteach.dao.SchoolRepository;
import net.nemerosa.iteach.dao.model.TSchool;
import net.nemerosa.iteach.service.SecurityUtils;
import net.nemerosa.iteach.service.TeacherService;
import net.nemerosa.iteach.service.model.School;
import net.nemerosa.iteach.service.model.SchoolForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

    private final SchoolRepository schoolRepository;
    private final SecurityUtils securityUtils;
    private final Function<? super TSchool, ? extends School> schoolFn = t -> new School(
            t.getId(),
            t.getTeacherId(),
            t.getName(),
            t.getColour(),
            t.getContact(),
            t.getHourlyRate(),
            t.getPostalAddress(),
            t.getPhone(),
            t.getMobilePhone(),
            t.getEmail(),
            t.getWebSite()
    );

    @Autowired
    public TeacherServiceImpl(SchoolRepository schoolRepository, SecurityUtils securityUtils) {
        this.schoolRepository = schoolRepository;
        this.securityUtils = securityUtils;
    }

    @Override
    public List<School> getSchools(int teacherId) {
        // Checks the teacher access
        securityUtils.checkTeacher(teacherId);
        // Lists
        return schoolRepository.findAll(teacherId).parallelStream().map(schoolFn).collect(Collectors.toList());
    }

    @Override
    public int createSchool(int teacherId, SchoolForm form) {
        // Checks the teacher access
        securityUtils.checkTeacher(teacherId);
        // Creation
        return schoolRepository.create(
                teacherId,
                form.getName(),
                form.getColour(),
                form.getContact(),
                form.getEmail(),
                form.getHourlyRate(),
                form.getPostalAddress(),
                form.getPhone(),
                form.getMobilePhone(),
                form.getWebSite()
        );
    }

    @Override
    public School getSchool(int teacherId, int schoolId) {
        // Checks the teacher access
        securityUtils.checkTeacher(teacherId);
        // Access
        return schoolFn.apply(schoolRepository.getById(teacherId, schoolId));
    }

}