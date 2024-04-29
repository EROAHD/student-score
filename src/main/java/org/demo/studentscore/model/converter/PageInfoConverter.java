package org.demo.studentscore.model.converter;

import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;

@Component
public class PageInfoConverter {

    public <T> void pageInfoToVO(PageInfo<?> pageInfo, PageInfo<T> VOpageInfo) {
        VOpageInfo.setPageNum(pageInfo.getPageNum());
        VOpageInfo.setPageSize(pageInfo.getPageSize());
        VOpageInfo.setSize(pageInfo.getSize());
        VOpageInfo.setStartRow(pageInfo.getStartRow());
        VOpageInfo.setEndRow(pageInfo.getEndRow());
        VOpageInfo.setPages(pageInfo.getPages());
        VOpageInfo.setPrePage(pageInfo.getPrePage());
        VOpageInfo.setNextPage(pageInfo.getNextPage());
        VOpageInfo.setIsFirstPage(pageInfo.isIsFirstPage());
        VOpageInfo.setIsLastPage(pageInfo.isIsLastPage());
        VOpageInfo.setHasPreviousPage(pageInfo.isHasPreviousPage());
        VOpageInfo.setHasNextPage(pageInfo.isHasNextPage());
        VOpageInfo.setNavigatePages(pageInfo.getNavigatePages());
        VOpageInfo.setNavigatepageNums(pageInfo.getNavigatepageNums());
        VOpageInfo.setNavigateFirstPage(pageInfo.getNavigateFirstPage());
        VOpageInfo.setNavigateLastPage(pageInfo.getNavigateLastPage());
    }
}
