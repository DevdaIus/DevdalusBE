package site.devdalus.ariadne.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.devdalus.ariadne.constant.AnswerContentType;
import site.devdalus.ariadne.domain.Answer;
import site.devdalus.ariadne.domain.Node;
import site.devdalus.ariadne.dto.AnswerDto.CreateAnswerDto;
import site.devdalus.ariadne.dto.AnswerDto.GetAnswerResponseDto;
import site.devdalus.ariadne.dto.AnswerDto.UpdateAnswerDto;
import site.devdalus.ariadne.exception.ResourceNotExistException;
import site.devdalus.ariadne.repository.AnswerRepository;
import site.devdalus.ariadne.repository.NodeRepository;

import java.util.Optional;
import java.util.UUID;

import static site.devdalus.ariadne.dto.AnswerDto.*;

@Service
@Transactional(readOnly = true)
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final NodeRepository nodeRepository;

    @Autowired
    public AnswerService(AnswerRepository answerRepository, NodeRepository nodeRepository) {
        this.answerRepository = answerRepository;
        this.nodeRepository = nodeRepository;
    }

    // TODO answerContentType TEXT로 고정, 추후 MARKDOWN 입력 받기
    @Transactional
    public CreateAnswerResponseDto createAnswer(CreateAnswerDto createAnswerDto) {
        Node node = nodeRepository
                .findById(createAnswerDto.getNodeId())
                .orElseThrow(() -> new ResourceNotExistException("Node not found."));
        Answer answer = Answer
                .builder()
                .answerContentType(AnswerContentType.TEXT)
                .content(createAnswerDto.getContent())
                .node(node)
                .build();
        Answer savedAnswer = answerRepository.save(answer);
        return CreateAnswerResponseDto.toDto(savedAnswer);
    }

    public GetAnswerResponseDto getAnswer(UUID answerId) {
        Answer answer = answerRepository.findById(answerId).orElseThrow(() -> new ResourceNotExistException("Answer not found."));
        return GetAnswerResponseDto.toDto(answer.getContent());
    }

    @Transactional
    public void updateAnswer(UpdateAnswerDto updateAnswerDto, UUID answerId) {
        Answer answer = answerRepository.findById(answerId).orElseThrow(() -> new ResourceNotExistException("Answer not found."));
        answer.setContent(updateAnswerDto.getContent());
    }

    @Transactional
    public void removeAnswer(UUID answerId) {
        answerRepository.deleteById(answerId);
    }
}
