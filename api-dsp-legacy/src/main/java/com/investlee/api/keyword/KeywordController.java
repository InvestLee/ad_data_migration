package com.investlee.api.keyword;

import com.investlee.core.group.GroupResult;
import com.investlee.core.group.GroupService;
import com.investlee.core.keyword.KeywordCreateCommand;
import com.investlee.core.keyword.KeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/keyword/v1")
public class KeywordController {

    private final KeywordService keywordService;
    private final GroupService groupService;

    @PostMapping("")
    public KeywordResponse create(@RequestBody KeywordCreateRequest request) {
        GroupResult group = groupService.find(request.groupId());
        return KeywordResponse.from(keywordService.create(
                new KeywordCreateCommand(
                        request.text(),
                        group.id(),
                        group.userId())));
    }

    @GetMapping("/{id}")
    public KeywordResponse find(@PathVariable("id") Long id) {
        return KeywordResponse.from(keywordService.find(id));
    }

    @DeleteMapping("/{id}")
    public KeywordResponse delete(@PathVariable("id") Long id) {
        return KeywordResponse.from(keywordService.delete(id));
    }
}
