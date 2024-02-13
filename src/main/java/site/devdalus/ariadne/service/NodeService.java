package site.devdalus.ariadne.service;

import org.springframework.stereotype.Service;
import site.devdalus.ariadne.dto.NodeDto;
import site.devdalus.ariadne.dto.NodeDto.CreateNodeDto;
import site.devdalus.ariadne.dto.NodeDto.CreateNodeResponseDto;
import site.devdalus.ariadne.dto.NodeDto.GetNodeDetailResponseDto;
import site.devdalus.ariadne.dto.NodeDto.GetNodeResponseDto;

import java.util.UUID;

@Service
public class NodeService {
    public CreateNodeResponseDto createNode(CreateNodeDto createNodeDto) {
        return null;
    }

    public GetNodeResponseDto getNode(UUID nodeId) {
        return null;
    }

    public GetNodeDetailResponseDto getNodeDetail(UUID nodeId) {
        return null;
    }
}
