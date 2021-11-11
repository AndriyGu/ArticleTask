package com.test.ArticleTestTask.service;

import com.test.ArticleTestTask.model.DTO.ArticleDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AdminService {





    public Page<ArticleDataDTO> findPaginated(List<ArticleDataDTO> articleDataDTOList, Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<ArticleDataDTO> list;

        if (articleDataDTOList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, articleDataDTOList.size());
            list = articleDataDTOList.subList(startItem, toIndex);
        }
        Page<ArticleDataDTO> bookPage
                = new PageImpl<ArticleDataDTO>(list, PageRequest.of(currentPage, pageSize), articleDataDTOList.size());
        return bookPage;
    }
}
