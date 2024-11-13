package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {

    private final List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }

    public boolean hasAnotherAnswers(NsUser javajigi) {
        return answers.stream().anyMatch(answer -> !answer.isOwner(javajigi));
    }

    public DeleteHistories deleteAnswers() {
        List<DeleteHistory> deleteHistories = answers.stream().map(Answer::delete).collect(Collectors.toList());
        return new DeleteHistories(deleteHistories);
    }
}
