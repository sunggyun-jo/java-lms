package nextstep.qna.domain;

import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteHistoryTest {

    @Test
    @DisplayName("DeleteHistory 생성을 위한 factory method")
    void createDeleteHistory() {
        Long contentId = 1L;
        NsUser deletedBy = NsUser.GUEST_USER;

        DeleteHistory deleteHistory = new DeleteHistory(ContentType.QUESTION, contentId, deletedBy, LocalDateTime.now());
        DeleteHistory deleteHistoryOfQuestion = DeleteHistory.ofQuestion(1L, deletedBy);

        assertThat(deleteHistoryOfQuestion).isEqualTo(deleteHistory);

        DeleteHistory answerDeleteHistory = new DeleteHistory(ContentType.ANSWER, contentId, deletedBy, LocalDateTime.now());
        DeleteHistory deleteHistoryOfAnswer = DeleteHistory.ofAnswer(1L, deletedBy);

        assertThat(deleteHistoryOfAnswer).isEqualTo(answerDeleteHistory);
    }
}
