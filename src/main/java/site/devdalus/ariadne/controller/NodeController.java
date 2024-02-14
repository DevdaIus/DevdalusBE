package site.devdalus.ariadne.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.devdalus.ariadne.dto.NodeDto.*;
import site.devdalus.ariadne.service.NodeService;

import java.util.UUID;

@RestController
@RequestMapping("/v1/node")
public class NodeController {
    private final NodeService nodeService;
    @Autowired
    public NodeController(NodeService nodeService) {
        this.nodeService = nodeService;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateNodeResponseDto create(@RequestBody @Valid CreateNodeDto createNodeDto) {
        return nodeService.createNode(createNodeDto);
    }

    @GetMapping("/{nodeId}")
    public GetNodeResponseDto get(@PathVariable("nodeId") UUID nodeId) {
        return nodeService.getNode(nodeId);
    }

    @PatchMapping("/{nodeId}")
    public ResponseEntity<Void> update(@RequestBody @Valid UpdateNodeDto updateNode, @PathVariable("nodeId") UUID nodeId) {
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{nodeId}")
    public ResponseEntity<Void> delete(@PathVariable("nodeId") UUID nodeId) {
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{nodeId}/detail")
    public GetNodeDetailResponseDto GetNodeDetail(@PathVariable("nodeId") UUID nodeId) {
        return nodeService.getNodeDetail(nodeId);
    }
}