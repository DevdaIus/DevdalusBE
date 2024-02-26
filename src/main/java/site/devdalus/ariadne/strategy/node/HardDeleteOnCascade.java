package site.devdalus.ariadne.strategy.node;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import site.devdalus.ariadne.domain.Answer;
import site.devdalus.ariadne.domain.Node;
import site.devdalus.ariadne.repository.AnswerRepository;
import site.devdalus.ariadne.repository.NodeRepository;

import java.util.List;
import java.util.UUID;

@Component
public class HardDeleteOnCascade implements NodeRemoveStrategy {
    private final NodeRepository nodeRepository;
    private final AnswerRepository answerRepository;

    @Autowired
    public HardDeleteOnCascade(NodeRepository nodeRepository, AnswerRepository answerRepository) {
        this.nodeRepository = nodeRepository;
        this.answerRepository = answerRepository;
    }

    // TODO bulk delete
    @Transactional
    @Override
    public void remove(Node node) {
        UUID nodeId = node.getNodeId();
        List<Answer> answers = answerRepository.findByNode(node);
        answerRepository.deleteAll(answers);
        nodeRepository.deleteById(nodeId);
    }
}
