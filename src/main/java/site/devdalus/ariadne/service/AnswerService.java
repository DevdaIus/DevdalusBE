package site.devdalus.ariadne.service;

import org.springframework.stereotype.Service;
import site.devdalus.ariadne.dto.AnswerDto;
import site.devdalus.ariadne.dto.AnswerDto.CreateAnswerDto;
import site.devdalus.ariadne.dto.AnswerDto.GetAnswerResponseDto;
import site.devdalus.ariadne.dto.AnswerDto.UpdateAnswerDto;

import java.util.UUID;

@Service
public class AnswerService {
    public void createAnswer(CreateAnswerDto createAnswerDto) {

    }

    public GetAnswerResponseDto getAnswer(UUID answerId) {
        return null;
    }

    public void updateAnswer(UpdateAnswerDto updateAnswerDto, UUID answerId) {

    }

    public void removeAnswer(UUID answerId) {

    }
}
