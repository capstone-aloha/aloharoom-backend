package com.aloharoombackend.Service;

import com.aloharoombackend.Repository.BoardRepository;
import com.aloharoombackend.model.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public List<Board> findAll() {
        return boardRepository.findAll();
    }
}
