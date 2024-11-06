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
    void deleteQuestion() throws Exception {
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
}
