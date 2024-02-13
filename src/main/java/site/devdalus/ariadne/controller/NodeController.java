package site.devdalus.ariadne.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import site.devdalus.ariadne.dto.NodeDto.*;
import site.devdalus.ariadne.service.NodeService;

import java.util.UUID;

@RestController
@RequestMapping("/v1/node")
public class NodeController {
    private final NodeService nodeService;
    public NodeController(NodeService nodeService) {
        this.nodeService = nodeService;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateNodeResponseDto create(@RequestBody @Valid CreateNodeDto createNode) {
        return nodeService.createNode(createNode);
    }

    @GetMapping("/{nodeId}")
    public GetNodeResponseDto get(@PathVariable("nodeId") UUID nodeId) {
        return nodeService.getNode(nodeId);
    }

    @PatchMapping("/{nodeId}")
    public void update(@RequestBody @Valid UpdateNodeDto updateNode, @PathVariable("nodeId") UUID nodeId) {

    }

    @DeleteMapping("/{nodeId}")
    public void delete(@PathVariable("nodeId") UUID nodeId) {

    }

    @GetMapping("/{nodeId}/detail")
    public GetNodeDetailResponseDto GetNodeDetail(@PathVariable("nodeId") UUID nodeId) {
        return nodeService.getNodeDetail(nodeId);
    }
}
