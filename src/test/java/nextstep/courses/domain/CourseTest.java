package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class CourseTest {

    @Test
    @DisplayName("Course 는 기수 단위로 운영된다")
    void courseWithTerm() {
        Integer term = 19;
        Course course = new Course(1L, "TDD, 클린 코드 with Java 19기", 1L, term, LocalDateTime.now(), LocalDateTime.now());
        assertThat(course.getTerm()).isEqualTo(term);
    }
}
