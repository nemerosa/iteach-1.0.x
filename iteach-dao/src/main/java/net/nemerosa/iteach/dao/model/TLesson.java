package net.nemerosa.iteach.dao.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TLesson {

    private final int id;
    private final int teacherId;
    private final int studentId;
    private final LocalDateTime from;
    private final LocalDateTime to;
    private final String location;

}
