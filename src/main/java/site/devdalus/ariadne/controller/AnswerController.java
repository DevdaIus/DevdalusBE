package site.devdalus.ariadne.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.devdalus.ariadne.dto.AnswerDto.CreateAnswerDto;
import site.devdalus.ariadne.dto.AnswerDto.GetAnswerResponseDto;
import site.devdalus.ariadne.service.AnswerService;

import java.util.UUID;

import static site.devdalus.ariadne.dto.AnswerDto.*;

@RestController
@RequestMapping("/v1/answer")
public class AnswerController {
    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateAnswerResponseDto createAnswer(final @RequestBody @Valid CreateAnswerDto createAnswerDto) {
        return answerService.createAnswer(createAnswerDto);
    }

    @GetMapping("/{answerId}")
    public GetAnswerResponseDto getAnswer(final @PathVariable("answerId")UUID answerId) {
        return answerService.getAnswer(answerId);
    }

    @PatchMapping("/{answerId}")
    public ResponseEntity<Void> updateAnswer(final @RequestBody @Valid UpdateAnswerDto updateAnswerDto, final @PathVariable("answerId") UUID answerId) {
        answerService.updateAnswer(updateAnswerDto, answerId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{answerId}")
    public ResponseEntity<Void> removeAnswer(final @PathVariable("answerId") UUID answerId) {
        answerService.removeAnswer(answerId);
        return ResponseEntity.noContent().build();
    }
}
