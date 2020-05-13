package greencity.controller;

import greencity.annotations.ApiPageable;
import greencity.constant.HttpStatuses;
import greencity.dto.search.SearchRequestDto;
import greencity.dto.search.SearchResponseDto;
import greencity.service.SearchService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/search")
@AllArgsConstructor
public class SearchController {
    private final SearchService searchService;

    /**
     * Method for search.
     *
     * @param searchQuery query to search.
     * @return list of {@link SearchResponseDto}.
     */
    @ApiOperation(value = "Search.")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = HttpStatuses.OK),
        @ApiResponse(code = 303, message = HttpStatuses.SEE_OTHER),
        @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN)
    })
    @GetMapping("")
    public ResponseEntity<SearchResponseDto> search(
        @ApiParam(value = "Query to search") @RequestParam String searchQuery) {
        return ResponseEntity.status(HttpStatus.OK).body(searchService.search(searchQuery));
    }

    /**
     * Method for search all by page.
     *
     * @param searchRequestDto query to search.
     * @return list of {@link SearchResponseDto}.
     */
    @ApiOperation(value = "Search all by page.")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = HttpStatuses.OK),
        @ApiResponse(code = 303, message = HttpStatuses.SEE_OTHER),
        @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN)
    })
    @ApiPageable
    @GetMapping("/all")
    public ResponseEntity<SearchResponseDto> searchAll(
        @ApiParam(value = "Query to search and sort parameter (by default - relevance)")
        @RequestBody SearchRequestDto searchRequestDto,
        @ApiIgnore Pageable page) {
        return ResponseEntity.status(HttpStatus.OK).body(searchService.search(page, searchRequestDto));
    }
}
