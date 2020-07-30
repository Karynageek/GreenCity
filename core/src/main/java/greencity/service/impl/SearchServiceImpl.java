package greencity.service.impl;

import greencity.dto.PageableDto;
import greencity.dto.search.SearchNewsDto;
import greencity.dto.search.SearchResponseDto;
import greencity.dto.search.SearchTipsAndTricksDto;
import greencity.service.EcoNewsService;
import greencity.service.SearchService;
import greencity.service.TipsAndTricksService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SearchServiceImpl implements SearchService {
    private final EcoNewsService ecoNewsService;
    private final TipsAndTricksService tipsAndTricksService;

    /**
     * Method that allow you to search {@link SearchResponseDto}.
     *
     * @param searchQuery query to search
     * @return list of {@link SearchResponseDto} and {@link SearchTipsAndTricksDto}
     */
    @Override
    public SearchResponseDto search(String searchQuery) {
        PageableDto<SearchNewsDto> ecoNews = ecoNewsService.search(searchQuery);
        PageableDto<SearchTipsAndTricksDto> tipsAndTricks = tipsAndTricksService.search(searchQuery);

        return SearchResponseDto.builder()
            .ecoNews(ecoNews.getPage())
            .tipsAndTricks(tipsAndTricks.getPage())
            .countOfResults(ecoNews.getTotalElements() + tipsAndTricks.getTotalElements())
            .build();
    }

    /**
     * Method that allow you to search all {@link SearchResponseDto}.
     *
     * @param searchQuery query to search
     * @return list of {@link SearchResponseDto} and {@link SearchTipsAndTricksDto}
     */
    @Override
    public SearchResponseDto searchAll(String searchQuery) {
        List<SearchNewsDto> ecoNews = ecoNewsService.searchAll(searchQuery);
        List<SearchTipsAndTricksDto> tipsAndTricks = tipsAndTricksService.searchAll(searchQuery);

        return SearchResponseDto.builder()
                .ecoNews(ecoNews)
                .tipsAndTricks(tipsAndTricks)
                .countOfResults((long) (ecoNews.size() + tipsAndTricks.size()))
                .build();
    }
}
