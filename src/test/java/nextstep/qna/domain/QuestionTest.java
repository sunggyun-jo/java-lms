package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("질문 상태를 삭제 상태로 변경")
    void deleteQuestion() throws CannotDeleteException {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        assertThat(question.isDeleted()).isFalse();
        question.delete(NsUserTest.JAVAJIGI);
        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("로그인 사용자와 질문한 사람이 다를 경우 예외를 발생 시킨다")
    void deleteQuestionWithAnotherUser() {
        NsUser loginUser = NsUserTest.SANJIGI;
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        assertThat(question.isDeleted()).isFalse();

        assertThatThrownBy(() -> {
            question.delete(loginUser);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("답변이 없는 경우 삭제가 가능하다")
    void deleteQuestionWithoutAnswers() throws CannotDeleteException {
        NsUser loginUser = NsUserTest.JAVAJIGI;
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");

        assertThat(question.isDeleted()).isFalse();
        question.delete(loginUser);
        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("질문자와 답변자가 다른 경우 예외를 발생 시킨다")
    void deleteQuestionWithAnswersByAnother() {
        NsUser loginUser = NsUserTest.JAVAJIGI;
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        Answer answer = new Answer(NsUserTest.SANJIGI, question, "content1");
        question.addAnswer(answer);

        assertThat(question.isDeleted()).isFalse();

        assertThatThrownBy(() -> {
            question.delete(loginUser);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("질문자와 답변글의 모든 답변자가 같은 경우 삭제 가능하다.")
    void deleteQuestionWithMyAnswers() throws CannotDeleteException{
        NsUser loginUser = NsUserTest.JAVAJIGI;
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        Answer answer = new Answer(NsUserTest.JAVAJIGI, question, "content1");
        question.addAnswer(answer);

        assertThat(question.isDeleted()).isFalse();
        question.delete(loginUser);
        assertThat(question.isDeleted()).isTrue();
    }
}
