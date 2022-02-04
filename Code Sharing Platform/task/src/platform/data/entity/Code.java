package platform.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Entity
@Table(name = "code")
public class Code {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "code")
    private String code;

    @Column(name = "token")
    private String token;

    @Column(name = "time")
    private Long time;

    @Column(name = "views")
    private Long views;

    public String getDateFormatted(String pattern) {
        return getDate().format(DateTimeFormatter.ofPattern(pattern));
    }

    public String getDateFormatted() {
        return getDateFormatted("yy/MM/dd HH:mm:ss");
    }

    public Long getFormattedTime() {
        return time == -1 ? 0 : time;
    }

    public Long getFormattedViews() {
        return views == -1 ? 0 : views;
    }
}
