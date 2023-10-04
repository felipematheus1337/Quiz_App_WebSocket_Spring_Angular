package com.appquiz.chat.utils;

import com.appquiz.chat.model.question.Question;
import com.appquiz.chat.model.enums.ChatType;
import com.appquiz.chat.model.enums.MessageType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class MixedMartialArtsUTILS {

    public static List<Question> gerarQuestoes() {
        return gerador();
    }

    public static List<Question> gerador() {
        Question q1 = new Question();

        q1.setQuestionText(" Quem é o campeão dos pesos-pesados do UFC?");

        String opcaoA1 = "A - Conor McGregor";
        String opcaoB1 = "B - Jon Jones";
        String opcaoC1 = "C - Khabib Nurmagomedov";
        String opcaoD1 = "D - Francis Ngannou";

        q1.setOptions(Arrays.asList(opcaoA1, opcaoB1, opcaoC1, opcaoD1));
        q1.setCorrectOption(1);
        q1.setChatType(ChatType.MMA);
        q1.setMessageType(MessageType.QUESTION);

        Question q2 = new Question();

        q2.setQuestionText(" Qual lutador é conhecido como 'The Notorious'?");

        String opcaoA2 = "A - Jon Jones";
        String opcaoB2 = "B - Khabib Nurmagomedov";
        String opcaoC2 = "C - Conor McGregor";
        String opcaoD2 = "D - Anderson Silva";

        q2.setOptions(Arrays.asList(opcaoA2, opcaoB2, opcaoC2, opcaoD2));
        q2.setCorrectOption(2);
        q2.setChatType(ChatType.MMA);
        q2.setMessageType(MessageType.QUESTION);

        Question q3 = new Question();

        q3.setQuestionText(" Qual é a sigla do maior evento de MMA do mundo?");

        String opcaoA3 = "A - UFC";
        String opcaoB3 = "B - Bellator";
        String opcaoC3 = "C - ONE Championship";
        String opcaoD3 = "D - PFL";

        q3.setOptions(Arrays.asList(opcaoA3, opcaoB3, opcaoC3, opcaoD3));
        q3.setCorrectOption(0);
        q3.setChatType(ChatType.MMA);
        q3.setMessageType(MessageType.QUESTION);

        Question q12 = new Question();

        q12.setQuestionText("Qual é a especialidade de Demian Maia nas artes marciais mistas (MMA)?");

        String opcaoA12 = "A - Boxe";
        String opcaoB12 = "B - Muay Thai";
        String opcaoC12 = "C - Jiu-Jitsu";
        String opcaoD12 = "D - Wrestling";

        q12.setOptions(Arrays.asList(opcaoA12, opcaoB12, opcaoC12, opcaoD12));
        q12.setCorrectOption(2);
        q12.setChatType(ChatType.MMA);
        q12.setMessageType(MessageType.QUESTION);


        Question q13 = new Question();

        q13.setQuestionText("Qual é a especialidade de Demian Maia nas artes marciais mistas (MMA)?");

        String opcaoA13 = "A - Boxe";
        String opcaoB13 = "B - Muay Thai";
        String opcaoC13 = "C - Jiu-Jitsu";
        String opcaoD13 = "D - Wrestling";

        q13.setOptions(Arrays.asList(opcaoA13, opcaoB13, opcaoC13, opcaoD13));
        q13.setCorrectOption(2);
        q13.setChatType(ChatType.MMA);
        q13.setMessageType(MessageType.LAST_QUESTION);


        return List.of(q1, q2, q3, q12, q13);
    }

    public static Question getQuestionByIndex(int index) {
        List<Question> questions = gerarQuestoes();

        if (index >= 0 && index < questions.size()) {
            return questions.get(index);
        } else {
            return null;
        }
    }
}
