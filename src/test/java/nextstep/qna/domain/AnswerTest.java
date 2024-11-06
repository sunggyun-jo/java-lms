package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("답변 상태를 삭제 상태로 변경")
    void deleteAnswer() {
        assertThat(A1.isDeleted()).isFalse();
        A1.delete();
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("답변 삭제 이력을 DeleteHistory 에 남긴다")
    void deleteAnswerAndGetDeleteHistory() {
        assertThat(A2.isDeleted()).isFalse();
        DeleteHistory deleteHistory = A2.delete();
        assertThat(A2.isDeleted()).isTrue();

        assertThat(deleteHistory.getContentId()).isEqualTo(A2.getId());
    }
}
