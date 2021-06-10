package com.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "shift_time")
public class ShiftTime {
    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Setter(value = AccessLevel.NONE)
    private String id;

    @Column(name = "start_time")
    @NotNull(message = "Start shift time cannot empty")
    private Date startTime;

    @Column(name = "end_time")
    @NotNull(message = "End shift time cannot empty")
    private Date endTime;

    @Override
    public String toString() {
        DateFormat formatter = new SimpleDateFormat("hh:mm");
        String shiftTimeStart = formatter.format(getStartTime());
        String shiftTimeEnd = formatter.format(getEndTime());

        return shiftTimeStart + " - " + shiftTimeEnd;
    }
}
