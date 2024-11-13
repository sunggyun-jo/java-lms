package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswersTest {

    @Test
    @DisplayName("질문자와 답변자가 다른 경우가 있는지 확인한다")
    void hasAnotherAnswers() {
        Answers answers = new Answers();
        answers.add(AnswerTest.A1);
        answers.add(AnswerTest.A2);

        assertThat(answers.hasAnotherAnswers(NsUserTest.JAVAJIGI)).isTrue();
    }

    @Test
    @DisplayName("답변 목록을 삭제 한다")
    void deleteAnswers() {
        Answers emptyAnswers = new Answers();
        DeleteHistories emptyDeleteHistoryList = emptyAnswers.deleteAnswers();
        assertThat(emptyDeleteHistoryList.asList()).isEmpty();

        Answers answers = new Answers();
        answers.add(AnswerTest.A1);
        answers.add(AnswerTest.A2);

        DeleteHistories deleteHistoryList = answers.deleteAnswers();
        assertThat(deleteHistoryList.asList()).contains(
                DeleteHistory.ofAnswer(AnswerTest.A1.getId(), AnswerTest.A1.getWriter()),
                DeleteHistory.ofAnswer(AnswerTest.A2.getId(), AnswerTest.A2.getWriter())
        );
    }
}
