package com.core.backend.group.ui;

import com.core.backend.common.mock.MockData;
import com.core.backend.common.repsonse.ResultResponse;
import com.core.backend.group.ui.dto.GroupInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GroupController {

    @GetMapping("/groups")
    public ResultResponse<List<GroupInfoResponse>> getAllGroup() {

        List<GroupInfoResponse> responses = MockData.getAllGroupMockData();

        return ResultResponse.success(responses);
    }
}
